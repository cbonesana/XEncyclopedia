package org.dnacorp.xencyclopedia.converter.bob;

import org.dnacorp.xencyclopedia.converter.bob.cut.BOBDomCUT;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 20:54.
 */
public class BOBDomDocument {

    public BOBDomBOB bob = null;
    public BOBDomCUT cut = null;

    private Settings settings;

    public BOBDomDocument(Settings settings) {
        this.settings = settings;
    }

    public boolean fromFile(FileInputStream fis) {
        return false;
    }

    public boolean convert(FileInputStream fis, FileOutputStream fos) {
        return false;
    }

    public boolean toTextFile(FileOutputStream fos) {
        return false;
    }

    public boolean toPlainFile(FileOutputStream fos) {
        return false;
    }
}
