package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DInteger;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 20:12.
 */
public class Vertex extends Point3DInteger {

    public Vertex() {
        super();
    }

    public Vertex(int x, int y, int z) {
        super(x, y, z);
    }

    public Vertex(Point3DInteger pt) {
        super(pt);
    }

    public boolean load(DataInputStream dis) throws IOException {
        x = dis.readInt();
        y = dis.readInt();
        z = dis.readInt();
        return false;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeInt(x);
        dos.writeInt(y);
        dos.writeInt(z);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        toBinaryFile(dos);
        return true;
    }

}
