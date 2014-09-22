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
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_POINT_END;

    public BOBPointMap map             = new BOBPointMap();
    public List<BOBVertex> newVertices = new ArrayList<>();

    private boolean outputRaw(DataOutputStream dos) throws IOException {
        StringBuilder sb = new StringBuilder();
        int i=0;

        sb.append("// Vertices begin").append(map.pointsSize()).append(" - ").append(map.uniquePointsSize()).append("\n");
        for (BOBVertex it : map.m_points) {
            sb.append(it.x).append(";").append(it.y).append(";").append(it.y);
            if (it.hasTextureCoords())
                sb.append("\t\tUV: ").append(it.textureCoords.x).append(";").append(it.textureCoords.y);
            if ((it.flags & BOBVertex.FLAG_WEIRD_STUFF) > 0) {
                sb.append("\tWeird coords: ").append(it.weirdCoords.x).append(";").append(it.weirdCoords.y);
            }
            sb.append("\tNormal: ").append(it.normalVector.x).append(";").append(it.normalVector.y).append(";").append(it.normalVector.z);
            sb.append("\tSGBits: ").append(it.sgbits);
            sb.append("//").append(i).append("\n");
        }
        sb.append("-1; -1; -1; // Vertices end\n\n");

        dos.writeChars(sb.toString());

        return true;
    }
    private boolean outputBOD(DataOutputStream dos) throws IOException {
        int i=0;
        dos.writeChars("// beginning of points (" + map.uniquePointsSize() + ")\n");

        BOBVertex p;
        while ((p=map.nextUniquePoint()) != null) {
            p.toBinaryFile(dos);
        }
        dos.writeChars("-1; -1; -1; // points end\n\n");

        return true;
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

    public boolean toTextFile(DataOutputStream dos, Settings settings) throws IOException {
        if (settings.rawMode())
            return outputRaw(dos);
        else
            return outputBOD(dos);
    }
}
