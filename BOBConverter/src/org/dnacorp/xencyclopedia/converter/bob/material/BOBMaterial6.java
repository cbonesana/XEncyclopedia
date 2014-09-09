package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:10.
 */
public class BOBMaterial6 extends BOBMaterial {

    public int flags   = 0;
    public Small small = null;
    public Big big     = null;

    public BOBMaterial6() {
        type = MaterialType.mat6;
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
