package org.dnacorp.xencyclopedia.extractor.cat;

import org.dnacorp.xencyclopedia.extractor.FileBuffer;
import org.dnacorp.xencyclopedia.extractor.XFDFlag;
import org.dnacorp.xencyclopedia.extractor.crypt.Crypt;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;

import java.io.*;
import java.util.ArrayList;

import static org.dnacorp.xencyclopedia.extractor.XFDFlag.*;

import static org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError.*;

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
        if (isDirty()) {
            saveCAT();
            saveDAT();
        }
    }

    public String getCATPosition() {
        return m_pszCATName;
    }

    public String getDATPosition() {
        return m_pszDATName;
    }

    public boolean isDirty() {
        return  m_bDirty;
    }

    public int getReferenceCount() {
        return m_nRefCount;
    }

    public int addReference() {
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
                XCATEntry info = new XCATEntry();    // create a new X2CATEntry
                info.pszFileName = line.substring(0,last);
                info.offset = offset;
                info.size   = Long.parseLong(line.substring(last+1));
                add(info);
                sb.setLength(0);                     // clean StringBuilder
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
            throw new XFileDriverException("No entry: " + pszFile + ".", + X2FD_E_CAT_NOENTRY);
        return loadFile(entry, fileType);
    }

    public FileBuffer loadFile(XCATEntry entry, XFDFlag fileType) throws XFileDriverException, FileNotFoundException, IOException {
        byte[] outdata;
        long mtime=-1;  // -1 mean "not set"

        FileBuffer buff = new FileBuffer();
        if(entry.size == 0){
            outdata = null;
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
                fileType = CatPCK.GetBufferCompressionType(data, entry.getSize());

            if(fileType == FILETYPE_PLAIN) {       // plain file
                outdata = data;
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

    public FileBuffer createFile(String pszFile, XFDFlag fileType) throws XFileDriverException {

        if(!isValidFileName(pszFile))
            throw new XFileDriverException("Given filename " + pszFile + " is not valid.", X2FD_E_CAT_INVALIDFILENAME);

        if(fileType != FILETYPE_PCK && fileType != FILETYPE_PLAIN && fileType != FILETYPE_DEFLATE)
            throw new XFileDriverException("Given file type is not valid.", X2FD_E_BAD_FLAGS);

        XCATEntry entry = new XCATEntry();
        FileBuffer buff = new FileBuffer();

        buff.pszName = m_pszDATName + "::" + pszFile;
        buff.setDirty(true);
        buff.type |= FileBuffer.fileTypeToBufferType(fileType);

        buff.setCat(this);
        buff.setTime(System.currentTimeMillis());

        entry.pszFileName = pszFile;
        entry.buffer = buff;

        for(XCATEntry xcatEntry: this)
            entry.offset += xcatEntry.size;

        add(entry);
        setDirty(true);

        return buff;
    }

    public boolean deleteFile(String pszFileName) throws XFileDriverException {
        XCATEntry entry = find(pszFileName);
        if(entry == null)
        throw new XFileDriverException("Entry " + pszFileName + " not found.", X2FD_E_CAT_NOENTRY);

        // shrink the DAT and delete the entry
        // calculate size of data below our file
        long sizeLeft = 0;

        int entryPositionInList = findPosition(pszFileName);
        for(int i= entryPositionInList; i<this.size(); i++)
            sizeLeft+=this.get(i).getSize();

        long readPosition;
        long writePosition;
        long size;

        // buffer size (max 20 MB)
        byte[] buff = new byte[20480000];

        // mark ourselves dirty so saveCAT will be called upon close
        setDirty(true);

        readPosition  = entry.offset + entry.size;
        writePosition = entry.offset;

        try {
            RandomAccessFile raf = new RandomAccessFile(m_hDATFile, "wr");
            do {
                size = Long.min(this.size(), sizeLeft);
                raf.seek(readPosition);
                raf.read(buff);
                raf.seek(writePosition);
                raf.write(buff);
                readPosition += size;
                writePosition += size;
                sizeLeft -= size;
            } while (sizeLeft>0);

        } catch (FileNotFoundException e) {
            throw new XFileDriverException("DAT file " + m_pszDATName + " not found.", X2FD_E_FILE_ERROR);
        } catch (IOException e) {
            throw new XFileDriverException("IOException writing on DAT file " + m_pszDATName + ".", X2FD_E_FILE_ERROR);
        }

        // shift the offsets
        for (int i=entryPositionInList+1; i<this.size(); i++) {
            this.get(i).offset -= this.get(i).size;
        }

        this.remove(entry);

        return true;
    }

    public boolean saveFile(FileBuffer buff) {
        // - find the buffer
        // - check number of locks
        // - save that shit:
        //   - move all files below our file up and save our file at the end
        //   - modify the offset in cat entries and move modified file to the end
        String pszCAT;
        String pszFile;
        gzwriter gz;
        char magic;
        byte[] data;
        long dataLen;
        long specLen;
        long sizeLeft;

        ParseCATPath(buff->pszName, &pszCAT, &pszFile);
        iterator pos=find(pszFile);
        if(pos==end()){
            error(X2FD_E_CAT_NOENTRY);
            return false;
        }

        x2catentry *entry=*pos;
        if(buff->locked_r() > 1){
            error(X2FD_E_FILE_LOCKED);
            return false;
        }

        // calculate size of data below our file
        sizeLeft=0;
        for(iterator it=pos + 1; it!=end(); ++it){
            sizeLeft+=it->size;
        }
        if(buff->type & filebuffer::ISPLAIN){
            data=buff->data();
            dataLen=buff->size();
            specLen=dataLen;
        }
        // compressed
        else{
            // pack the file - if it fails the DAT remains unchanged
            if(error(CompressBuffer(buff->data(), buff->size(), buff->mtime(), filebuffer::bufferTypeToFileType(buff->type), &gz, &magic)) != 0) return false;
            data = gz.buffer();
            dataLen = gz.size();
            specLen = dataLen + (buff->type & filebuffer::ISPCK ? 1 : 0);
        }

        bool bNoMove = (specLen == entry->size);

        // there are some data after our file and size of old and new buffer differs
        if(sizeLeft > 0 && bNoMove==false){
            byte *buff;
            // buffer size (max 20 MB)
            size_t size=(size_t)__min(sizeLeft, 20480000);
            buff=new byte[size];
            if(buff==0) {
                error(X2FD_E_MALLOC);
                return false;
            }

            // now move the data after our file in the DAT

            io64::file::size rpos, wpos, s, sl=sizeLeft;

            rpos=entry->offset + entry->size;
            wpos=entry->offset;
            do{
                s=__min(size, sl);
                m_hDATFile.seek((io64::file::offset)rpos, SEEK_SET);
                m_hDATFile.read(buff, s);
                m_hDATFile.seek((io64::file::offset)wpos, SEEK_SET);
                m_hDATFile.write(buff, s);
                rpos+=s;
                wpos+=s;
                sl-=s;
            }
            while(sl > 0);

            delete[] buff;

            // shift the offsets
            for(iterator it=pos + 1; it!=end(); ++it){
                it->offset-=entry->size;
            }
            // set our new offset
            entry->offset=wpos;
        }
        else{
            // if there are no data below our file, just seek to the beginning of the entry
            m_hDATFile.seek((io64::file::offset)entry->offset, SEEK_SET);
        }

        // move cat entry to the end
        if(bNoMove==false && pos!=end() - 1){
            erase(pos);	// 'pos' is the iterator, 'entry' is its value
            push_back(entry);
        }
        // set dirty so destructor will save the modified CAT
        dirty(true);

        // set the new size of modified entry
        entry->size=dataLen;

        // save the data - all data are XORed with 33H
        if(buff->type & filebuffer::ISPCK){
            entry->size++; // increase size of file due to magic
            magic^=0x33;
            m_hDATFile.write(&magic, 1);
        }
        byte *dpos, *dend=data + dataLen;
        for(dpos=data; dpos < dend; dpos++){
            (*dpos)^=0x33;
        }
        m_hDATFile.write(data, dataLen);

        buff->dirty(false);

        return true;

    }
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
            throw new XFileDriverException("Entry " + pszFileName + " not found.", X2FD_E_CAT_NOENTRY);

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

    private int findPosition(String pszFileName) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).pszFileName.equals(pszFileName))
                return i;
        }
        return -1;
    }

    private XCATEntry find(String pszFileName){
        for (XCATEntry xCATEntry : this) {
            if (xCATEntry.pszFileName.equals(pszFileName))
                return xCATEntry;
        }
        return null;
    }

    private long dataSize() {
        long size = 0;
        for (XCATEntry XCATEntry : this)
            size += XCATEntry.size;
        return size;
    }

    private void setDirty(boolean var) {
        m_bDirty = var;
    }

    private void saveCAT() {

    }

    private void saveDAT() {
//        m_hDATFile.setSize(dataSize());
    }

    private boolean isValidFileName(String pszFileName){
        if(pszFileName.length() == 0)
            return false;
        for(char ch : pszFileName.toCharArray()){
            if(ch=='/' || ch==':' || ch=='*' || ch=='<' || ch=='>' || ch=='|')
                return false;
        }
        return true;
    }

    private String getDATName() {
//        return extractFileName(fileName(), true) + ".dat";
        return null;
    }



}
