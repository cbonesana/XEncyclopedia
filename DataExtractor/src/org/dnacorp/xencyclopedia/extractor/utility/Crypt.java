package org.dnacorp.xencyclopedia.extractor.utility;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 14:48.
 */
public class Crypt {

    /* getSize must be multiple of 5! */
    public static void DecryptCAT(byte[] buffer, int size){
        int[] magic = {0xDB, 0xDC, 0xDD, 0xDE, 0xDF};

        for (int i=0; i<size; i++) {
            int j = i % 5;
            //buffer[i] = (byte)((int)buffer[i] ^ magic[j]);
            buffer[i] ^= magic[j];
            magic[j] = (magic[j] + 5) % 256;    // because we don't have unsigned types
        }
    }

    /**
     * although you can skip this function and will still be able to
     * unpack PCKs from DATs, you will fail to load nonpacked getData
     * don't ask me why...
     */
    public static void DecryptDAT(byte[] buffer, int size) {
        for (int i=0; i<size; i++)
            buffer[i] ^= 0x33;
    }
}
