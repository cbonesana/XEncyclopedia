package org.dnacorp.xencyclopedia.files;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public class XFile {

    public String pszCATName;
    public String pszFileName;
    private long fileSize;

    private XDATFile DATFile;
    private XCATFile CATFile;

    public XFile(String pszCATName, String pszFileName) {
        this.pszCATName = pszCATName;
        this.pszFileName = pszFileName;
    }

    public long getFileSize() {
        return fileSize;
    }
}
