package org.dnacorp.xencyclopedia.extractor.cat;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class CATEntry {

    protected String pszFileName;
    protected long offset;
    protected long size;

    protected byte[] buffer;

    public CATEntry(){
        pszFileName = null;
        offset = 0;
        size = 0;
        buffer = null;
    }

    public CATEntry(String pszFileName) {
        this.pszFileName = pszFileName;
    }
}
