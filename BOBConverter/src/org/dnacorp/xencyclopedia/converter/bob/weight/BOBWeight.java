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

    public void load(FileInputStream fis) throws IOException {
        DataInputStream dis = new DataInputStream(fis);
        short count = dis.readShort();

        for (short i=0; i<count; i++) {
            Value v = new Value();
            v.boneIndex = dis.readShort();
            v.boneCoefficient = dis.readInt();
            values.add(v);
        }
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        DataOutputStream dos = new DataOutputStream(fos);
        dos.write((short)values.size());

        for (Value v : values) {
            dos.write(v.boneIndex);
            dos.write(v.boneCoefficient);
        }
    }

    public void toPlainFile(FileOutputStream fos, int index) throws IOException {
        // TODO
    }
}
