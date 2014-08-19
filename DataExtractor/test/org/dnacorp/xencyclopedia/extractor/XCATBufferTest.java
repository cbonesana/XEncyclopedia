package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.cat.XCATBuffer;
import org.dnacorp.xencyclopedia.extractor.cat.XCATEntry;
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
        XCATBuffer XCATBuffer = new XCATBuffer();

        XCATBuffer.open("res/13.getCat");

        for (XCATEntry XCATEntry : XCATBuffer)
            System.out.println(XCATEntry);
    }
}