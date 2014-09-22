package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:04.
 */
public class Float4 {

    public float f[] = new float[4];

    public void load(DataInputStream dis) throws IOException {
        for (int i=0; i<f.length; i++)
            f[i] = dis.readFloat();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        for (float a : f) dos.writeFloat(a);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        for (float a : f)
            dos.writeChars(a + ";");
    }
}
