package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.cat.X2CATBuffer;

import java.io.File;

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

    private long writeBuffer(byte[] data, long size, long offset) {
        return 0;
    }

    private boolean openFilePlain(String pszName, int nAccess, int nCreateDisposition) {
        return false;
    }

    private boolean openFileCompressed(String pszName, int nAccess, int nCreateDisposition, int compressionType) {
        return false;
    }

    public static final int ISFILE = 1;
    public static final int ISPLAIN = 2;
    public static final int ISPCK = 4;
    public static final int ISDEFLATE = 8;

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
//        file.close();
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

    public void binarysize(long newsize) {
        m_binarysize=newsize;
    }

    public long filesize() {
        return 0;
    }

    public long read(byte[] buffer, long size, long offset) {
        return 0;
    }

    public boolean save() {
        return false;
    }

    public byte[] data() {
        return m_data;
    }

    public void data(byte[] buffer, long buffsize, long datasize) {
        m_data=buffer;
        m_allocated=buffsize;
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
    public int release()
    {
        if(--m_nRefCount==0) {
//            delete this;
            return 0;
        }
        else
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

    public long write(byte[] pData, long size, long offset) {
        return 0;
    }

    public boolean allocate(long newsize) {
        return false;
    }

    public boolean convert(int nNewFileType){
        return false;
    }

    public boolean openFile(String pszName, int nAccess, int nCreateDisposition, int nFileType) {
        return false;
    }

    public static int fileTypeToBufferType(X2FDFlag fileType)
    {
        int res;
        if(fileType == X2FDFlag.FILETYPE_PCK)
            res = ISPCK;
        else if(fileType == X2FDFlag.FILETYPE_DEFLATE)
            res = ISDEFLATE;
        else
            res = ISPLAIN;
        return res;
    }

    public static X2FDFlag bufferTypeToFileType(int bufferType)
    {
        X2FDFlag res;
        if((bufferType & ISPCK) > 0)
            res = X2FDFlag.FILETYPE_PCK;
        else if((bufferType & ISDEFLATE) > 0)
            res = X2FDFlag.FILETYPE_DEFLATE;
        else
            res = X2FDFlag.FILETYPE_PLAIN;
        return res;
    }
}
