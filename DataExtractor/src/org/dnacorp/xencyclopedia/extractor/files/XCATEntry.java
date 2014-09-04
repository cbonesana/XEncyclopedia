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

    public XDATEntry getDATEntry() throws XFileDriverException {
        return parent.readDATEntry(this);
    }

    @Override
    public String toString() {
        return String.format("%02d", parent.id) + ": " + filePath + " " + size + " (" + offset + ")";
    }

    public void setParent(XFile parent) {
        this.parent = parent;
    }

    public void setPath(String filePath) {
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

    public String getPath() {
        return filePath;
    }

    public long getOffset() {
        return offset;
    }

    public long getSize() throws XFileDriverException {
        return size;
    }

    public int getParentId() {
        return parent.id;
    }
}
