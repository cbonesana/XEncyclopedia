package org.dnacorp.xencyclopedia.converter.bob.cut;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 22:13.
 */
public class BOBNotes extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_NOTE_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_NOTE_END;

    private List<BOBNote> noteList    = new ArrayList<>();

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
