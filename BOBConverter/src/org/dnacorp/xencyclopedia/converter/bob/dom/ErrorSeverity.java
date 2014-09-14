package org.dnacorp.xencyclopedia.converter.bob.dom;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 12.09.2014 18:51.
 */
public enum ErrorSeverity {
    S_OK(0),
    S_Warning(1),
    S_Error(2);

    private final int value;
    private ErrorSeverity(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
