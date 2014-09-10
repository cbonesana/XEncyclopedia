package org.dnacorp.xencyclopedia.converter.bob.part;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DDouble;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:38.
 */
public class BOBPartCollisionBox {

    public Point3DDouble sphereOffse;
    public double sphereDiameter = 0;
    public Point3DDouble boxOffset;
    public Point3DDouble boxSize;

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toTextFile(FileOutputStream fos) throws IOException {
        // TODO
    }

}
