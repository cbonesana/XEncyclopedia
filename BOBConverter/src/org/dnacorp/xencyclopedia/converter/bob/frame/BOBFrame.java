package org.dnacorp.xencyclopedia.converter.bob.frame;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBWithErrors;
import org.dnacorp.xencyclopedia.converter.bob.material.RGB;
import org.dnacorp.xencyclopedia.converter.bob.vertex.Position3D;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 21:49.
 */
public class BOBFrame extends BOBWithErrors {

    public static final int CUT_F_LINEAR         = 0x1;
    public static final int CUT_F_ROT            = 0x2;
    public static final int CUT_F_TARGETPOS      = 0x8;
    public static final int CUT_F_SAMEPOS        = 0x10;
    public static final int CUT_F_SAMEROT        = 0x20;
    public static final int CUT_F_SAMETARGET     = 0x40;
    public static final int CUT_F_BEZIER         = 0x80;
    public static final int CUT_F_SAMESCALE      = 0x100;
    public static final int CUT_F_COLOR          = 0x200;
    public static final int CUT_F_SAMECOLOR      = 0x400;
    public static final int CUT_F_FOV            = 0x800;
    public static final int CUT_F_SAMEFOV        = 0x1000;
    public static final int CUT_F_ABSROT         = 0x2000;
    public static final int CUT_F_POSTCBINFO     = 0x4000;
    public static final int CUT_F_ROTTCBINFO     = 0x8000;
    public static final int CUT_F_POSBEZINFO     = 0x10000;
    public static final int CUT_F_TPOSTCBINFO    = 0x20000;
    public static final int CUT_F_FAKEROTTCBINFO = 0x40000;

    public static int flagMask = CUT_F_LINEAR|CUT_F_ROT|CUT_F_TARGETPOS|CUT_F_SAMEPOS|CUT_F_SAMEROT|CUT_F_SAMETARGET|CUT_F_BEZIER|CUT_F_SAMESCALE|CUT_F_COLOR|CUT_F_SAMECOLOR|CUT_F_FOV|CUT_F_SAMEFOV|CUT_F_ABSROT|CUT_F_POSTCBINFO|CUT_F_ROTTCBINFO|CUT_F_TPOSTCBINFO|CUT_F_FAKEROTTCBINFO;

    public int flags = 0;
    public Position3D position;
    public AngleAxis rotation;
    public Position3D targetPos;
    public double rollAngle = 0.0;
    public TCBInfo pos_tcb_info;
    public TCBInfo rot_tcb_info;
    public TCBInfo tpos_tcb_info;
    public double fov = 0.0;
    public RGB color;
    public int length = 0;
    public int index = 0;

    public int valueCount() {
        // TODO
        return 0;
    }

    public void load(DataInputStream dis) {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos) {
        // TODO
    }

    public void toTextFile(DataOutputStream dos) {
        // TODO
    }



}
