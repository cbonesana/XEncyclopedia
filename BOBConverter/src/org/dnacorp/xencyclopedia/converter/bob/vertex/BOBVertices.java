package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.point.BOBPointMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 22:13.
 */
public class BOBVertices extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_POINT_BEGIN;
    public static final int HDR_END = BOBNames.BOB_SECTION_NAME_POINT_END;

    public BOBPointMap map;
    public List<BOBVertex> newVertices = new ArrayList<>();

    private void outputRaw(FileOutputStream os) {
        // TODO
    }
    private void outputBOD(FileOutputStream os) {
        // TODO
    }

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toPlainFile(FileOutputStream fos, int index) throws IOException {
        // TODO
    }
}
