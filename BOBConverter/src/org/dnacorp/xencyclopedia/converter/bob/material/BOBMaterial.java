package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:44.
 */
public class BOBMaterial {

    public enum MaterialType {
        mat1, mat3, mat5, mat6
    }

    public MaterialType type;
    public short index = 0;

    public void load(DataInputStream dis) throws IOException {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        // TODO
    }
}
