package org.dnacorp.xencyclopedia.converter.bob.dom;

import org.dnacorp.xencyclopedia.converter.bob.BOBInfo;
import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.body.BOBBodies;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 20:59.
 */
public class BOBDomBOB extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_BOB_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_BOB_END;

    private Settings m_settings;

    public BOBInfo info;
    public BOBMaterials materials;
    public BOBBodies bodies;

    public BOBDomBOB(Settings m_settings) {
        this.m_settings = m_settings;
    }

    public boolean load(DataInputStream dis) throws IOException {
        // TODO
        return false;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
        return false;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        // TODO
        return false;
    }
}
