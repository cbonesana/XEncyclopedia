package org.dnacorp.xencyclopedia.files;

import org.dnacorp.xencyclopedia.extractor.cat.CATInputStreamReader;
import org.dnacorp.xencyclopedia.extractor.cat.XCATEntry;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public class XFile {

    protected String positionCATName;
    protected String positionDATName;

    private File DATFile;
    private File CATFile;

    protected List<XCATEntry> xCATEntryList = new ArrayList<>();

    /**
     * @param archiveName
     */
    public XFile(String archiveName) throws XFileDriverException {
        if (archiveName.endsWith(".cat") || archiveName.endsWith(".dat"))
            archiveName = archiveName.substring(0,archiveName.indexOf("."));
        this.positionCATName = archiveName + ".cat";
        this.positionDATName = archiveName + ".dat";

        initFiles();
        try {
            readCAT();
        } catch (IOException e) {
            throw new XFileDriverException("IO Error: " + e.getMessage(), XFileDriverError.XFD_E_FILE_ERROR);
        }
    }

    private void initFiles() throws XFileDriverException {
        DATFile = new File(positionDATName);
        CATFile = new File(positionCATName);

        if (!CATFile.exists())
            throw new XFileDriverException("File " + positionCATName + " doesn't exist.", XFileDriverError.XFD_E_FILE_EXIST);
        if (!DATFile.exists())
            throw new XFileDriverException("File " + positionDATName + " doesn't exist.", XFileDriverError.XFD_E_FILE_EXIST);
        if (!CATFile.isFile())
            throw new XFileDriverException("File " + positionCATName + " is not a valid file.", XFileDriverError.XFD_E_FILE_INVALID);
        if (!DATFile.isFile())
            throw new XFileDriverException("File " + positionDATName + " is not a valid file.", XFileDriverError.XFD_E_FILE_INVALID);
        if (!CATFile.canRead())
            throw new XFileDriverException("File " + positionCATName + " can not be read.", XFileDriverError.XFD_E_FILE_ACCESS);
        if (!DATFile.canRead())
            throw new XFileDriverException("File " + positionDATName + " can not be read.", XFileDriverError.XFD_E_FILE_ACCESS);
    }

    private void readCAT() throws XFileDriverException, IOException {
        CATInputStreamReader cisr = new CATInputStreamReader(CATFile);
        int c;
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int offset = 0;
        while ((c = cisr.read()) != -1){
            if (c == 0)                         // skip zero charachers
                continue;
            if (c == 10 || c == 13) {           // read line each \n or \r
                if (first) {                    // avoid first line
                    first = false;
                    sb.setLength(0);
                    continue;
                }
                String line = sb.toString();
                int last = line.lastIndexOf(" ");
                if (last == -1)
                    throw new XFileDriverException("CAT file " + positionCATName + " contains invalid lines.", XFileDriverError.XFD_E_FILE_INVALID);
                long entrySize = Long.parseLong(line.substring(last+1));

                XCATEntry entry = new XCATEntry();    // create a new X2CATEntry
                entry.setParent(this);
                entry.setFilePath(line.substring(0,last));
                entry.setOffset(offset);
                entry.setSize(entrySize);
                xCATEntryList.add(entry);

                offset += entrySize;
                sb.setLength(0);                     // clean StringBuilder
            } else {
                sb.append((char)c);
            }
        }
        cisr.close();

        if (xCATEntryList.isEmpty())
            throw new XFileDriverException("CAT entry list is empty.", XFileDriverError.XFD_E_FILE_EMPTY);
    }

    public List<XCATEntry> getEntryList() {
        return xCATEntryList;
    }

    public void readDATEntry(XCATEntry entry) {

    }
}