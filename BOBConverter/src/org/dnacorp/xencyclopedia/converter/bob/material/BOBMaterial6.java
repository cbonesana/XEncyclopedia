package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:10.
 */
public class BOBMaterial6 extends BOBMaterial {

    public int flags   = 0;
    public Small small = null;
    public Big big     = null;

    public BOBMaterial6() {
        type = MaterialType.mat6;
    }

    public boolean load(DataInputStream dis) throws IOException {
        BOBErrorCodes errorCode = BOBErrorCodes.e_noError;
        index = dis.readShort();
        flags = dis.readInt();

        if (flags == Big.flag) {
            big = new Big();
            errorCode = big.load(dis);
        } else {
            small = new Small();
            errorCode = small.load(dis, flags);
        }

        return errorCode == BOBErrorCodes.e_noError;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        boolean bRes;

        dos.write(index);
        dos.write(flags);
        if (big != null)
            bRes = big.toBinaryFile(dos);
        else
            bRes = small.toBinaryFile(dos);
        return bRes;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars("MATERIAL6: " + index + ";" + "0x" + String.format("%x", flags));

        if(big != null)
            big.toTextFile(dos);
        else if(small != null)
            small.toTextFile(dos);
        return true;
    }
}
