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
        x = dis.readDouble();
        y = dis.readDouble();
        z = dis.readDouble();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeDouble(x);
        dos.writeDouble(y);
        dos.writeDouble(z);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(x + ";" + y + ";" + z  + ";");
    }

}
