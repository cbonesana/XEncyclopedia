package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:09.
 */
public class BOBMaterial6Values {

    public List<Material6Value> materialList = new ArrayList<>();

    public BOBErrorCodes load(DataInputStream dis) throws IOException {
        short count = dis.readShort();
        Material6Value v;
        BOBErrorCodes res;

        for (int i=0; i<count; i++) {
            v = new Material6Value();
            if ((res = v.load(dis)) != BOBErrorCodes.e_noError)
                return res;
            materialList.add(v);
        }
        return BOBErrorCodes.e_noError;
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeShort(materialList.size());
        for (Material6Value it : materialList)
            it.toBinaryFile(dos);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(materialList.size() + ";");
        for (Material6Value it : materialList)
            it.toTextFile(dos);
    }
}
