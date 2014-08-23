package org.dnacorp.xencyclopedia.extractor.utils;

import org.dnacorp.xencyclopedia.files.XFile;
import org.dnacorp.xencyclopedia.extractor.exception.XPathException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 18:15.
 */
public class XPath {

    public static String ExtractFilePath(String pszPath) {
        return null;
    }

    public static XFile parseCATPath(String pszName) throws XPathException {
        String[] tokens = pszName.split("::");
        if (tokens.length < 2)
            throw new XPathException("THe given pattern is invalid: " + pszName);

        return new XFile(tokens[0], tokens[1]);
    }
}
