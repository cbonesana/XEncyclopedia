package org.dnacorp.xencyclopedia.converter.bob.vertex;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:29.
 */
public class BOBX3VertexData extends ArrayList<BOBX3VertexDataRecord>{

    public void load(DataInputStream dis) throws IOException {
        // TODO
    }

    public void toFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public int search(int pointIndex) {
        int min=0, mid, res, max = size() -1;

        if (max == -1)
            return -1;
        do {
            mid = (max + min) / 2;
            res = pointIndex - get(mid).pointIndex;
            if (res <= 0) max = mid -1;
            if (res >= 0) min = mid +1;
        } while (min <= max);

        return pointIndex == get(mid).pointIndex ? mid : size();
    }
}
