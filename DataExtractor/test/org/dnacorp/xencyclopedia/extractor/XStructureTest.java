package org.dnacorp.xencyclopedia.extractor;

import org.junit.Before;
import org.junit.Test;

public class XStructureTest {

    private XStructure xStructure;

    @Before
    public void setUp() throws Exception {
        xStructure = new XStructure();
    }

    @Test
    public void testAddFolder() throws Exception {
        String folder = "C:\\Games\\X3 Terran Conflict";
        xStructure.addFolder(folder);

        System.out.println(xStructure);
    }
}