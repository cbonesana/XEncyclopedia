package org.dnacorp.xencyclopedia.converter.bob.weight;

import org.dnacorp.xencyclopedia.converter.bob.BOBException;
import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.point.BOBPointMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:22.
 */
public class BOBWeights extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_WEIGHT_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_WEIGHT_END;

    public List<BOBWeight> weightList;
    public List<BOBWeight> newWeights;

    public void load(DataInputStream dis) throws IOException, BOBException {

        int header = fis.read();
        if (header != HDR_BEGIN)
            throw new BOBException("Invalid begin header for weights.");

        int count = fis.read();
        for (int i=0; i<count; i++) {
            BOBWeight weight = new BOBWeight();
            try {
                weight.load(fis);
            } catch (IOException e) {
                throw new BOBException("Weight " + i + ": " + e.getMessage());
            }
            weightList.add(weight);
        }

        header = fis.read();
        if (header != HDR_END)
            throw new BOBException("Invalid end header for weights.");
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        if (newWeights.isEmpty() && weightList.isEmpty())
            return;

        fos.write(HDR_BEGIN);

        if (!newWeights.isEmpty()) {
            fos.write(newWeights.size());
            for (BOBWeight weight : newWeights)
                weight.toBinaryFile(fos);
        } else {
            fos.write(weightList.size());
            for (BOBWeight weight : weightList)
                weight.toBinaryFile(fos);
        }

        fos.write(HDR_END);
    }

    public void toTextFile(DataOutputStream dos, BOBPointMap pointMap) {
        // TODO
    }

}
