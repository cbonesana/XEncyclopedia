package org.dnacorp.xencyclopedia.converter.bob.body;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:15.
 */
public class BOBBodies extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_BODY_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_BODY_END;

    public List<BOBBody> bodyList = new ArrayList<>();

    public boolean load(DataInputStream dis) throws IOException {
        // TODO
        return false;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
        return false;
    }

    public boolean toTextFile(DataOutputStream dos, Settings settings, BOBMaterials materials) throws IOException {
        // TODO
        return false;
    }
}
