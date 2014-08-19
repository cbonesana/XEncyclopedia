package org.dnacorp.xencyclopedia.extractor.cat;

import org.dnacorp.xencyclopedia.extractor.FileBuffer;
import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class X2CATEntry {

    protected String pszFileName;
    protected long offset;
    protected long size;

    protected FileBuffer buffer;

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

    public int getSize() throws X2FileDriverException {
        if (size > Integer.MAX_VALUE)
            throw new X2FileDriverException("Entry " + pszFileName + " is too big (" + size + "byte)", X2FileDriverError.X2FD_E_CAT_INVALIDSIZE);
        return (int)size;
    }
}
