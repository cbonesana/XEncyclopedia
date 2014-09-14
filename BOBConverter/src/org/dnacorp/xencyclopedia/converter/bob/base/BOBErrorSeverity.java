package org.dnacorp.xencyclopedia.converter.bob.base;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:55.
 */
public enum BOBErrorSeverity {
    s_warning(0),
    s_error(1);

    private final int value;
    private BOBErrorSeverity(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
