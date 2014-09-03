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

    /** The key is the path to the archive, the value is the archive. */
    private Map<String, XFile> archives = new HashMap<>();
    /** The key is the path to the file inside a CAT, the value is the relative entry. */
    private Map<String, XCATEntry> entriesStructure = new HashMap<>();

    /**
     * Add all the entries in the given {@link org.dnacorp.xencyclopedia.extractor.files.XFile} to the current structure.
     * @param archive the new archive to add.
     */
    public void addArchive(XFile archive) {
        archives.put(archive.getArchiveName(), archive);

        for (XCATEntry entry : archive.getEntryList()) {
            if (entriesStructure.containsKey(entry.getPath())) {
                // the file already exists, check the cat number and update if necessary
                int entryParentId     = entry.getParentId();
                int structureParentId = entriesStructure.get(entry.getPath()).getParentId();
                if (entryParentId > structureParentId)
                    entriesStructure.put(entry.getPath(), entry);
            } else {
                // otherwise add the new file
                entriesStructure.put(entry.getPath(), entry);
            }
        }
    }

    /**
     * Given a path to a file inside a CAT, get the relative {@link org.dnacorp.xencyclopedia.extractor.files.XCATEntry}.
     * @param path the path inside the CAT.
     * @return the relative entry.
     */
    public XCATEntry getArchiveEntry(String path) {
        return entriesStructure.get(path);
    }
}
