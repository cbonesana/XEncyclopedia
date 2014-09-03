package org.dnacorp.xencyclopedia.extractor.files;

import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class XCATEntry {

    protected XFile parent;

    protected String filePath;
    protected long offset = 0;
    protected long size = 0;

    public XCATEntry(){}

    public XCATEntry(String filePath, long offset, long size) {
        this.filePath = filePath;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ENTRY: " + filePath + " " + size + " (" + offset + ")";
    }

    public long getSize() throws XFileDriverException {
        return size;
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

    public XFile getParent() {
        return parent;
    }

    public long getOffset() {
        return offset;
    }

    public String getFilePath() {
        return filePath;
    }
}
