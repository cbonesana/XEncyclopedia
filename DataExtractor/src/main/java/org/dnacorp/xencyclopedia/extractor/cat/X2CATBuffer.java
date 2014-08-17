package org.dnacorp.xencyclopedia.extractor.cat;

import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class X2CATBuffer extends ArrayList<X2CATEntry> {

    private String m_pszCATName;
    private String m_pszDATName;

    private File m_hDATFile;
    private File m_hCATFile;

    private int m_nRefCount;
    private boolean m_bDirty;

    public X2CATBuffer (){
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

    public X2CATEntry findFile(String pszName){
        for (X2CATEntry x2CATEntry : this)
            if (x2CATEntry.pszFileName.equals(pszName))
                return x2CATEntry;
        return null;
    }

    public boolean open(String pszName) throws IOException {
        m_pszCATName = pszName;
        m_hCATFile   = new File(pszName);

        // we must open the corresponding DAT file as well
        // do not use stored dat name because it is always 01.dat even for 02.cat and 03.cat
        m_pszDATName = pszName.replace(".cat", ".dat");
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
                X2CATEntry info = new X2CATEntry();     // create a new X2CATEntry
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

//    public filebuffer * loadFile(String pszFile, int fileType) throws X2FileDriverException {
//        X2CATEntry entry = findFile(pszFile);
//        if (entry == null)
//            throw new X2FileDriverException("No entry: " + pszFile + ".", +X2FileDriverError.X2FD_E_CAT_NOENTRY);
//        return loadFile(entry, fileType);
//    }
//
//    public filebuffer * loadFile(X2CATEntry entry, int fileType) {
//
//
//
//    }
//    public filebuffer * createFile(const char *pszFile, int fileType);
//    public bool deleteFile(const char *pszFile);
//    public bool saveFile(filebuffer *buff);
//    public void closeFile(filebuffer *buff);
//
//    public bool create(const char *pszName);
//
//    public bool renameFile(const char *pszFileName, const char *pszNewName);
//
//    public int getFileCompressionType(const char *pszFileName);
//    public bool fileStat(const char *pszFileName, X2FILEINFO *info);
//
//    public iterator findFirstFile(const char *pattern);
//    public iterator findNextFile(iterator it, const char *pattern);

    private int find(String pszFileName){
        for (int i = 0; i < this.size(); i++) {
            X2CATEntry x2CATEntry = this.get(i);
            if (x2CATEntry.pszFileName.equals(pszFileName))
                return i;
        }
        return -1;
    }

    private long dataSize() {
        long size = 0;
        for (X2CATEntry x2CATEntry : this)
            size += x2CATEntry.size;
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
