package org.dnacorp.xencyclopedia.converter.bob.base;

import org.dnacorp.xencyclopedia.converter.bob.BOBSection;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 21:26.
 */
public class BOBString extends BOBSection {

    public String m_text;

    public String text() {
        return m_text;
    }

    public void text(String str) {
        m_text = str;
    }

    public void load(FileInputStream fis, int startHeader, int endHeader) {
        // TODO
    }

    public void toFile(FileOutputStream fos, int begin, int end) {
        // TODO
    }

}
