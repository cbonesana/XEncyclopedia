package org.dnacorp.xencyclopedia.extractor.dat;

import org.dnacorp.xencyclopedia.extractor.utils.DecryptedInputStreamReader;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 16:27.
 */
public class DATInputStreamReader extends DecryptedInputStreamReader {

    public DATInputStreamReader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    protected int decrypt(int c) {
        return c ^ 0x33;
    }
}
