package org.dnacorp.xencyclopedia.extractor.files;

import java.nio.ByteBuffer;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 31.08.2014 14:52.
 */
public class XDATEntry {

    private final XCATEntry xCATEntry;
    private ByteBuffer buffer;

    public XDATEntry(XCATEntry entry) {
        this.xCATEntry = entry;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

}
