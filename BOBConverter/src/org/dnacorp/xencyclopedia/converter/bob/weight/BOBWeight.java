package org.dnacorp.xencyclopedia.converter.bob.weight;

import java.io.*;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:22.
 */
public class BOBWeight {

    public List<Value> values;

    public BOBWeight() {}

    public BOBWeight(BOBWeight other) {
        for (Value v : other.values)
            values.add(v);
    }

    public void load(DataInputStream dis) throws IOException {
        short count = dis.readShort();

        for (short i=0; i<count; i++) {
            Value v = new Value();
            v.boneIndex = dis.readShort();
            v.boneCoefficient = dis.readInt();
            values.add(v);
        }
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.write((short)values.size());

        for (Value v : values) {
            dos.write(v.boneIndex);
            dos.write(v.boneCoefficient);
        }
    }

    public void toTextFile(DataOutputStream dos, int index) throws IOException {
        // TODO
    }
}
