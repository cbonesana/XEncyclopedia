package org.dnacorp.xencyclopedia.converter.bob.part;

import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertices;
import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBX3VertexData;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:35.
 */
public class BOBFaceList extends ArrayList<BOBFace> {

    private boolean saveVertexTangents(DataOutputStream dos, BOBVertices vertices) {
        // TODO
        return false;
    }

    public int materialIndex;
    public BOBX3VertexData x3VertexData = new BOBX3VertexData();

    public void load(DataInputStream dis, boolean x3data) throws IOException {
        // TODO
    }

    public void toFile(DataOutputStream dos, BOBVertices vertices, boolean x3data) throws IOException {
        // TODO
    }

    public void outputX3VertexDataRaw(DataOutputStream dos) {
        // TODO
    }
}
