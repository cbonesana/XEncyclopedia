package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:45.
 */
public class BOBMaterial1 extends BOBMaterial {

    public short textureID = 0;

    public RGB ambient;
    public RGB diffuse;
    public RGB specular;

    public BOBMaterial1() {
        type = MaterialType.mat1;
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
