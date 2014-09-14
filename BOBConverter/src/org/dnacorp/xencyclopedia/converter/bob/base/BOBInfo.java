package org.dnacorp.xencyclopedia.converter.bob.base;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 21:28.
 */
public class BOBInfo extends BOBString {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_INFO_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_INFO_END;

    public void load(DataInputStream dis) throws IOException {
        super.load(dis, HDR_BEGIN, HDR_END);
    }

    public void toBinaryFile(DataOutputStream dos, int begin, int end) throws IOException {
        super.toBinaryFile(dos, HDR_BEGIN, HDR_END);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        if (m_text != null && m_text.length() > 0)
            dos.writeChars("/# " + m_text + "\n");
    }

}
