package org.dnacorp.xencyclopedia.converter.bob.dom;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 12.09.2014 18:52.
 */
public enum ErrorFacility {
    F_BOBLoader(0),
    F_StatLoader(1);

    private final int value;
    private ErrorFacility(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
