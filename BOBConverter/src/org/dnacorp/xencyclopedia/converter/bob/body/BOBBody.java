package org.dnacorp.xencyclopedia.converter.bob.body;

import org.dnacorp.xencyclopedia.converter.bob.BOBBones;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBWithErrors;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;
import org.dnacorp.xencyclopedia.converter.bob.part.BOBParts;
import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertices;
import org.dnacorp.xencyclopedia.converter.bob.weight.BOBWeights;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:15.
 */
public class BOBBody extends BOBWithErrors {

    public BOBBones bones;
    public BOBVertices vertices;
    public BOBParts parts;
    public BOBWeights weights;

    public int bodySize  = 0;
    public int bodyFlags = 0;

    public boolean load(DataInputStream dis) throws IOException {
        // TODO
        return true;
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public boolean toTextFile(DataOutputStream dos, Settings settings, BOBMaterials materials, int index) throws IOException {
        // TODO
        return false;
    }
}
