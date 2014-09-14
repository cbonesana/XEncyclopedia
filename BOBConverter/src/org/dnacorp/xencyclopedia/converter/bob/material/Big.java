package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:11.
 */
public class Big {

    public static final int flag = 0x2000000;

    public String effect   = null;
    public short technique = 0;

    public BOBMaterial6Values values;

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
