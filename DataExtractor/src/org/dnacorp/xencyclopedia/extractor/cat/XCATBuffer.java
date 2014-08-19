package org.dnacorp.xencyclopedia.extractor.cat;

import org.dnacorp.xencyclopedia.extractor.FileBuffer;
import org.dnacorp.xencyclopedia.extractor.XFDFlag;
import org.dnacorp.xencyclopedia.extractor.crypt.Crypt;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;

import java.io.*;
import java.util.ArrayList;

import static org.dnacorp.xencyclopedia.extractor.XFDFlag.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class XCATBuffer extends ArrayList<XCATEntry> {

    private String m_pszCATName;
    private String m_pszDATName;

    private File m_hDATFile;
    private File m_hCATFile;

    private int m_nRefCount;
    private boolean m_bDirty;

    public XCATBuffer(){
        m_nRefCount = 1;
        m_pszCATName = null;
        m_pszDATName = null;
        m_bDirty = false;
    }

    /**
     * Destructor.
     */
    public void cleanUp() {
        if (dirty()) {
            saveCAT();
            saveDAT();
        }
//        m_hCATFile.close();     // buffered reader
//        m_hDATFile.close();     // buffered reader
    }

    public String fileNmae() {
        return m_pszCATName;
    }

    public String fileNameDAT() {
        return m_pszDATName;
    }

    public boolean dirty() {
        return  m_bDirty;
    }

    public int refCount() {
        return m_nRefCount;
    }

    public int addRef() {
        return  ++m_nRefCount;
    }

    public int release() {
        if (--m_nRefCount == 0) {
            cleanUp();
            return 0;
        } else {
            return m_nRefCount;
        }
    }

    public XCATEntry findFile(String pszName){
        for (XCATEntry XCATEntry : this)
            if (XCATEntry.pszFileName.equals(pszName))
                return XCATEntry;
        return null;
    }

    public boolean open(String pszName) throws IOException {
        m_pszCATName = pszName;
        m_hCATFile   = new File(pszName);

        // we must open the corresponding DAT file as well
        // do not use stored dat name because it is always 01.dat even for 02.getCat and 03.getCat
        m_pszDATName = pszName.replace(".getCat", ".dat");
        m_hDATFile   = new File(m_pszDATName);

        if (!m_hCATFile.exists())
            throw new FileNotFoundException("File " + m_pszCATName + " doesn't exist.");
        if (!m_hDATFile.exists())
            throw new FileNotFoundException("File " + m_pszDATName + " doesn't exist.");

        if (m_hCATFile.length() == 0)
            throw new IOException("File " + m_pszCATName + " is empty.");
        if (m_hDATFile.length() == 0)
            throw new IOException("File " + m_pszDATName + " is empty.");

        CATInputStreamReader cisr = new CATInputStreamReader(m_hCATFile);

        int c;
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int offset = 0;
        while ((c = cisr.read()) != -1){
            if (c == 0)                         // skip zero charachers
                continue;
            if (c == 10 || c == 13) {           // read line each \n or \r
                if (first) {                    // avoid first line
                    first = false;
                    sb.setLength(0);
                    continue;
                }
                String line = sb.toString();
                int last = line.lastIndexOf(" ");
                if (last == -1)
                    throw new IOException("CAT file " + pszName + " contains invalid lines.");
                XCATEntry info = new XCATEntry();     // create a new X2CATEntry
                info.pszFileName = line.substring(0,last);
                info.offset = offset;
                info.size   = Long.parseLong(line.substring(last+1));
                add(info);
                sb.setLength(0);                // clean StringBuilder
            } else {
                sb.append((char)c);
            }
            offset++;
        }

        cisr.close();

        return true;
    }

    public FileBuffer loadFile(String pszFile, XFDFlag fileType) throws XFileDriverException, IOException {
        XCATEntry entry = findFile(pszFile);
        if (entry == null)
            throw new XFileDriverException("No entry: " + pszFile + ".", +XFileDriverError.X2FD_E_CAT_NOENTRY);
        return loadFile(entry, fileType);
    }

    public FileBuffer loadFile(XCATEntry entry, XFDFlag fileType) throws XFileDriverException, FileNotFoundException, IOException {
        byte[] outdata;
        long outsize;
        long mtime=-1; // -1 mean "not set"

        FileBuffer buff = new FileBuffer();
        if(entry.size == 0){
            outdata = null;
            outsize = 0;
            if(fileType == FILETYPE_AUTO)
                fileType= FILETYPE_PCK;
            buff.type |= fileType.value();
        } else{
            byte[] data = new byte[entry.getSize()];
            RandomAccessFile raf = new RandomAccessFile(m_hDATFile, "r");
            raf.seek(entry.offset);
            raf.read(data,0,entry.getSize());
            raf.close();

            Crypt.DecryptCAT(data, entry.getSize());

            if(fileType == FILETYPE_AUTO)
                fileType = GetBufferCompressionType(data, entry.getSize());

            if(fileType == FILETYPE_PLAIN) {       // plain file
                outdata = data;
                outsize = entry.getSize();
                buff.type |= FileBuffer.IS_PLAIN;
            } else {                                        // compressed
                outdata = CatPCK.DecompressBuffer(data, entry.getSize(), fileType);
                buff.type |= FileBuffer.fileTypeToBufferType(fileType);
            }
        }

        entry.buffer = buff;
        buff.setCat(this);
        buff.setData(outdata, entry.getSize());
        buff.setTime(mtime);
        buff.setBinarySize(entry.getSize());
        buff.pszName = m_pszDATName + "::" + entry.pszFileName;

        return buff;
    }

    //    public filebuffer * createFile(String pszFile, int fileType);
//    public bool deleteFile(String pszFile);
//    public bool saveFile(filebuffer *buff);
//    public void closeFile(filebuffer *buff);
//
//    public bool create(String pszName);
//
//    public bool renameFile(String pszFileName, String pszNewName);
//
    public static XFDFlag getFileCompressionType(String pszFileName) throws XFileDriverException, IOException {
        int nRes;
        int i = find(pszFileName);

        if(i == -1)
            throw new XFileDriverException("Entry " + pszFileName + " not found.", XFileDriverError.X2FD_E_CAT_NOENTRY);

        XCATEntry entry = this.get(i);
        if(entry.size >= 3){
            byte[] data = new byte[3];
            RandomAccessFile raf = new RandomAccessFile(m_hDATFile, "r");
            raf.seek(entry.offset);
            raf.read(data,0,3);
            raf.close();
            nRes = GetBufferCompressionType(data, 3) == FILETYPE_PCK;
        }

        return nRes;
    }
//    public bool fileStat(String pszFileName, X2FILEINFO *info);
//
//    public iterator findFirstFile(String pattern);
//    public iterator findNextFile(iterator it, String pattern);

    private int find(String pszFileName){
        for (int i = 0; i < this.size(); i++) {
            XCATEntry XCATEntry = this.get(i);
            if (XCATEntry.pszFileName.equals(pszFileName))
                return i;
        }
        return -1;
    }

    private long dataSize() {
        long size = 0;
        for (XCATEntry XCATEntry : this)
            size += XCATEntry.size;
        return size;
    }

    private void setM_bDirty(boolean var) {
        m_bDirty = var;
    }

    private void saveCAT() {

    }

    private void saveDAT() {
//        m_hDATFile.setSize(dataSize());
    }

    private boolean isValidFileName(String m_pszFileName){
        return false;
    }

    private String getDATName() {
//        return extractFileName(fileName(), true) + ".dat";
        return null;
    }



}
