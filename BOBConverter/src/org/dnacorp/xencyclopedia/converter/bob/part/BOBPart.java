package org.dnacorp.xencyclopedia.converter.bob.part;

import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;
import org.dnacorp.xencyclopedia.converter.bob.point.BOBPointMap;
import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertex;
import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertices;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBPart extends ArrayList<BOBFaceList> {

    public static final int FLAG_X3 = 0x10000000;
    public static final int BOD_FLAG_UV = 8;
    public static final int BOD_FLAG_SGBITS = 16;

    public int flags = 0;
    public BOBPartCollisionBox collisionBox;

    private void outputX3VertexData(DataOutputStream dos, BOBPointMap pointMap) {
        // TODO
    }

    private void outputExtraPtInfo(DataOutputStream dos, BOBVertex[] points) {
        // TODO
    }

    private void outputNormals(DataOutputStream dos, BOBVertex[] points) {
        // TODO
    }

    private void outputRaw(DataOutputStream dos, BOBPointMap pointMap, int idx) {
        // TODO
    }

    private void outputBOD(DataOutputStream dos, Settings settings, BOBMaterials materials, BOBPointMap pointMap, int idx) {
        // TODO
    }

    public int numFaces() {
        int count=0;
        for(BOBFaceList it : this)
            count += it.size();

        return count;
    }

    /**
     * find the appropriate face list based on material index
     * @param matIdx
     * @return
     */
    public BOBFaceList get(int matIdx) {
        for (int i=size(); i<=0; i--)
            if (this.get(i).materialIndex == matIdx)
                return this.get(i);
        return null;
    }

    /**
     * find or create an appropriate face list based on material index
     * @param matIdx
     * @return
     */
    public BOBFaceList faceList(int matIdx) {
        BOBFaceList p = get(matIdx);
        if (p == null) {
            p = new BOBFaceList();
            add(p);
            p.materialIndex = matIdx;
        }
        return p;
    }

    public void load(DataInputStream dis) throws IOException {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos, BOBVertices vertices) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos, Settings settings, BOBMaterials materials, BOBPointMap pointMap, int idx) throws IOException {
        // TODO
    }

}
