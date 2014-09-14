package org.dnacorp.xencyclopedia.converter.bob.part;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;
import org.dnacorp.xencyclopedia.converter.bob.point.BOBPointMap;
import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertices;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 22:13.
 */
public class BOBParts extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_PART_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_PART_END;

    public List<BOBPart> partList = new ArrayList<>();

    public void load(DataInputStream dis) throws IOException {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos, BOBVertices vertices) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos, Settings settings, BOBMaterials materials, BOBPointMap pointMap) throws IOException {
        // TODO
    }
}
