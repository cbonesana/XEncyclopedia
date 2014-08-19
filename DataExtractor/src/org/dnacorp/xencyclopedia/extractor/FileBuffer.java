package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.cat.X2CATBuffer;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 18:42.
 */
public class FileBuffer {

    private int m_nRefCount;
    private int m_lockw;
    private int m_lockr;
    private X2CATBuffer m_cat;
    private long m_allocated;
    private long m_size;
    private long m_binarysize;
    private byte[] m_data;
    private boolean m_bDirty;
    private long m_mtime;

    private static final int GROWBY = 102400;	// 100 KB

    private void allocated(long newsize) {
        m_allocated=newsize;
    }

    private void writeBuffer(byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();
    }

    private int openFilePlain(String pszName, X2FDFlag nAccess, X2FDFlag nCreateDisposition) throws IOException {
        file = new File(pszName);

        if(nCreateDisposition == X2FDFlag.CREATE_NEW)
            if (!file.createNewFile())
                throw new IOException("Can't create new file " + pszName + ".");
        if(nAccess == X2FDFlag.READ){
            if(!file.setReadOnly())
                throw new IOException("Can't set file " + pszName + " to read only.");
        } else if(nAccess == X2FDFlag.WRITE){
            if (!file.setWritable(true))
                throw new IOException("Can't set file " + pszName + " writable.");
        }

        this.pszName = pszName;
        type |= IS_FILE;
        type |= IS_PLAIN;
        mtime(file.lastModified());
        binarysize(file.length());

        FileInputStream fis = new FileInputStream(file);
        m_data = new byte[(int)m_binarysize];
        int readed = fis.read(m_data);
        fis.close();
        return readed;
    }

    private int openFileCompressed(String pszName, X2FDFlag nAccess, X2FDFlag nCreateDisposition, X2FDFlag compressionType) throws IOException {
        openFilePlain(pszName, nAccess, nCreateDisposition);
        int readed = -1;
        // unpack file only if it's not empty
        if (m_size != 0) {
            byte[] buffer = new byte[(int)m_size];
            GZIPInputStream gzis;

            if(compressionType == X2FDFlag.FILETYPE_PCK){
                gzis = new GZIPInputStream(new PCKFileInputStream(file));
            } else {
                gzis = new GZIPInputStream(new FileInputStream(file));
            }

            readed = gzis.read(buffer, 0, (int)m_size);
            gzis.close();
            m_data = buffer;

        } else{
            mtime(file.lastModified());
            dirty(true);
        }

        if(compressionType == X2FDFlag.FILETYPE_PCK)
            type |= IS_PCK;
        else if(compressionType == X2FDFlag.FILETYPE_DEFLATE)
            type |= IS_DEFLATE;
        type |= IS_FILE;
        binarysize(file.length());

        return readed;
    }

    public static final int IS_FILE    = 1;
    public static final int IS_PLAIN   = 2;
    public static final int IS_PCK     = 4;
    public static final int IS_DEFLATE = 8;

    public File file;
    public String pszName;
    public int type;

    public FileBuffer() {
        m_cat = null;
        m_data = null;
        m_size = 0;
        m_binarysize = 0;
        m_nRefCount = 1;
        pszName = null;
        m_lockw=0;
        m_lockr=0;
        type=0;
        m_allocated=0;
        m_bDirty=false;
        m_mtime=-1;
    }

    public void cleanUp() {
        if(m_cat != null)
            m_cat.release();
    }

    public long allocated() {
        return m_allocated;
    }

    public long size() {
        return m_size;
    }

    public long binarysize() {
        return m_binarysize;
    }

    public void binarysize(long newSize) {
        m_binarysize = newSize;
    }

    public long filesize() {
        if(this.isFile() && this.isPlain())
            return file.length();
        return size();
    }

    public long read(byte[] buffer, long size, long offset) throws IOException {
        long readed;
        if (this.isFile() && this.isPlain()){
            FileInputStream fis = new FileInputStream(file);
            readed = fis.read(buffer);
            if (readed != size)
                throw new IOException("Expected " + size + "byte, found " + readed + ".");
        } else {
            if (offset > this->allocated())         // offset is beyond eof (and if allocated()==0)
            return 0;

            readed=size;
            if(size > (this->allocated() - offset)) // shring the size so we won't read past eof
            readed=this->allocated() - offset;
            memcpy(buffer, data() + offset, (size_t)readed);
        }
        return readed;
    }

    public boolean save() {
        return false;
    }

    public byte[] data() {
        return m_data;
    }

    public void data(byte[] buffer, long datasize) {
        m_data=buffer;
        m_allocated=buffer.length;
        m_size=datasize;
    }

    public byte[] end() {
//        return data() + allocated();
        return null;
    }

    public long mtime() { return m_mtime; }
    public void mtime(long modtime) { m_mtime=modtime; }

    public int locked_w() { return m_lockw; }
    public int lock_w() { return ++m_lockw; }
    public int unlock_w() { return (m_lockw > 0 ? --m_lockw : 0); }

    public int locked_r() { return m_lockr; }
    public int lock_r() { return ++m_lockr; }
    public int unlock_r() { return (m_lockr > 0? --m_lockr : 0); }

    public int refcount() { return m_nRefCount; }
    public int addref() { return ++m_nRefCount; }
    public int release() {
        if(--m_nRefCount==0) {
            cleanUp();
            return 0;
        }
        return m_nRefCount;
    }

    public boolean dirty() { return m_bDirty; }
    public void dirty(boolean val) {
        m_bDirty=val;
    }

    public X2CATBuffer cat() {
        return m_cat;
    }

    public void cat(X2CATBuffer buff) {
        buff.addRef();
        if(m_cat != null)
            m_cat.release();
        m_cat=buff;
    }

    public void write(byte[] pData, long size, long offset) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(pData);
        fos.flush();
        fos.close();
        mtime(System.currentTimeMillis());	// update mtime
        dirty(true);
    }

    public boolean allocate(long newsize) {
        return false;
    }

    public boolean convert(int nNewFileType){
        return false;
    }

    public int openFile(String pszName, X2FDFlag nAccess, X2FDFlag nCreateDisposition, X2FDFlag nFileType) throws IOException {
        if(nFileType == X2FDFlag.FILETYPE_AUTO)
            nFileType = GetFileCompressionType(pszName);

        int bRes;

        if(nFileType != X2FDFlag.FILETYPE_PLAIN)
            bRes = openFileCompressed(pszName, nAccess, nCreateDisposition, nFileType);
        else
            bRes = openFilePlain(pszName, nAccess, nCreateDisposition);

        return bRes;
    }

    public static int fileTypeToBufferType(X2FDFlag fileType) {
        int res;
        if(fileType == X2FDFlag.FILETYPE_PCK)
            res = IS_PCK;
        else if(fileType == X2FDFlag.FILETYPE_DEFLATE)
            res = IS_DEFLATE;
        else
            res = IS_PLAIN;
        return res;
    }

    public static X2FDFlag bufferTypeToFileType(int bufferType) {
        X2FDFlag res;
        if((bufferType & IS_PCK) > 0)
            res = X2FDFlag.FILETYPE_PCK;
        else if((bufferType & IS_DEFLATE) > 0)
            res = X2FDFlag.FILETYPE_DEFLATE;
        else
            res = X2FDFlag.FILETYPE_PLAIN;
        return res;
    }

    public boolean isFile() {
        return type % IS_FILE == 0;
    }

    public boolean isPlain() {
        return type % IS_PLAIN == 0;
    }


}
