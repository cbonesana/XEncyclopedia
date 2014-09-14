package org.dnacorp.xencyclopedia.converter.bob;

import java.io.*;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:45.
 */
public class BOBBones extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_BONE_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_BONE_END;

    public List<String> boneList;

    public void load(DataInputStream dis) throws IOException {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        // TODO
    }
}
