package org.dnacorp.xencyclopedia.extractor.cat;

import org.apache.commons.io.IOUtils;
import org.dnacorp.xencyclopedia.extractor.X2FDFlag;
import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.X2FileDriverException;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 21:01
 */
public class CatPCK {

    public static byte[] DecompressFile(File file, X2FDFlag compressionMethod, long mtime) throws IOException, X2FileDriverException {
        int size = (int)file.length();
        byte[] data = new byte[size];

        RandomAccessFile raf = new RandomAccessFile(file, "r");
        raf.read(data);
        raf.close();

        return DecompressBuffer(data, size, compressionMethod);
    }

    public static byte[] DecompressBuffer(byte[] in_data, int in_size, X2FDFlag compressionMethod) throws X2FileDriverException {
        if(in_size <= 0)
            throw new X2FileDriverException("Decompression of an empty file.", X2FileDriverError.X2FD_E_FILE_EMPTY);

        int magic = in_data[0] ^ 0xC8;
        byte[] data = null;

        if(compressionMethod == X2FDFlag.FILETYPE_PCK){
            data = new byte[in_data.length-1];
            for(int i=0; i<in_size-1; i++)
                data[i] = (byte)(in_data[i+1] ^ magic);
        } else
            data = in_data;

        try {
            GZIPInputStream gZis = new GZIPInputStream(new ByteArrayInputStream(data));
            ByteArrayOutputStream boos = new ByteArrayOutputStream();
            IOUtils.copy(gZis, boos);
            return boos.toByteArray();
        } catch (IOException e) {
            throw new X2FileDriverException("Error decompressing with GZip", X2FileDriverError.X2FD_E_GZ_COMPRESSION);
        }
    }
}
