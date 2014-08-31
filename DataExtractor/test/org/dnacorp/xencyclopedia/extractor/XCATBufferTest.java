package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.cat.XCATEntry;
import org.dnacorp.xencyclopedia.files.XDATEntry;
import org.dnacorp.xencyclopedia.files.XFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XCATBufferTest {

    XFile xFile;

    @Before
    public void setUp() throws Exception {
        xFile = new XFile("res/13.cat");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOpenCAT() throws Exception {
        for (XCATEntry XCATEntry : xFile.getEntryList())
            System.out.println(XCATEntry);
    }

    @Test
    public void testOpenDAT() throws Exception {
        XCATEntry xcatEntry = xFile.getEntryList().get(0);
        XDATEntry xdatEntry = xFile.readDATEntry(xcatEntry);

        for (byte b : xdatEntry.getBuffer().array())
            System.out.print((char)b);
    }
}