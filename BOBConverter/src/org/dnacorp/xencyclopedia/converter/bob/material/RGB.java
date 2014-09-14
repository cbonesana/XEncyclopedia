package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 17:54.
 */
public class RGB {

    public short r = 0;
    public short g = 0;
    public short b = 0;

    public void load(DataInputStream dis) throws IOException {
        r = dis.readShort();
        g = dis.readShort();
        b = dis.readShort();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.write(r);
        dos.write(g);
        dos.write(b);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(r + " " + g + " " + b);
    }

}
