package org.dnacorp.xencyclopedia.extractor.cat;

import org.dnacorp.xencyclopedia.extractor.X2FDFlag;
import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverException;

import java.io.File;
import java.util.zip.GZIPInputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 21:01
 */
public class CatPCK {

    public static int DecompressFile(File file, X2FDFlag compressionMethod, char[] out_data, long out_size, long mtime) {
        int nRes;
        int size = (int)file.length();
        char[] data = new char[size];



        file.read(data, size);
        nRes = DecompressBuffer(data, size, compressionMethod, out_data, out_size, mtime);
        delete[] data;
        return nRes;
    }

    public static int DecompressBuffer(char[] in_data, long in_size, X2FDFlag compressionMethod, char[] out_data, long out_size, long mtime) throws X2FileDriverException {
        if(in_size <= 0)
            throw new X2FileDriverException("Decompression of an empty file.", X2FileDriverError.X2FD_E_FILE_EMPTY);

        int magic = in_data[0] ^ 0xC8;
        int nRes=0;
        int offset = 0;

        if(compressionMethod == X2FDFlag.FILETYPE_PCK){
            offset = 1;
            for(int i=0; i<in_size; i++){
                in_data[i] ^= magic;
            }
        }

        GZIPInputStream gzis = new GZIPInputStream();

        gzreader gz;
        bool bRes=gz.unpack(in_data + offset, (size_t)in_size - offset);
        nRes = TranslateGZError(gz.error()); // set the error regardless of the return value

        if(bRes==false){
            gz.flush();
            *out_data=NULL;
            *out_size=0;
            *mtime=0;
        }
        else{
            *out_data=gz.outdata();
            *out_size=gz.outsize();
            *mtime=gz.mtime();
        }
        return nRes;
    }


}
