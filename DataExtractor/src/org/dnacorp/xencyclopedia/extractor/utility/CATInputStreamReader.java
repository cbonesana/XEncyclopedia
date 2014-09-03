package org.dnacorp.xencyclopedia.extractor.utility;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 16:26.
 */
public class CATInputStreamReader extends DecryptedInputStreamReader {
    private int[] magic = {0xDB, 0xDC, 0xDD, 0xDE, 0xDF};
    protected long counter = 0;

    public CATInputStreamReader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    protected int decrypt(int c) {
        int j = (int)(counter % 5);
        //buffer[i] = (byte)((int)buffer[i] ^ magic[j]);
        int v = c ^ magic[j];
        magic[j] = (magic[j] + 5) % 256;    // because we don't have unsigned types
        counter++;
        return v;
    }
}
