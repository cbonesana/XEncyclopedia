package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.BOBErrorStrings;
import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;
import org.dnacorp.xencyclopedia.converter.bob.point.BOBPointMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 22:13.
 */
public class BOBVertices extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_POINT_BEGIN;
    public static final int HDR_END = BOBNames.BOB_SECTION_NAME_POINT_END;

    public BOBPointMap map;
    public List<BOBVertex> newVertices = new ArrayList<>();

    private boolean outputRaw(DataOutputStream dos) {
        // TODO (*)
        return false;
    }
    private boolean outputBOD(DataOutputStream dos) {
        // TODO
        return false;
    }

    public boolean load(DataInputStream dis) throws IOException {
        int hdr = dis.readInt();
        if (hdr != HDR_BEGIN) {
            error(BOBErrorCodes.e_badHeader);
            return false;
        }

        int pointCount = dis.readInt();

        map.create(pointCount);

        BOBVertex ch;
        for (int i = 0; i < pointCount; i++) {
            ch = new BOBVertex();
            if (!ch.load(dis)) {
                error(ch.errorCode, "point[%d]: %s", "" + i, BOBErrorStrings.bobTranslateError(ch.errorCode));
            }
            map.addPoint(ch);
        }

        hdr = dis.readInt();
        if (hdr != HDR_END)
            error(BOBErrorCodes.e_badEndHeader);

        return hdr == HDR_END;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeInt(HDR_BEGIN);

        if (newVertices.size() > 0) {
            dos.writeInt(newVertices.size());
            for (BOBVertex it : newVertices) {
                it.toBinaryFile(dos);
            }
        } else {
            dos.writeInt(map.pointsSize());
            for (int i = 0; i < map.pointsSize(); i++) {
                map.get(i).toBinaryFile(dos);
            }
        }

        dos.writeInt(HDR_END);
        return true;
    }

    public void toTextFile(DataOutputStream dos, Settings settings) throws IOException {
        if (settings.rawMode())
            return outputRaw(dos);
        else
            return outputBOD(dos);
    }
}
