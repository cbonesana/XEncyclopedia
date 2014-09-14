package org.dnacorp.xencyclopedia.converter.bob.base;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:54.
 */
public enum BOBErrorCodes {
    e_noError(0),
    e_badHeader(1),
    e_badEndHeader(2),
    e_notEnoughData(3),
    e_moreData(4),
    e_unkPointFlags(5),
    e_error(6),
    e_format_noStatFormat(7),
    e_format_notEnoughData(8),
    e_format_UserWarning(9),
    e_pointNotFound(10),
    e_badVersion(11),
    e_unsupportedFrameFlags(12),
    e_unkMaterialValueType(13);

    private final int value;
    private BOBErrorCodes(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
