package org.dnacorp.xencyclopedia.converter.bob.body;

import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBError;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:15.
 */
public class BOBBodies extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_BODY_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_BODY_END;

    public List<BOBBody> bodyList = new ArrayList<>();

    public boolean load(DataInputStream dis) throws IOException {
        int hdr = dis.readInt();
        if (hdr != HDR_BEGIN) {
            error(BOBErrorCodes.e_badHeader);
            return false;
        }

        short bodyCount = dis.readShort();

        BOBBody ch;
        for (int i = 0; i < bodyCount; i++) {
            ch = createChild();
            if (!ch.load(dis)) {
                for (BOBError e : ch.errors)
                    error(e.severity, e.code, "Body[%d]->%s", "" +i, e.text);
                return false;
            }
        }

        hdr = dis.readInt();
        if (hdr != HDR_END)
            error(BOBErrorCodes.e_badEndHeader);
        return hdr != HDR_END;
    }

    private BOBBody createChild() {
        BOBBody body = new BOBBody();
        bodyList.add(body);
        return body;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeInt(HDR_BEGIN);
        dos.writeShort((short)bodyList.size());
        for (BOBBody it : bodyList) {
            it.toBinaryFile(dos);
        }
        dos.writeInt(HDR_END);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos, Settings settings, BOBMaterials materials) throws IOException {
        int i = 1;
        dos.writeChars("// beginning of bodies (" + bodyList.size() + ")\n");
        for (BOBBody it : bodyList) {
            if (!it.toTextFile(dos, settings, materials, i)) {
                for (BOBError e : errors) {
                    error(e.severity, e.code, "Body[%d]->%s", "" +i, e.text);
                }
                return false;
            }
            dos.writeChars("\n");
        }
        dos.writeChars("// end of bodies \n");
        return true;
    }
}
