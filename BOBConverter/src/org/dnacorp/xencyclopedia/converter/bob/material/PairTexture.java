package org.dnacorp.xencyclopedia.converter.bob.material;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:14.
 */
public class PairTexture {

    public String texture;
    public short strength;

    public PairTexture(PairTexture other) {
        texture = other.texture;
        strength = other.strength;
    }
}
