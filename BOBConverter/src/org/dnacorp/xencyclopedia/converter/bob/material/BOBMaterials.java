package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:15.
 */
public class BOBMaterials extends BOBSection{

    public List<BOBMaterial> materialList;

    public static final int HDR_MAT6_BEGIN = BOBNames.BOB_SECTION_NAME_MAT6_BEGIN;
    public static final int HDR_MAT5_BEGIN = BOBNames.BOB_SECTION_NAME_MAT5_BEGIN;
    public static final int HDR_END        = BOBNames.BOB_SECTION_NAME_MAT_END;

    public void load(DataInputStream dis) throws IOException {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos, int index) throws IOException {
        // TODO
    }

}
