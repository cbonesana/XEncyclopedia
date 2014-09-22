package org.dnacorp.xencyclopedia.converter.bob.cut;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 22:12.
 */
public class BOBNote {

    public int value = 0;
    public String text;
    public BOBErrorCodes errorCode = BOBErrorCodes.e_noError;

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
