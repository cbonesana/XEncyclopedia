package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:11.
 */
public class Big {

    public static final int flag = 0x2000000;

    public String effect   = null;
    public short technique = 0;

    public BOBMaterial6Values values = new BOBMaterial6Values();

    public BOBErrorCodes load(DataInputStream dis) throws IOException {
        technique = dis.readShort();
        return values.load(dis);
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.write(technique);
        dos.writeChars(effect);
        values.toBinaryFile(dos);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(technique + ";" + effect + ";");
        values.toTextFile(dos);
        return true;
    }
}
