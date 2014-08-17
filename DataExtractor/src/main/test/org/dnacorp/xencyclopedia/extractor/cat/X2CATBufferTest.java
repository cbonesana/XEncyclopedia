package org.dnacorp.xencyclopedia.extractor.cat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class X2CATBufferTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOpen() throws Exception {
        X2CATBuffer x2CATBuffer = new X2CATBuffer();

        x2CATBuffer.open("res/13.cat");

        for (X2CATEntry x2CATEntry : x2CATBuffer)
            System.out.println(x2CATEntry);
    }
}