package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:45.
 */
public class BOBMaterial1 extends BOBMaterial {

    public short textureID = 0;

    public RGB ambient;
    public RGB diffuse;
    public RGB specular;

    public BOBMaterial1() {
        type = MaterialType.mat1;
    }

    public boolean load(DataInputStream dis) throws IOException {
        index = dis.readShort();
        textureID = dis.readShort();
        ambient.load(dis);
        diffuse.load(dis);
        specular.load(dis);

        // TODO: check for errorCode=is.fail() ? e_notEnoughData : e_noError;
        return true;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.write(index);
        dos.write(textureID);
        ambient.toBinaryFile(dos);
        diffuse.toBinaryFile(dos);
        specular.toBinaryFile(dos);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars("MATERIAL");
        switch (type) {
            case mat3: dos.writeChar('3'); break;
            case mat5: dos.writeChar('5'); break;
        }
        dos.writeChars(": " + index + " " + textureID + " ");
        ambient.toTextFile(dos);
        diffuse.toTextFile(dos);
        specular.toTextFile(dos);
        return true;
    }
}
