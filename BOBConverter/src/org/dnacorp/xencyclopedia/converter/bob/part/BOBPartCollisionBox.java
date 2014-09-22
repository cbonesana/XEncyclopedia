package org.dnacorp.xencyclopedia.converter.bob.part;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DDouble;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:38.
 */
public class BOBPartCollisionBox {

    public Point3DDouble sphereOffse = new Point3DDouble();
    public double sphereDiameter     = 0;
    public Point3DDouble boxOffset   = new Point3DDouble();
    public Point3DDouble boxSize     = new Point3DDouble();

    public void load(DataInputStream dis) throws IOException {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        // TODO
    }

}
