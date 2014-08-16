package org.dnacorp.xencyclopedia.extractor.exception;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:29
 */
public interface X2FileDriverError {

    public static final int X2FD_E_MSG                   = -1;
    public static final int X2FD_E_OK                    =  0;
    public static final int X2FD_E_HANDLE                =  1;
    public static final int X2FD_E_SEEK                  =  2;
    public static final int X2FD_E_BAD_SEEK_SPEC         =  3;

    public static final int X2FD_E_FILE_ERROR            =   4;
    public static final int X2FD_E_FILE_ACCESS           =   5;
    public static final int X2FD_E_FILE_EXIST            =   6;
    public static final int X2FD_E_FILE_NOENTRY          =   7;

    public static final int X2FD_E_FILE_BADMODE          =   8;
    public static final int X2FD_E_FILE_BADDATA          =   9;
    public static final int X2FD_E_FILE_EMPTY            =  10;
    public static final int X2FD_E_FILE_LOCKED           =  11;

    public static final int X2FD_E_CAT_NOTOPEN           =  12;
    public static final int X2FD_E_CAT_NOENTRY           =  13;
    public static final int X2FD_E_CAT_NODAT             =  14;
    public static final int X2FD_E_CAT_INVALIDSIZE       =  15;
    public static final int X2FD_E_CAT_FILESLOCKED       =  16;
    public static final int X2FD_E_CAT_INVALIDFILENAME   =  17;

    public static final int X2FD_E_ERROR                 =  18;
    public static final int X2FD_E_MALLOC                =  19;
    public static final int X2FD_E_BAD_FLAGS             =  20;

    // GZ errors
    public static final int X2FD_GZ_BEGIN                =  21;

    public static final int X2FD_E_GZ_FLAGS              =  X2FD_GZ_BEGIN;     // reserved bits are set
    public static final int X2FD_E_GZ_HEADER             =  X2FD_GZ_BEGIN + 1; // no gzip header
    public static final int X2FD_E_GZ_TOOSMALL           =  X2FD_GZ_BEGIN + 2; // input stream is too small
    public static final int X2FD_E_GZ_CRC                =  X2FD_GZ_BEGIN + 3; // data crc does not match
    public static final int X2FD_E_GZ_HCRC               =  X2FD_GZ_BEGIN + 4; // header crc does not match
    public static final int X2FD_E_GZ_COMPRESSION        =  X2FD_GZ_BEGIN + 5; // unsupported compression
    public static final int X2FD_E_GZ_DEFLATE            =  X2FD_GZ_BEGIN + 6; // deflate failed
}
