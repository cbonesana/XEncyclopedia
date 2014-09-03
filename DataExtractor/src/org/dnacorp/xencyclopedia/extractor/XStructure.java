package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.files.XCATEntry;
import org.dnacorp.xencyclopedia.extractor.files.XFile;
import org.dnacorp.xencyclopedia.extractor.utility.XPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 03.09.2014 18:08.
 */
public class XStructure {

    private Map<String,XFile> archives = new HashMap<>();
    private Map<String, XCATEntry> entriesStructure = new HashMap<>();
    private List<XPath> pathsToFiles = new ArrayList<>();

    public void addArchive(XFile archive) {
        archives.put(archive.getArchiveName(), archive);

        for (XCATEntry entry : archive.getEntryList()) {
            // TODO: check for cat id, keep higher
        }

    }

}
