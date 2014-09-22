package org.dnacorp.xencyclopedia.converter.bob.cut;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBString;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 22:08.
 */
public class BOBName extends BOBString {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_NAME_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_NAME_END;

    public void load(DataInputStream dis) throws IOException {
        super.load(dis, HDR_BEGIN, HDR_END);
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        super.toBinaryFile(dos, HDR_BEGIN, HDR_END);
    }

    public void toTextFile(DataOutputStream dos) {
        // TODO
    }

}
