package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:12.
 */
public class Small {

    public String textureFile = null;
    public RGB ambient;
    public RGB diffuse;
    public RGB specular;
    public int transparency = 0;
    public short selfIllumination = 0;
    public Pair shininess;
    public boolean destinationBlend = false;
    public boolean twoSided = false;
    public boolean wireframe = false;
    public short textureValue;
    public PairTexture enviromentMap;
    public PairTexture bumpMap;
    public PairTexture lightMap;
    public PairTexture map4;
    public PairTexture map5;

    public BOBErrorCodes load(DataInputStream dis, int flags) throws IOException {
        textureFile = dis.readUTF();
        ambient.load(dis);
        diffuse.load(dis);
        specular.load(dis);
        transparency = dis.read();
        selfIllumination = dis.readShort();
        shininess.load(dis);

        short s = (short)flags;
        destinationBlend = (s & 0x2) > 0;
        twoSided = (s & 0x10) > 0;
        wireframe = (s & 0x8) > 0;

        textureValue = dis.readShort();
        enviromentMap.load(dis);
        bumpMap.load(dis);
        lightMap.load(dis);
        map4.load(dis);
        map5.load(dis);

        return BOBErrorCodes.e_noError;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeChars((textureFile == null ? "" : textureFile));
        ambient.toBinaryFile(dos);
        diffuse.toBinaryFile(dos);
        specular.toBinaryFile(dos);
        dos.write(transparency);
        dos.write(selfIllumination);
        shininess.toBinaryFile(dos);
        dos.writeShort(textureValue);
        enviromentMap.toBinaryFile(dos);
        bumpMap.toBinaryFile(dos);
        lightMap.toBinaryFile(dos);
        map4.toBinaryFile(dos);
        map5.toBinaryFile(dos);

        return true;
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars((textureFile == null ? "NULL" : textureFile)); dos.writeChar(';');
        ambient.toTextFile(dos); dos.writeChar(';');
        diffuse.toTextFile(dos); dos.writeChar(';');
        specular.toTextFile(dos); dos.writeChar(';');
        dos.writeChars(transparency + ";");
        dos.writeChars(selfIllumination + ";");
        shininess.toTextFile(dos); dos.writeChar(';');
        dos.writeChars(textureValue + ";");
        enviromentMap.toTextFile(dos); dos.writeChar(';');
        bumpMap.toTextFile(dos); dos.writeChar(';');
        lightMap.toTextFile(dos); dos.writeChar(';');
        map4.toTextFile(dos); dos.writeChar(';');
        map5.toTextFile(dos); dos.writeChar(';');
    }

}
