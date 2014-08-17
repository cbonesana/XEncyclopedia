package org.dnacorp.xencyclopedia.extractor;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 21:16
 */
public class PCKFileInputStream extends FileInputStream {

    private int counter = 0;
    private int magic;

    public PCKFileInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public int read() throws IOException {
        if (counter == 0) {
            magic = super.read() ^ 0xC8;
            counter++;
        }
        return super.read() ^ magic;
    }
}
