package org.dnacorp.xencyclopedia.extractor.utility;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 16:17.
 */
public abstract class DecryptedInputStreamReader extends FileInputStream {

    public DecryptedInputStreamReader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public int read() throws IOException {
        int v = super.read();
        if (v == -1)
            return -1;
        return decrypt(v);
    }

    protected abstract int decrypt(int c);
}
