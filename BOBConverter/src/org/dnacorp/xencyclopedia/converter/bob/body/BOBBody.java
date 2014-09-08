package org.dnacorp.xencyclopedia.converter.bob.body;

import org.dnacorp.xencyclopedia.converter.bob.BOBBones;
import org.dnacorp.xencyclopedia.converter.bob.part.BOBParts;
import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertices;
import org.dnacorp.xencyclopedia.converter.bob.weight.BOBWeights;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:15.
 */
public class BOBBody {

    public BOBBones bones;
    public BOBVertices vertices;
    public BOBParts parts;
    public BOBWeights weights;

    public int bodySize = 0;
    public int bodyFlags = 0;

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
