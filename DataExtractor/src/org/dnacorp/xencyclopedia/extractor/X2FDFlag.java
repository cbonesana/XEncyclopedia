package org.dnacorp.xencyclopedia.extractor;


/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public enum X2FDFlag {

    // open mode
    READ(0),
    WRITE(1),

    // create disposition
    OPEN_EXISTING(0),
    CREATE_NEW(1),
    CONVERT(2),

    // seek modes
    SEEK_SET(0),
    SEEK_END(1),
    SEEK_CUR(2),

    // open flags
    FILETYPE_AUTO(0),
    FILETYPE_PLAIN(1),
    FILETYPE_PCK(2),
    FILETYPE_DEFLATE(3);

    private int value;

    private X2FDFlag(int value) {
        this.value = value;
    }

    public int and(X2FDFlag flag) {
        return this.value & flag.value;
    }

    public int value() {
        return value;
    }

}
