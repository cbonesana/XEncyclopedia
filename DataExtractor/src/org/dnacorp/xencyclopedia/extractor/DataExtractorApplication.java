package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;
import org.dnacorp.xencyclopedia.extractor.exception.XPathException;
import org.dnacorp.xencyclopedia.files.XFile;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public class DataExtractorApplication {

    public static void main(String[] args) {

        DataExtractorApplication dea = new DataExtractorApplication();

        String PCK_FILE_READ  = "new_pack.pck";
        String PCK_FILE_WRITE = "new_pack.pck";
        String TXT_FILE_READ  = "new_txt.txt";
        String TXT_FILE_WRITE = "new_txt.txt";

        String CAT_FILE           = "test_cat.getCat";
        String CAT_PCK_FILE_READ  = CAT_FILE + "::new_pack.pck";
        String CAT_PCK_FILE_WRITE = CAT_FILE + "::new_pack.pck";
        String CAT_TXT_FILE_READ  = CAT_FILE + "::some_dir\\new_text.txt";
        String CAT_TXT_FILE_WRITE = CAT_FILE + "::some_dir\\new_text.txt";

        dea.pckRead(PCK_FILE_READ);
        dea.pckWrite(CAT_PCK_FILE_WRITE);

        dea.txtRead(TXT_FILE_READ);
        dea.txtWrite(CAT_TXT_FILE_WRITE);

        dea.catCreate(CAT_FILE);
        dea.catEnum(CAT_FILE);
        dea.catPckRead(CAT_PCK_FILE_READ);
        dea.catPckWrite(CAT_PCK_FILE_WRITE);

        dea.catTxtRead(CAT_TXT_FILE_READ);
        dea.catTxtWrite(CAT_TXT_FILE_WRITE);
        dea.catEnum(CAT_FILE);
    }

    private void pckRead(String pck) {
        try {
            myRead(pck);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void pckWrite(String pck) {
        try {
            myWrite(pck, true);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void txtRead(String txt) {
        try {
            myRead(txt);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void txtWrite(String txt) {
        try {
            myWrite(txt, false);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void catCreate(String catFile) {
        XFileDriver X2FD = new XFileDriver();
        System.out.println();
        System.out.println("************");
        System.out.println("Opening/creating catalog file " + catFile);
        System.out.println("************\n");
        XCatalog cat=X2FD.OpenCatalog(catFile, XFDFlag.CREATE_NEW);
        X2FD.CloseCatalog(cat);
    }

    private void catPckRead(String pck) {
        try {
            myRead(pck);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void catPckWrite(String pck) {
        try {
            myWrite(pck, true);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void catTxtRead(String txt) {
        try {
            myRead(txt);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void catTxtWrite(String txt) {
        try {
            myWrite(txt, false);
        } catch (XFileDriverException | XPathException e) {
            e.printStackTrace();
        }
    }

    private void catEnum(String catFile) {
        XFileDriver X2FD = new XFileDriver();
        System.out.println();
        System.out.println("************");
        System.out.println("Listing content of catalog");
        System.out.println("************");
        System.out.println("Listing content of catalog " + catFile + "...");
        XCatalog cat=X2FD.OpenCatalog(catFile, XFDFlag.OPEN_EXISTING);

        XCatFileInfo info = new XCatFileInfo();
        // TODO: this is an iterator!
        XFind hFind=X2FD.CatFindFirstFile(cat, "*", info);
        if(hFind != null){
            do{
                System.out.println("\t" + info.szFileName);
            } // TODO: this is a .next()
            while(X2FD.CatFindNextFile(hFind, info));
        }
        X2FD.CatFindClose(hFind);
        X2FD.CloseCatalog(cat);
        System.out.println("End of list.\n");
    }

    private void myRead(String filename) throws XFileDriverException, XPathException {
        XFileDriver X2FD = new XFileDriver();
        // open the file
        System.out.println();
        System.out.println("************");
        System.out.println("* Reading file " + filename);
        System.out.println("************");
        System.out.println("Opening file " + filename);
        XFile file = X2FD.XFDOpenFile(
                filename,           // file name
                XFDFlag.READ,          // open for reading
                XFDFlag.OPEN_EXISTING, // don't create new if it doesn't exist
                XFDFlag.FILETYPE_AUTO  // automatically get the file type (PCK/plain)
        );

        // get the fileinfo so we can tell if it is a PCK or plain file
        XFileInfo fi;
        fi = X2FD.FileStatByHandle(file);

        System.out.println("File type is " + /*(fi.flags & X2FD_FI_PLAIN)*/ (fi.isPlain() ? "PLAIN" : "PCK"));

        // get file getSize
        int size= (int) X2FD.XFDFileSize(file);
        System.out.println("File getSize is " + size + " bytes");
        // read the getData
        long data = X2FD.ReadFile(file,null);
        System.out.println("File getData:\n" + data);

        // eof should be TRUE now
        boolean eof=X2FD.EOF(file);
        System.out.println("EOF = " + eof);

        // we will now seek to the begining of file and read 10 bytes two times
        System.out.println("Seeking to begining\n");
        X2FD.SeekFile(file, 0, XFDFlag.SEEK_SET);

        // eof is false this time
        eof=X2FD.EOF(file);
        System.out.println("EOF = " + eof);

        // read the first 10 bytes
        long buff;
        buff = X2FD.ReadFile(file, null);
        System.out.println("First 10 bytes:\n" + buff);
        // read the second 10 bytes
        buff = X2FD.ReadFile(file, null);
        System.out.println("Second 10 bytes:\n" + buff);

        // cleanUp the file
        boolean ret=X2FD.CloseFile(file);
        System.out.println("Close file returned: " + ret);
    }

    private void myWrite(String filename, boolean bPCK) throws XFileDriverException, XPathException {
        XFileDriver X2FD = new XFileDriver();

        System.out.println();
        System.out.println("************");
        System.out.println("* Writing to file " + filename);
        System.out.println("************");
        System.out.println("Opening file " + filename);
        // open the file
        XFile file=X2FD.XFDOpenFile(filename, // file name
                XFDFlag.WRITE, // open for read/write
                XFDFlag.CREATE_NEW, // create new if it doesn't exist
                bPCK ? XFDFlag.FILETYPE_PCK : XFDFlag.FILETYPE_PLAIN
        );

        // get the file getSize
        int size= (int) X2FD.XFDFileSize(file);

        // if getSize is  > 0 then the file already exists and contains something
        // we will display it
        if(size > 0){
            long buff = X2FD.ReadFile(file, null);
            System.out.println("Old getData:\n" + buff);
        }

        // I would write 'hallo word' but we are in space sim...
        String text="hallo universe!";
        String text2="\nHow are you today?";

        // We must seek to begining because if we called X2FD_ReadFile in above condition
        // if(getSize)... the file pointer is at end
        X2FD.SeekFile(file, 0, XFDFlag.SEEK_SET);

        System.out.println("Writing getData");
        // write the text
        X2FD.WriteFile(file, null);
        // write the second line
        X2FD.WriteFile(file, null);

        // Always call this prior to cleanUp if writing to file
        // This is to ensure that there will be no garbage beyond your getData (as the file
        // can be actually bigger than the getData you are writing now). Of course if you are using
        // random access writing you must first seek to the correct end offset.
        X2FD.SetEndOfFile(file);

        // cleanUp the file
        System.out.println("Closing file\n");
        X2FD.CloseFile(file);

        // read the file content
        myRead(filename);
    }

}
