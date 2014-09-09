package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBMaterial3 extends BOBMaterial1 {

    public int transparency         = 0;
    public short selfIllumination   = 0;
    public Pair shininess;
    public boolean destinationBlend = false;
    public boolean twoSided         = false;
    public boolean wireFrame        = false;
    public short textureValue       = 0;
    public Pair environmentMap;
    public Pair bumpMap;

    public BOBMaterial3() {
        type = MaterialType.mat3;
    }

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toPlainFile(FileOutputStream fos) throws IOException {
        // TODO
    }
}
