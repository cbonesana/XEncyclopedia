package org.dnacorp.xencyclopedia.extractor.cat;

import org.dnacorp.xencyclopedia.extractor.FileBuffer;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;
import org.dnacorp.xencyclopedia.files.XFile;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class XCATEntry {

    protected XFile parent;

    protected String filePath;
    protected long offset;
    protected long size;

    protected FileBuffer buffer;

    public XCATEntry(){
        offset = 0;
        size = 0;
        buffer = null;
    }

    public XCATEntry(String filePath, long offset, long size) {
        this.filePath = filePath;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ENTRY: " + filePath + " " + size + " (" + offset + ")";
    }

    public int getSize() throws XFileDriverException {
        if (size > Integer.MAX_VALUE)
            throw new XFileDriverException("Entry " + filePath + " is too big (" + size + "byte)", XFileDriverError.XFD_E_CAT_INVALIDSIZE);
        return (int)size;
    }

    public void setParent(XFile parent) {
        this.parent = parent;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
