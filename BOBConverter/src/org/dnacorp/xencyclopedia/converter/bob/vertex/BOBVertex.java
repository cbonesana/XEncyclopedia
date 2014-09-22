package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;
import org.dnacorp.xencyclopedia.converter.bob.geometry.Point2DDouble;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBVertex extends Vertex {

    public static final int FLAG_DEFAULT     = 0x19;
    public static final int FLAG_UV          = 2;
    public static final int FLAG_WEIRD_STUFF = 4;

    public short flags                = 0;
    public UVCoord textureCoords      = new UVCoord();
    public Point2DDouble weirdCoords  = new Point2DDouble();
    public int sgbits;
    public NormalVector normalVector  = new NormalVector();
    public NormalVector tangentVector = new NormalVector();

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
        flags = dis.readShort();
        super.load(dis);

        if ((flags & FLAG_DEFAULT) != FLAG_DEFAULT) {
            errorCode = BOBErrorCodes.e_unkPointFlags;
            return false;
        }

        if (hasTextureCoords())
            textureCoords.load(dis);
        if ((flags & FLAG_WEIRD_STUFF) > 0)
            weirdCoords.load(dis);
        normalVector.load(dis);
        sgbits = dis.readInt();

        return false;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        // set the default bits before outputting
        flags |= FLAG_DEFAULT;
        dos.writeShort(flags);
        super.toBinaryFile(dos);
        if (hasTextureCoords())
            textureCoords.toBinaryFile(dos);
        if ((flags & FLAG_WEIRD_STUFF) > 0)
            weirdCoords.toBinaryFile(dos);
        normalVector.toBinaryFile(dos);
        dos.writeInt(sgbits);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos, int idx) throws IOException {
        int[] coords = getBodCoords();
        dos.writeChars(coords[0] + ";" + coords[1] + ";" + coords[2] + "; // " + idx  + "\n");
        return true;
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
