package org.dnacorp.xencyclopedia.converter.bob.cut;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 22:10.
 */
public class BOBConstants extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_CONST_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_CONST_END;

    public List<Constant> values = new ArrayList<>();

    public void load(DataInputStream dis) {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos) {
        // TODO
    }

    public void toTextFile(DataOutputStream dos) {
        // TODO
    }
}
