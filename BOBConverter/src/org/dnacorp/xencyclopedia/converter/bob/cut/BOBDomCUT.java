package org.dnacorp.xencyclopedia.converter.bob.cut;

import org.dnacorp.xencyclopedia.converter.bob.BOBInfo;
import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:00.
 */
public class BOBDomCUT extends BOBSection {

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_CUT_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_CUT_END;
    public static final int supported_version = 6;

    private List<BOBPath> pathList = new ArrayList<>();

    private int m_storedPatCount;
    private Settings m_settings;

    public int version;
    public BOBInfo info;

    public BOBDomCUT(Settings settings) {
        this.m_settings = settings;
    }

    public boolean load(DataInputStream dis) {
        // TODO
        return false;
    }

    public boolean toBinaryFile(DataOutputStream dos) {
        // TODO
        return false;
    }

    public boolean toTextFile(DataOutputStream dos) {
        // TODO
        return false;
    }

    public BOBPath createChild() {
        BOBPath ch = new BOBPath(m_settings);
        pathList.add(ch);
        return ch;
    }

    public boolean convert(DataInputStream dis, DataOutputStream dos) {
        // TODO
        return false;
    }

}
