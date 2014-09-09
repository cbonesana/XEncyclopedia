package org.dnacorp.xencyclopedia.converter.bob;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:45.
 */
public class BOBBones extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_BONE_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_BONE_END;

    public List<String> boneList;

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toPlainFile(FileOutputStream fos) throws IOException {
        // TODO
    }
}
