package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:14.
 */
public class PairTexture {

    public String texture;
    public short strength;

    public PairTexture(PairTexture other) {
        texture = other.texture;
        strength = other.strength;
    }

    public void load(DataInputStream dis) throws IOException {
        texture = dis.readUTF();
        strength = dis.readShort();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeChars(texture);
        dos.writeShort(strength);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(texture + ";" + strength);
    }
}
