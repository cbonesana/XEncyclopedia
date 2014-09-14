package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DDouble;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 20:12.
 */
public class Vector extends Point3DDouble {

    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector(org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DDouble pt) {
        super(pt);
    }

    public void load(DataInputStream dis) throws IOException {
        DataInputStream dis = new DataInputStream(fis);
        x = dis.readDouble();
        y = dis.readDouble();
        z = dis.readDouble();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        DataOutputStream dis = new DataOutputStream(fos);
        dis.writeDouble(x);
        dis.writeDouble(y);
        dis.writeDouble(z);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        toBinaryFile(fos);
    }

}
