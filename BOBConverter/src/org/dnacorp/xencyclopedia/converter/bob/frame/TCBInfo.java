package org.dnacorp.xencyclopedia.converter.bob.frame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 22:02.
 */
public class TCBInfo {

    public double tension    = 0;
    public double continuity = 0;
    public double bias       = 0;
    public double easeFrom   = 0;
    public double easeTo     = 0;

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
