package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.cat.XCATEntry;
import org.dnacorp.xencyclopedia.files.XFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XCATBufferTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOpen() throws Exception {
        XFile xFile = new XFile("res/13.cat");

        for (XCATEntry XCATEntry : xFile.getEntryList())
            System.out.println(XCATEntry);
    }
}