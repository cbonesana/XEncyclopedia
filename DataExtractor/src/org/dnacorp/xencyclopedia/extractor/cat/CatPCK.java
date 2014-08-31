package org.dnacorp.xencyclopedia.extractor.cat;

import org.apache.commons.io.IOUtils;
import org.dnacorp.xencyclopedia.extractor.XFDFlag;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 17.08.2014 21:01
 */
public class CatPCK {

    public static byte[] DecompressFile(File file, XFDFlag compressionMethod, long mtime) throws IOException, XFileDriverException {
        int size = (int)file.length();
        byte[] data = new byte[size];

        RandomAccessFile raf = new RandomAccessFile(file, "r");
        raf.read(data);
        raf.close();

        return DecompressBuffer(data, size, compressionMethod);
    }

    public static byte[] DecompressBuffer(byte[] in_data, int in_size, XFDFlag compressionMethod) throws XFileDriverException {
        if(in_size <= 0)
            throw new XFileDriverException("Decompression of an empty file.", XFileDriverError.XFD_E_FILE_EMPTY);

        int magic = in_data[0] ^ 0xC8;
        byte[] data;

        if(compressionMethod == XFDFlag.FILETYPE_PCK){
            data = new byte[in_data.length-1];
            for(int i=0; i<in_size-1; i++)
                data[i] = (byte)(in_data[i+1] ^ magic);
        } else {
            data = in_data;
        }

        try {
            GZIPInputStream gZis = new GZIPInputStream(new ByteArrayInputStream(data));
            ByteArrayOutputStream boos = new ByteArrayOutputStream();
            IOUtils.copy(gZis, boos);
            return boos.toByteArray();
        } catch (IOException e) {
            throw new XFileDriverException("Error decompressing with GZip", XFileDriverError.XFD_E_GZ_COMPRESSION);
        }
    }

    public static byte[] CompressBuffer(byte[] buffer, XFDFlag compressionType) throws XFileDriverException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(buffer);
            gzipOutputStream.close();
        } catch(IOException e){
            throw new XFileDriverException("Error compressing with GZip", XFileDriverError.XFD_E_GZ_COMPRESSION);
        }
        byte[] compressed = byteArrayOutputStream.toByteArray();
        byte[] out = new byte[compressed.length+1];

        if (compressionType == XFDFlag.FILETYPE_PCK) {
            int m = (int)System.nanoTime();
            byte magic = (byte)(m ^ 0xC8);
            out[0] = magic;
            for (int i=1; i<out.length; i++)
                out[i] = (byte)(compressed[i-1] ^ magic);
        }

        return out;
    }

    public static XFDFlag GetBufferCompressionType(byte[] data, int size){
        if(size >= 3){
            byte magic = (byte)(data[0] ^ 0xC8);
            if((data[1] ^ magic) == 0x1F && (data[2] ^ magic)==0x8B)
                return XFDFlag.FILETYPE_PCK;
        }
        if(size >= 2 && (data[0] == 0x1F && data[1] == 0x8B))
            return XFDFlag.FILETYPE_DEFLATE;

        return XFDFlag.FILETYPE_PLAIN;
    }
}
