package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:44.
 */
public class BOBMaterial {

    public enum MaterialType {
        mat1(1), mat3(3), mat5(5), mat6(6);

        private final int value;
        private MaterialType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public BOBErrorCodes errorCode;

    public MaterialType type = null;
    public short index       = 0;

    public boolean load(DataInputStream dis) throws IOException {
        // TODO
        return true;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        // TODO
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        // TODO
        return true;
    }
}
