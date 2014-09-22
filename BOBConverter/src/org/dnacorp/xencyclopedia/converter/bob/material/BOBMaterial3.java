package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBMaterial3 extends BOBMaterial1 {

    public int transparency         = 0;
    public short selfIllumination   = 0;
    public Pair shininess;
    public boolean destinationBlend = false;
    public boolean twoSided         = false;
    public boolean wireframe        = false;
    public short textureValue       = 0;
    public Pair environmentMap;
    public Pair bumpMap;

    public BOBMaterial3() {
        type = MaterialType.mat3;
    }

    public boolean load(DataInputStream dis) throws IOException {
        super.load(dis);
        if (type.value() > MaterialType.mat1.value()) {
            transparency = dis.readInt();
            selfIllumination = dis.readShort();
            shininess.load(dis);
            short s = dis.readShort();
            destinationBlend = (s & 0x02) > 0;
            twoSided = (s & 0x10) > 0;
            wireframe = (s & 0x8) > 0;
            textureValue = dis.readShort();
            environmentMap.load(dis);
            bumpMap.load(dis);
        }

        // TODO: check for errorCode=is.fail() ? e_notEnoughData : e_noError;
        return true;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        super.toBinaryFile(dos);
        dos.writeShort(transparency);
        dos.writeShort(selfIllumination);
        shininess.toBinaryFile(dos);
        short s=0;
        if(destinationBlend) s|=0x2;
        if(twoSided) s|=0x10;
        if(wireframe) s|=0x8;
        dos.writeShort(s);
        dos.writeShort(textureValue);
        environmentMap.toBinaryFile(dos);
        bumpMap.toBinaryFile(dos);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        super.toTextFile(dos);
        dos.writeChars(";" + transparency + ";" + selfIllumination + ";");
        shininess.toTextFile(dos);
        dos.writeChars(";" + destinationBlend  + ";" + twoSided + ";" + wireframe + ";" + textureValue + ";");
        environmentMap.toTextFile(dos);
        dos.writeChar(';');
        bumpMap.toTextFile(dos);
        return true;
    }
}
