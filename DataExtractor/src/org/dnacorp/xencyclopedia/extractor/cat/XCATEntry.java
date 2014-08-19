package org.dnacorp.xencyclopedia.extractor.cat;

import org.dnacorp.xencyclopedia.extractor.FileBuffer;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class XCATEntry {

    protected String pszFileName;
    protected long offset;
    protected long size;

    protected FileBuffer buffer;

    public XCATEntry(){
        pszFileName = null;
        offset = 0;
        size = 0;
        buffer = null;
    }

    public XCATEntry(String pszFileName, long offset, long size) {
        this.pszFileName = pszFileName;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ENTRY: " + pszFileName + " " + size + " (" + offset + ")";
    }

    public int getSize() throws XFileDriverException {
        if (size > Integer.MAX_VALUE)
            throw new XFileDriverException("Entry " + pszFileName + " is too big (" + size + "byte)", XFileDriverError.X2FD_E_CAT_INVALIDSIZE);
        return (int)size;
    }
}
