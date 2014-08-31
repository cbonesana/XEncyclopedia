package org.dnacorp.xencyclopedia.files;

import org.apache.commons.io.IOUtils;
import org.dnacorp.xencyclopedia.extractor.XFDFlag;
import org.dnacorp.xencyclopedia.extractor.cat.CATInputStreamReader;
import org.dnacorp.xencyclopedia.extractor.cat.XCATEntry;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static java.nio.file.StandardOpenOption.READ;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public class XFile {

    protected String positionCATName;
    protected String positionDATName;

    private File DATFile;
    private File CATFile;

    protected List<XCATEntry> xCATEntryList = new ArrayList<>();

    /**
     * @param archiveName
     */
    public XFile(String archiveName) throws XFileDriverException {
        if (archiveName.endsWith(".cat") || archiveName.endsWith(".dat"))
            archiveName = archiveName.substring(0,archiveName.indexOf("."));
        this.positionCATName = archiveName + ".cat";
        this.positionDATName = archiveName + ".dat";

        initFiles();
        try {
            readCAT();
        } catch (IOException e) {
            throw new XFileDriverException("IO Error: " + e.getMessage(), XFileDriverError.XFD_E_FILE_ERROR);
        }
    }

    private void initFiles() throws XFileDriverException {
        DATFile = new File(positionDATName);
        CATFile = new File(positionCATName);

        if (!CATFile.exists())
            throw new XFileDriverException("File " + positionCATName + " doesn't exist.", XFileDriverError.XFD_E_FILE_EXIST);
        if (!DATFile.exists())
            throw new XFileDriverException("File " + positionDATName + " doesn't exist.", XFileDriverError.XFD_E_FILE_EXIST);
        if (!CATFile.isFile())
            throw new XFileDriverException("File " + positionCATName + " is not a valid file.", XFileDriverError.XFD_E_FILE_INVALID);
        if (!DATFile.isFile())
            throw new XFileDriverException("File " + positionDATName + " is not a valid file.", XFileDriverError.XFD_E_FILE_INVALID);
        if (!CATFile.canRead())
            throw new XFileDriverException("File " + positionCATName + " can not be read.", XFileDriverError.XFD_E_FILE_ACCESS);
        if (!DATFile.canRead())
            throw new XFileDriverException("File " + positionDATName + " can not be read.", XFileDriverError.XFD_E_FILE_ACCESS);
        if (CATFile.length() == 0)
            throw new XFileDriverException("File " + positionCATName + " is empty.", XFileDriverError.XFD_E_FILE_EMPTY);
        if (DATFile.length() == 0)
            throw new XFileDriverException("File " + positionDATName + " is empty.", XFileDriverError.XFD_E_FILE_EMPTY);
    }

    private void readCAT() throws XFileDriverException, IOException {
        CATInputStreamReader cisr = new CATInputStreamReader(CATFile);
        int c;
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int offset = 0;
        while ((c = cisr.read()) != -1){
            if (c == 0)                         // skip zero charachers
                continue;
            if (c == 10 || c == 13) {           // read line each \n or \r
                if (first) {                    // avoid first line
                    first = false;
                    sb.setLength(0);
                    continue;
                }
                String line = sb.toString();
                int last = line.lastIndexOf(" ");
                if (last == -1)
                    throw new XFileDriverException("CAT file " + positionCATName + " contains invalid lines.", XFileDriverError.XFD_E_FILE_INVALID);
                long entrySize = Long.parseLong(line.substring(last+1));

                XCATEntry entry = new XCATEntry();    // create a new X2CATEntry
                entry.setParent(this);
                entry.setFilePath(line.substring(0,last));
                entry.setOffset(offset);
                entry.setSize(entrySize);
                xCATEntryList.add(entry);

                offset += entrySize;
                sb.setLength(0);                     // clean StringBuilder
            } else {
                sb.append((char)c);
            }
        }
        cisr.close();

        if (xCATEntryList.isEmpty())
            throw new XFileDriverException("CAT entry list is empty.", XFileDriverError.XFD_E_FILE_EMPTY);
    }

    public List<XCATEntry> getEntryList() {
        return xCATEntryList;
    }

    public XDATEntry readDATEntry(XCATEntry entry) throws XFileDriverException {
        if (entry.getParent() != this)
            throw new XFileDriverException("Entry " + entry + " doesn't belong to the entry list.", XFileDriverError.XFD_E_CAT_INVALIDFILENAME);

        XDATEntry xDATEntry = new XDATEntry(entry);

        try {
            Path filePath = DATFile.toPath();
            SeekableByteChannel sbc = Files.newByteChannel(filePath, EnumSet.of(READ));

            // this is safe because we don't have compressed file of more than 2GB
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)entry.getSize());

            sbc.position(entry.getOffset());
            int read = sbc.read(byteBuffer);
            sbc.close();

            if (read != byteBuffer.capacity())
                throw new XFileDriverException("Read " + read  + "byte, expected " + byteBuffer.capacity() + ".", XFileDriverError.XFD_E_ERROR);
            if (read == 0)
                throw new XFileDriverException("Decompression of an empty file.", XFileDriverError.XFD_E_FILE_EMPTY);

            XFDFlag flag = XFDFlag.FILETYPE_PLAIN;
            if (entry.getFilePath().endsWith(".pck"))
                flag = XFDFlag.FILETYPE_PCK;
            xDATEntry.setBuffer(decompressBuffer(byteBuffer, flag));

        } catch (FileNotFoundException e) {
            throw new XFileDriverException("File " + positionDATName + " not found.", XFileDriverError.XFD_E_FILE_EXIST);
        } catch (IOException e) {
            throw new XFileDriverException("Can't read entry in dat file.", XFileDriverError.XFD_E_FILE_ERROR);
        }

        return xDATEntry;
    }

    public static ByteBuffer decompressBuffer(ByteBuffer data_in, XFDFlag compressionMethod) throws XFileDriverException {
        byte[] decompressed = data_in.array();
        byte[] data;

        if (compressionMethod == XFDFlag.FILETYPE_PCK){
            int magic = (byte)(decompressed[0] ^ 0xC8);
            System.out.println(decompressed[0] + " -> " + magic);
            data = new byte[decompressed.length-1];
            for(int i=0; i<decompressed.length-1; i++)
                data[i] = (byte)(decompressed[i+1] ^ magic);
        } else {
            data = decompressed;
        }

        try {
            GZIPInputStream gZis = new GZIPInputStream(new ByteArrayInputStream(data));
            ByteArrayOutputStream boos = new ByteArrayOutputStream();
            IOUtils.copy(gZis, boos);
            return ByteBuffer.wrap(boos.toByteArray());
        } catch (IOException e) {
            throw new XFileDriverException("Error decompressing with GZip", XFileDriverError.XFD_E_GZ_COMPRESSION);
        }
    }

    public static ByteBuffer compressBuffer(ByteBuffer data_in, XFDFlag compressionType) throws XFileDriverException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try{
            GZIPOutputStream gZos = new GZIPOutputStream(byteArrayOutputStream);
            gZos.write(data_in.array());
            gZos.close();
        } catch(IOException e){
            throw new XFileDriverException("Error compressing with GZip", XFileDriverError.XFD_E_GZ_COMPRESSION);
        }

        byte[] compressed = byteArrayOutputStream.toByteArray();
        byte[] data_out = new byte[compressed.length+1];

        if (compressionType == XFDFlag.FILETYPE_PCK) {
            int m = (byte)System.nanoTime();
            byte magic = (byte)(m ^ 0xC8);
            System.out.println(m + " -> " + magic);
            data_out[0] = magic;
            for (int i=1; i<data_out.length; i++)
                data_out[i] = (byte)(compressed[i-1] ^ magic);
        } else {
            data_out = compressed;
        }

        return ByteBuffer.wrap(data_out);
    }
}
