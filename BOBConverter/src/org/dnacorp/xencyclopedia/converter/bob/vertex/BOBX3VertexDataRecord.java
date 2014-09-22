package org.dnacorp.xencyclopedia.converter.bob.vertex;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 21:43.
 */
public class BOBX3VertexDataRecord {

    public int pointIndex;
    public Vector tangent = new Vector();
    public Vector unk     = new Vector();

    public void load(DataInputStream dis) throws IOException {
        pointIndex = dis.readInt();
        tangent.load(dis);
        unk.load(dis);
    }

    public void toFile(DataOutputStream dos) throws IOException {
        // TODO
    }

}
