package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.cat.XCATBuffer;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 18:42.
 */
public class FileBuffer {

    public static final int IS_FILE    = 1;
    public static final int IS_PLAIN   = 2;
    public static final int IS_PCK     = 4;
    public static final int IS_DEFLATE = 8;

    private static final int GROWBY = 102400;	// 100 KB

    private int m_nRefCount;
    private int m_lockW;
    private int m_lockR;
    private XCATBuffer m_cat;
    private long m_allocated;
    private long m_size;
    private long m_binarySize;
    private byte[] m_data;
    private boolean m_bDirty;
    private long m_mTime;

    public File file;
    public String pszName;
    public int type;

    public FileBuffer() {
        m_cat = null;
        m_data = null;
        m_size = 0;
        m_binarySize = 0;
        m_nRefCount = 1;
        pszName = null;
        m_lockW =0;
        m_lockR =0;
        type=0;
        m_allocated=0;
        m_bDirty=false;
        m_mTime =-1;
    }

    private void setAllocated(long newsize) {
        m_allocated = newsize;
    }

    private void writeBuffer(byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();
    }

    private int openFilePlain(String pszName, XFDFlag nAccess, XFDFlag nCreateDisposition) throws IOException {
        file = new File(pszName);

        if(nCreateDisposition == XFDFlag.CREATE_NEW)
            if (!file.createNewFile())
                throw new IOException("Can't create new file " + pszName + ".");
        if(nAccess == XFDFlag.READ){
            if(!file.setReadOnly())
                throw new IOException("Can't set file " + pszName + " to read only.");
        } else if(nAccess == XFDFlag.WRITE){
            if (!file.setWritable(true))
                throw new IOException("Can't set file " + pszName + " writable.");
        }

        this.pszName = pszName;
        type |= IS_FILE;
        type |= IS_PLAIN;
        setTime(file.lastModified());
        setBinarySize(file.length());

        FileInputStream fis = new FileInputStream(file);
        m_data = new byte[(int) m_binarySize];
        int read = fis.read(m_data);
        fis.close();
        return read;
    }

    private int openFileCompressed(String pszName, XFDFlag nAccess, XFDFlag nCreateDisposition, XFDFlag compressionType) throws IOException {
        openFilePlain(pszName, nAccess, nCreateDisposition);
        int read = -1;
        // unpack file only if it's not empty
        if (m_size != 0) {
            byte[] buffer = new byte[(int)m_size];

            GZIPInputStream gZis;

            if(compressionType == XFDFlag.FILETYPE_PCK){
                gZis = new GZIPInputStream(new PCKFileInputStream(file));
            } else {
                gZis = new GZIPInputStream(new FileInputStream(file));
            }

            read = gZis.read(buffer, 0, (int)m_size);
            gZis.close();
            m_data = buffer;

        } else{
            setTime(file.lastModified());
            setDirty(true);
        }

        if(compressionType == XFDFlag.FILETYPE_PCK)
            type |= IS_PCK;
        else if(compressionType == XFDFlag.FILETYPE_DEFLATE)
            type |= IS_DEFLATE;
        type |= IS_FILE;
        setBinarySize(file.length());

        return read;
    }

    public void cleanUp() {
        if(m_cat != null)
            m_cat.release();
    }

    public long getAllocated() {
        return m_allocated;
    }

    public long getSize() {
        return m_size;
    }

    public long getBinarySize() {
        return m_binarySize;
    }

    public void setBinarySize(long newSize) {
        m_binarySize = newSize;
    }

    public long getFileSize() {
        if(this.isFile() && this.isPlain())
            return file.length();
        return getSize();
    }

    public long read(byte[] buffer, long size, long offset) throws IOException {
        long read;
        if (this.isFile() && this.isPlain()){
            FileInputStream fis = new FileInputStream(file);
            read = fis.read(buffer);
            if (read != size)
                throw new IOException("Expected " + size + "byte, found " + read + ".");
        } else {
            if (offset > getAllocated())         // offset is beyond eof (and if getAllocated()==0)
                return 0;

            read=size;
            if(size > (getAllocated() - offset)) // shring the getSize so we won't read past eof
                read= getAllocated() - offset;

            System.arraycopy(getData(), (int) offset, buffer, 0, (int) size);
        }
        return read;
    }

    public boolean save() {
        return false;
    }

    public byte[] getData() {
        return m_data;
    }

    public void setData(byte[] buffer, long datasize) {
        m_data=buffer;
        m_allocated=buffer.length;
        m_size=datasize;
    }

    public int end() {
        return m_data.length;
    }

    public long getTime() { return m_mTime; }
    public void setTime(long modtime) { m_mTime =modtime; }

    public int getLockWrite() { return m_lockW; }
    public int lockWrite() { return ++m_lockW; }
    public int unLockWrite() { return (m_lockW > 0 ? --m_lockW : 0); }

    public int getLockRead() { return m_lockR; }
    public int lockRead() { return ++m_lockR; }
    public int unLockRead() { return (m_lockR > 0? --m_lockR : 0); }

    public int getRefCount() { return m_nRefCount; }
    public int addRed() { return ++m_nRefCount; }
    public int releaseRef() {
        if(--m_nRefCount==0) {
            cleanUp();
            return 0;
        }
        return m_nRefCount;
    }

    public boolean isDirty() { return m_bDirty; }
    public void setDirty(boolean val) {
        m_bDirty=val;
    }

    public XCATBuffer getCat() {
        return m_cat;
    }

    public void setCat(XCATBuffer buff) {
        buff.addReference();
        if(m_cat != null)
            m_cat.release();
        m_cat=buff;
    }

    public void write(byte[] pData, long size, long offset) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(pData);
        fos.flush();
        fos.close();
        setTime(System.currentTimeMillis());	// update getTime
        setDirty(true);
    }

    public boolean allocate(long newsize) {
        return false;
    }

    public boolean convert(XFDFlag nNewFileType){
        return false;
    }

    public int openFile(String pszName, XFDFlag nAccess, XFDFlag nCreateDisposition, XFDFlag nFileType) throws IOException {
//        if(nFileType == XFDFlag.FILETYPE_AUTO)
//            nFileType = GetFileCompressionType(pszName);

        int bRes;

        if(nFileType != XFDFlag.FILETYPE_PLAIN)
            bRes = openFileCompressed(pszName, nAccess, nCreateDisposition, nFileType);
        else
            bRes = openFilePlain(pszName, nAccess, nCreateDisposition);

        return bRes;
    }

    public static int fileTypeToBufferType(XFDFlag fileType) {
        int res;
        if(fileType == XFDFlag.FILETYPE_PCK)
            res = IS_PCK;
        else if(fileType == XFDFlag.FILETYPE_DEFLATE)
            res = IS_DEFLATE;
        else
            res = IS_PLAIN;
        return res;
    }

    public static XFDFlag bufferTypeToFileType(int bufferType) {
        XFDFlag res;
        if((bufferType & IS_PCK) > 0)
            res = XFDFlag.FILETYPE_PCK;
        else if((bufferType & IS_DEFLATE) > 0)
            res = XFDFlag.FILETYPE_DEFLATE;
        else
            res = XFDFlag.FILETYPE_PLAIN;
        return res;
    }

    public boolean isFile() {
        return type % IS_FILE == 0;
    }

    public boolean isPlain() {
        return type % IS_PLAIN == 0;
    }


}
