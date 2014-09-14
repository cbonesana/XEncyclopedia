package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.BOBErrorStrings;
import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.*;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:15.
 */
public class BOBMaterials extends BOBSection{

    public List<BOBMaterial> materialList;

    public static final int HDR_MAT6_BEGIN = BOBNames.BOB_SECTION_NAME_MAT6_BEGIN;
    public static final int HDR_MAT5_BEGIN = BOBNames.BOB_SECTION_NAME_MAT5_BEGIN;
    public static final int HDR_END        = BOBNames.BOB_SECTION_NAME_MAT_END;

    public boolean load(DataInputStream dis) throws IOException {
        int hdr = dis.readInt();
        BOBMaterial.MaterialType type;

        switch (hdr) {
            case HDR_MAT5_BEGIN: type = BOBMaterial.MaterialType.mat5; break;
            case HDR_MAT6_BEGIN: type = BOBMaterial.MaterialType.mat6; break;
            default:
                error(BOBErrorCodes.e_badHeader);
                return false;
        }

        int matCount = dis.readInt();

        for (int i=0; i<matCount; i++) {
            BOBMaterial m = null;
            switch (type) {
                case mat5: m = new BOBMaterial5(); break;
                case mat6: m = new BOBMaterial6(); break;
            }
            if (m == null) {
                BOBErrorCodes e = BOBErrorCodes.e_unkMaterialValueType;
                error(e, "material[%d]: %s", "" + i, BOBErrorStrings.bobTranslateError(e));
            } else if (!m.load(dis)) {
                error(m.errorCode, "material[%d]: %s", "" + i, BOBErrorStrings.bobTranslateError(m.errorCode));
            } else {
                materialList.add(m);
            }
        }

        hdr = dis.readInt();
        if (hdr != HDR_END)
            error(BOBErrorCodes.e_badEndHeader);

        return hdr == HDR_END;
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        // TODO
    }

    public int size() {
        return materialList.size();
    }
}
