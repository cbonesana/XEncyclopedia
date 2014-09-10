package org.dnacorp.xencyclopedia.converter.bob;

import org.dnacorp.xencyclopedia.converter.bob.body.BOBBodies;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 20:59.
 */
public class BOBDomBOB extends BOBSection{

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_BOB_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_BOB_END;

    private Settings m_settings;

    public BOBInfo info;
    public BOBMaterials materials;
    public BOBBodies bodies;

    public BOBDomBOB(Settings m_settings) {
        this.m_settings = m_settings;
    }

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toPlainFile(FileOutputStream fos, int index) throws IOException {
        // TODO
    }
}
