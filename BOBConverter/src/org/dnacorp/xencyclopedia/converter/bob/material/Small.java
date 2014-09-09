package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:12.
 */
public class Small {

    public String textureFile = null;
    public RGB ambient;
    public RGB diffuse;
    public RGB specular;
    public int transparency = 0;
    public short selfIllumination = 0;
    public Pair shininess;
    public boolean destinationBlend = false;
    public boolean twoSided = false;
    public boolean wireframe = false;
    public short textureValue;
    public PairTexture enviromentMap;
    public PairTexture bumpMap;
    public PairTexture lightMap;
    public PairTexture map4;
    public PairTexture map5;

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toPlainFile(FileOutputStream fos) throws IOException {
        // TODO
    }

}
