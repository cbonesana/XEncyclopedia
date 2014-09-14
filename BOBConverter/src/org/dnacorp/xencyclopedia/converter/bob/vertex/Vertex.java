package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DInteger;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 20:12.
 */
public class Vertex extends Point3DInteger {

    public Vertex(int x, int y, int z) {
        super(x, y, z);
    }

    public Vertex(Point3DInteger pt) {
        super(pt);
    }

    public void load(DataInputStream dis) throws IOException {
        DataInputStream dis = new DataInputStream(fis);
        x = dis.readInt();
        y = dis.readInt();
        z = dis.readInt();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        DataOutputStream dis = new DataOutputStream(fos);
        dis.writeInt(x);
        dis.writeInt(y);
        dis.writeInt(z);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        toBinaryFile(fos);
    }

}
