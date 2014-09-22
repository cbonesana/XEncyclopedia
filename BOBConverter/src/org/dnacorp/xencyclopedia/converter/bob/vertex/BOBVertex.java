package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;
import org.dnacorp.xencyclopedia.converter.bob.geometry.Point2DDouble;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBVertex extends Vertex {

    public static final int FLAG_DEFAULT = 0x19;
    public static final int FLAG_UV = 2;
    public static final int FLAG_WEIRD_STUFF = 4;

    public short flags = 0;
    public UVCoord textureCoords;
    public Point2DDouble weirdCoords;
    public int sgbits;
    public NormalVector normalVector;
    public NormalVector tangentVector;

    public BOBErrorCodes errorCode;

    public BOBVertex() {
        super(0, 0, 0);
    }

    public BOBVertex(int x, int y, int z) {
        super(x, y, z);
    }

    public boolean hasTextureCoords() {
        return (flags & FLAG_UV) > 0;
    }

    public boolean load(DataInputStream dis) throws IOException {
        // TODO
        return false;
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
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
