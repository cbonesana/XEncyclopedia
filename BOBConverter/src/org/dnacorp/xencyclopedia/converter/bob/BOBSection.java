package org.dnacorp.xencyclopedia.converter.bob;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBWithErrors;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:39.
 */
public class BOBSection extends BOBWithErrors {

    public static int peek(DataInputStream dis) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(dis);
        bis.mark(4);
        int i = bis.read();
        bis.reset();
        return i;
    }
}
