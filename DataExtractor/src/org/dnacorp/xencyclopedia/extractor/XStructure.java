package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;
import org.dnacorp.xencyclopedia.extractor.files.XCATEntry;
import org.dnacorp.xencyclopedia.extractor.files.XFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 03.09.2014 18:08.
 */
public class XStructure {

    /** The key is the path to the archive, the value is the archive. */
    private Map<String, XFile> archives = new HashMap<>();
    /** The key is the path to the file inside a CAT, the value is the relative entry. */
    private SortedMap<String, XCATEntry> entriesStructure = new TreeMap<>();

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
     * Reads the whole directory of a game and searches for the CAT files.
     * @param directory the path to the directory.
     * @throws XFileDriverException if the path is not a valid directory.
     */
    public void addFolder(String directory) throws XFileDriverException {
        File folder = new File(directory);

        if (!folder.isDirectory())
            throw new XFileDriverException("The path " + directory + " is not a valid directory.", XFileDriverError.XFD_E_FILE_ACCESS);

        if (folder.listFiles() != null) {
            for (File fileEntry : folder.listFiles()) {
                if (fileEntry.getAbsolutePath().endsWith(".cat")) {
                    XFile archive = new XFile(fileEntry.getAbsolutePath());
                    addArchive(archive);
                }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (XCATEntry entry: entriesStructure.values())
            sb.append(entry.toString()).append("\n");

        return sb.toString();
    }
}
