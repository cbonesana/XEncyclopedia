package org.dnacorp.xencyclopedia.extractor.cat;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class X2CATEntry {

    protected String pszFileName;
    protected long offset;
    protected long size;

    protected byte[] buffer;

    public X2CATEntry(){
        pszFileName = null;
        offset = 0;
        size = 0;
        buffer = null;
    }

    public X2CATEntry(String pszFileName, long offset, long size) {
        this.pszFileName = pszFileName;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ENTRY: " + pszFileName + " " + size + " (" + offset + ")";
    }
}
