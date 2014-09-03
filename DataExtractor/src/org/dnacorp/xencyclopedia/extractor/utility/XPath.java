package org.dnacorp.xencyclopedia.extractor.utility;

import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;
import org.dnacorp.xencyclopedia.extractor.files.XFile;
import org.dnacorp.xencyclopedia.extractor.exception.XPathException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 18:15.
 */
public class XPath {

    private String pathToCAT;
    private String pathInsideCAT;

    public XPath(String pathToCAT, String pathInsideCAT) {
        this.pathToCAT = pathToCAT;
        this.pathInsideCAT = pathInsideCAT;
    }

    public XPath(String newPath) throws XPathException {
        parseCATPath(newPath);
    }

    private void parseCATPath(String pszName) throws XPathException {
        String[] tokens = pszName.split("::");
        if (tokens.length < 2)
            throw new XPathException("The given pattern is invalid: " + pszName);

        pathToCAT = tokens[0];
        pathInsideCAT = tokens[0];
    }

    public String getPathToCAT() {
        return pathToCAT;
    }

    public String getPathInsideCAT() {
        return pathInsideCAT;
    }

    @Override
    public String toString() {
        return pathToCAT + "::" + pathInsideCAT;
    }
}
