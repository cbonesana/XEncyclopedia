package org.dnacorp.xencyclopedia.converter.bob.weight;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:23.
 */
public class Value {

    public static final double MULTIPLIER = 65536;

    public short boneIndex;
    public int boneCoefficient;

    public void toFile(DataOutputStream dos) throws IOException {
        byte[] bytes = new byte[2];
        bytes[0] = (byte)(boneIndex & 0xff);
        bytes[1] = (byte)((boneIndex >> 8) & 0xff);

        fos.write(bytes);
        fos.write((int) (boneCoefficient / MULTIPLIER));
    }
}
