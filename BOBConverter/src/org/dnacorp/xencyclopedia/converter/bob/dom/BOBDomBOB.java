package org.dnacorp.xencyclopedia.converter.bob.dom;

import org.dnacorp.xencyclopedia.converter.bob.BOBInfo;
import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBError;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;
import org.dnacorp.xencyclopedia.converter.bob.body.BOBBodies;
import org.dnacorp.xencyclopedia.converter.bob.material.BOBMaterials;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 20:59.
 */
public class BOBDomBOB extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_BOB_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_BOB_END;

    private Settings m_settings;

    public BOBInfo info           = new BOBInfo();
    public BOBMaterials materials = new BOBMaterials();
    public BOBBodies bodies       = new BOBBodies();

    public BOBDomBOB(Settings m_settings) {
        this.m_settings = m_settings;
    }

    public boolean load(DataInputStream dis) throws IOException {
        int hdr = dis.readInt();
        if (hdr != HDR_BEGIN) {
            error(BOBErrorCodes.e_badHeader);
            return false;
        }

        if (peek(dis) == BOBInfo.HDR_BEGIN) {
            if (!info.load(dis)) {
                for (BOBError it : errors)
                    error(it.code, "info: %s", it.text);
                return false;
            }
        }

        if (!materials.load(dis)) {
            for (BOBError it : errors)
                error(it.code, "materials->%s", it.text);
            return false;
        }

        if (!bodies.load(dis)) {
            for (BOBError it : errors)
                error(it.code, "bodies->%s", it.text);
            return false;
        }

        hdr = dis.readInt();

        if (hdr != HDR_END)
            error(BOBErrorCodes.e_badEndHeader);

        return hdr==HDR_END;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.write(HDR_BEGIN);
        info.toBinaryFile(dos);
        materials.toBinaryFile(dos);
        bodies.toBinaryFile(dos);
        dos.write(HDR_END);
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        info.toTextFile(dos);
        dos.writeChars("\n");
        materials.toTextFile(dos);
        if (materials.size() > 0)
            dos.writeChars("\n");
        if (!bodies.toTextFile(dos, m_settings, materials)) {
            for (BOBError it : errors)
                error(it.severity, it.code, "Bodies: %s", it.text);
            return false;
        }
        return true;
    }
}
