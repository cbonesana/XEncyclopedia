package org.dnacorp.xencyclopedia.converter.bob;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBString;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:51.
 */
public class BOBInfo extends BOBString {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_INFO_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_INFO_END;

    public boolean load(DataInputStream dis) throws IOException {
        return load(dis, HDR_BEGIN, HDR_END);
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        toBinaryFile(dos, HDR_BEGIN, HDR_END);
    }

}
