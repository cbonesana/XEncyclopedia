package org.dnacorp.xencyclopedia.converter.bob.base;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 21:28.
 */
public class BOBInfo extends BOBString {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_INFO_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_INFO_END;

    public void load(FileInputStream fis) {
        super.load(fis, HDR_BEGIN, HDR_END);
    }

    public void toBinaryFile(FileOutputStream fos, int begin, int end) {
        super.toFile(fos, HDR_BEGIN, HDR_END);
    }

    public void toTextFile(FileOutputStream fos) {
        // TODO
    }


}
