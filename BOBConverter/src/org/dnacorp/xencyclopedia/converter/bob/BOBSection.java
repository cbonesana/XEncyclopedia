package org.dnacorp.xencyclopedia.converter.bob;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:39.
 */
public class BOBSection {

    public int peek(FileInputStream fis) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.mark(4);
        int i = bis.read();
        bis.reset();
        return i;
    }
}