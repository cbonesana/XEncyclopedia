package org.dnacorp.xencyclopedia.extractor;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public class X2FileInfo {

    private static final int X2FD_FI_FILE      = 1;
    private static final int X2FD_FI_PLAIN     = 2;
    private static final int X2FD_FI_PCK       = 4;
    private static final int X2FD_FI_DEFLATE   = 8;

    public String szFileName;
    public long mtime;
    public long size;
    public long binarySize;
    public int  flags;

    public boolean isPlain() {
        return false;
    }
}
