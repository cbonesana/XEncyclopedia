package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBMaterial5 extends BOBMaterial3{

    public Pair lightMap;

    public BOBMaterial5() {
        type = MaterialType.mat5;
    }

    public boolean load(DataInputStream dis) throws IOException {
        super.load(dis);
        if (type.value() >  MaterialType.mat3.value())
            lightMap.load(dis);

        // TODO: check for errorCode=is.fail() ? e_notEnoughData : e_noError;
        return true;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        super.toBinaryFile(dos);
        if (type.value() >  MaterialType.mat3.value())
            lightMap.toBinaryFile(dos);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        super.toBinaryFile(dos);
        if (type.value() >  MaterialType.mat3.value()) {
            dos.writeChar(';');
            lightMap.toBinaryFile(dos);
        }
        return true;
    }
}
