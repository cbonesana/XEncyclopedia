package org.dnacorp.xencyclopedia.converter.bob.dom;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 12.09.2014 18:51.
 */
public enum ErrorCode {
    // BOB converter
    E_BadHeader(0),
    E_BadEndHeader(1),
    E_Error(2),
    E_NoError(3),
    E_MoreData(4),
    E_NotEnoughData(5),
    E_NoStatFormat(6),
    E_FormatUserWarning(7),
    E_UnkPointFlags(8),
    E_PointNotFound(9),
    E_BadVersion(10);

    private final int value;
    private ErrorCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
