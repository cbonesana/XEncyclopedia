package org.dnacorp.xencyclopedia.extractor;

import org.apache.commons.io.IOUtils;
import org.dnacorp.xencyclopedia.extractor.files.XCATEntry;
import org.dnacorp.xencyclopedia.extractor.files.XDATEntry;
import org.junit.Before;
import org.junit.Test;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class XStructureTest {

    private XStructure xStructure;
    private String folder = "C:\\Games\\X3 Terran Conflict";
    private String obj = "objects/ships/M3/Argon_M3P.pbb";

    @Before
    public void setUp() throws Exception {
        xStructure = new XStructure();
    }

    @Test
    public void testAddFolder() throws Exception {
        xStructure.addFolder(folder);
//        System.out.println(xStructure);

        XDATEntry xdatEntry = xStructure.getArchiveEntry(obj).getDATEntry();

        FileOutputStream fos = new FileOutputStream(new File("out.txt"));
        IOUtils.write(xdatEntry.getBuffer().array(), fos);
        fos.close();
    }
}