package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point2DDouble;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBVertex extends Vertex {

    public static final int FLAG_DEFAULT = 0x19;
    public static final int FLAG_UV = 2;
    public static final int FLAG_WEIRD_STUFF = 4;

    public short flags = 0;
    UVCoord textureCoords;
    Point2DDouble weirdCoords;
    int sgbits;
    NormalVector normalVector;
    NormalVector tangentVector;

    public BOBVertex(int x, int y, int z) {
        super(x, y, z);
    }

    public boolean hasTextureCoords() {
        return (flags & FLAG_UV) > 0;
    }

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toPlainFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public int[] getBodCoords() {
        int[] coords = new int[3];
        coords[0] = vertexBob2Bod(x);
        coords[1] = vertexBob2Bod(y);
        coords[2] = vertexBob2Bod(z);
        return coords;
    }

    private int vertexBob2Bod(int i) {
        // TODO
        return 0;
    }


}
