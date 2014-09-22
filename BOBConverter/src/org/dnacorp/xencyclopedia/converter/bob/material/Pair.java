package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 17:54.
 */
public class Pair {

    public short value = 0;
    public short strength = 0;

    public void load(DataInputStream dis) throws IOException {
        value = dis.readShort();
        strength = dis.readShort();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeShort(value);
        dos.writeShort(strength);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(value + ";" + strength);
    }
}
