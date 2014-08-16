package org.dnacorp.xencyclopedia.extractor.cat;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 22:42.
 */
public class CATBuffer extends ArrayList<CATEntry> {

    private String m_pszFileName;
    private String m_pszDATName;

    private File m_hDATFile;
    private File m_hCATFile;

    private int m_nRefCount;
    private boolean m_bDirty;

    private int find(String pszFileName){
        return Collections.binarySearch(this,new CATEntry(pszFileName),new Comparator<CATEntry>() {
            @Override
            public int compare(CATEntry o1, CATEntry o2) {
                return o1.pszFileName.compareTo(o2.pszFileName);
            }
        });
    }

    private String getDATName() {
        return extractFileName(fileName(), true) + ".dat";
    }

}
