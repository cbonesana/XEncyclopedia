package org.dnacorp.xencyclopedia.extractor;

import org.dnacorp.xencyclopedia.extractor.cat.XCATBuffer;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverError;
import org.dnacorp.xencyclopedia.extractor.exception.XFileDriverException;
import org.dnacorp.xencyclopedia.extractor.exception.XPathException;
import org.dnacorp.xencyclopedia.extractor.utils.XPath;
import org.dnacorp.xencyclopedia.files.XFile;

import static org.dnacorp.xencyclopedia.extractor.XFDFlag.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public class XFileDriver {

    /**
     * Opens a file and return its handle. Works like WINAPI CreateFile function.
     * You can open file more that once for reading, but you cannot open file for
     * reading if is already opened for writing and you cannot opend it for writing
     * if it's already opened for reading.
     * Every handle opened with X2FD_OpenFile must be closed with call to
     * X2FD_CloseFile
     *
     * nFileType means how X2FD will treat the file:
     * AUTO means it will check automatically if it's a PCK
     * !!! Don't use this when creating new file!!!
     * It would be always created as PLAIN.
     *
     * PLAIN means that there is no translation and you can open even PCKs with it,
     * but you will read and write the raw binary getData.
     *
     * PCK means that the file is PCK. If file doesn't exist or it's 0 bytes long,
     * it will be created and treated as PCK. If file exists and contains getData, then
     * it must really be a PCK file.
     *
     * This function can open physical files from OS filesystem, trasparently open
     * PCK files and transparently open files within CAT archives.
     *
     * To open file within a CAT archive use this syntax:
     * <CAT file name>::<file inside CAT>
     *
     * Example: C:\\01.getCat::types\\TShips.pck will open file "types\TShips.pck"
     * from catalog "c:\01.getCat".
     *
     * File name is case insensitive.
     *
     * Files opened for reading are locked for writing, files opened for writing are
     * locked for both read and write, so no other process can access them.
     *
     * in: pszName - name of file, nAccess - desired access X2FD_READ, X2FD_WRITE
     *     nCreateDisposition - whether file should be created if it does not exist
     *     (X2FD_OPEN_EXISTING - file must exist, X2FD_CREATE_NEW - file is created
     *     if does not exist, otherwise it's opened)
     *     nFileType - type of file (X2FD_FILETYPE_AUTO, X2FD_FILETYPE_PLAIN,
     *                 X2FD_FILETYPE_PCK)
     * ret: handle of opened file or 0 on failure
     */
    public XFile XFDOpenFile(String pszName, XFDFlag nAccess, XFDFlag nCreateDisposition, XFDFlag nFileType) throws XFileDriverException, XPathException {

        if (
                (nAccess != READ && nAccess != WRITE) ||
                (nCreateDisposition != CREATE_NEW && nCreateDisposition != OPEN_EXISTING) ||
                (nFileType != FILETYPE_PCK && nFileType != FILETYPE_DEFLATE && nFileType != FILETYPE_PLAIN && nFileType != FILETYPE_AUTO) ||
                (nCreateDisposition == CREATE_NEW && nAccess == WRITE)
           )
            throw new XFileDriverException("Wrong flag.", XFileDriverError.XFD_E_BAD_FLAGS);

        XFile xFile;
//        if (pszName.contains("::"))
            xFile = XFDOpenFile(pszName, nAccess, nCreateDisposition, nFileType);
//        else
//            xFile = OpenFileCAT(pszName, nAccess, nCreateDisposition, nFileType);

        return xFile;
    }

    /**
     * Operates like OpenFile but opened file is converted to specified file type
     * File is always opened with X2FD_WRITE access
     *
     * in: pszName - name of file, nAccess - desired access X2FD_READ, X2FD_WRITE
     *     nCreateDisposition - whether file should be created if it does not exist
     *     (X2FD_OPEN_EXISTING, X2FD_CREATE_NEW)
     *     nFileType - type of file (X2FD_FILETYPE_AUTO, X2FD_FILETYPE_PLAIN,
     *                 X2FD_FILETYPE_PCK)
     *     nConvertToFileType - desired type of file (X2FD_FILETYPE_PLAIN,
     *                          X2FD_FILETYPE_PCK)
     * ret: handle of opened file or 0 on failure
     */
    public XFile XFDOpenFileConvert(String pszName, XFDFlag nCreateDisposition, XFDFlag nFileType, XFDFlag nConvertToFileType) throws XFileDriverException, XPathException {
        XFile xFile=XFDOpenFile(pszName, WRITE, nCreateDisposition, nFileType);
//        if(xFile != null){
//            try {
//                xFile.convert(nConvertToFileType);
//            } finally {
//                XFDClose(xFile);
//            }
//        }
        return xFile;
    }

    /**
     * Close handle from previous call to X2FD_OpenFile. Does not necessarily cleanUp
     * the physical file itself if it has been opened more than once.
     * If the file is opened for writing and it is PCK or it is opened from CAT,
     * then the getData will be saved to the file while the last reference to such file
     * is freed.
     *
     * in: hFile - handle to cleanUp
     * ret: non zero if file has been really closed and was saved ok, if the file
     *      was not closed or there was an error while saving, zero is returned
     *      TODO
     */
    public boolean CloseFile(XFile xFile) {
        return false;
    }

    /**
     * Return getSize of file's buffer. For files opened as PLAIN the returned getSize is
     * same as physical file getSize. For PCK files, it's the getSize of getData it PCK file
     * not the physical getSize!
     *
     * in: handle of file
     * ret: getSize of file or -1 on failure
     */
    public long XFDFileSize(XFile xFile) {
        long size = -1;
//        if (xFile != null)
//            size = xFile.getFileSize();
        return size;
    }

    /**
     * Check if file pointer is at the file end (beyond last readable position)
     *
     * in: handle of file
     * ret: non zero if EOF is true, 0 otherwise
     * TODO
     */
    public boolean EOF(XFile xFile) {
        return false;
    }

    /**
     * Works like C runtime function fread()
     * It will read up to <getSize> bytes from specified file and write them to
     * <buffer>.
     * If file is opened as PCK then the getData readed are also unpacked.
     * The reading will begin at location specified by internal file pointer and
     * this pointer will be shifted by number of bytes readed.
     *
     * in: hFile - file to read from, buffer - buffer to hold the readed getData
     *     getSize - number of bytes to read from file
     * ret: number of bytes readed or -1 on error
     */
    public long ReadFile(XFile hFile, byte[] buffer) {
        return 0;
    }

    /**
     * Works as C runtime function fseek()
     * Will set the internal file pointer at specified location.
     *
     * in: hFile - file to seek, offset - offset in bytes from origin
     *     origin - X2FD_SEEK_SET - begining of file, X2FD_SEEK_CUR - current
     *              position, X2FD_SEEK_END - end of file
     * ret: new offset or -1 on failure
     */
    public long SeekFile(XFile hFile, int offset, XFDFlag origin) {
        return 0;
    }

    /**
     * in: hFile - file
     * ret: internal pointer position or -1 on failure
     */
    public long FileTell(XFile hFile) {
        return 0;
    }

    /**
     * Works like WINAPI SetEndOfFile() function
     * Will enlarge/shrink the file getSize so its end will be at location specified by
     * internal file pointer.
     *
     * in: handle of file to change
     * ret: 0 on error, non zero on success
     */
    public boolean SetEndOfFile(XFile hFile) {
        return false;
    }

    /**
     * Works like C function fwrite()
     * Will write nCount bytes from pData to file, begining at location specified by
     * internal file pointer.
     *
     * This function will not write anything to physical file if it's PCK or it's
     * opneded from CAT archive.
     * Such file will be saved with last call to X2FD_CloseFile.
     *
     * in: hFile - file to write to, pData - getData to write, nCount- count of bytes
     *     to write
     * ret: number of bytes written or -1 on failure
     */
    public long WriteFile(XFile file, byte[] data) {
        return 0;
    }

    /**
     * Will return X2FILEINFO structure with information about file type
     * (PCK/PLAIN), getSize and modifiation time.
     *
     * in: name of file, pointer to structure to get the info
     * ret: zero on failure, non zero otherwise
     */
    public XFileInfo FileStat(String pszFileName) {
        return null;
    }

    /**
     * Will return X2FILEINFO structure with information about file type
     * (PCK/PLAIN), getSize and modifiation time
     *
     * in: handle to file, pointer to structure to get the info
     * ret: zero on failure, non zero otherwise
     */
    public XFileInfo FileStatByHandle(XFile file) {
        return null;
    }

    /**
     * Check if file exists. Use the same syntax as in X2FD_OpenFile to check file
     * within CAT.
     * Does not make difference if target is file or directory
     *
     * in: file name
     * ret: zero if file doesn't exist, non zero otherwise
     */
    public boolean FileExists(String pszFileName){
        return false;
    }

    /**
     * Returns the compression format used in file.
     * Test is based on reading GZIP/PCK header from file.
     *
     * in: name of file
     * ret: one of X2FD_FILETYPE_ constants (on error PLAIN is returned)
     */
    public XFDFlag GetFileCompressionType(String pszFileName) throws XPathException, XFileDriverException {
        String pszCat;
        String pszFile;
        XFDFlag nRes = XFDFlag.FILETYPE_PLAIN;

        XFile xFile = XPath.parseCATPath(pszFileName);

//        CATPath catPath = ParseCATPath(positionDATName);
//
//        if (pszCat == null) {
//            nRes = GetFileCompressionType(positionDATName);
//        } else {
//
//            X2CATBuffer getCat = _OpenCatalog();
//
//        }


        return nRes;
    }

    private XCATBuffer _OpenCatalog(String pszName, String nCreateDisposition) {
        XCATBuffer catBuffer;

        return null;
    }

    /**
     * Delete a file. It can be PCK, PLAIN or from catalog.
     * Use the same syntax as in X2FD_OpenFile to delete file from catalog.
     * Catalog does not need to be open in order to delete from it.
     *
     * in: file name
     * ret: zero if file doesn't exist or cannot be deleted, non zero otherwise
     */
    public boolean DeleteFile(String pszFileName) {
        return false;
    }

    /**
     * Moves a file from one destination to another
     * Currently supports only moving files within catalog or across catalogs
     * but does not support moving on real file system or between real and getCat
     * filesystem
     *
     * in: file name, new name where to move
     * ret: zero on failure, non zero otherwise
     ************************************************/
    public boolean MoveFile(String pszFileName,String pszNewName) {
        return false;
    }

    /* catalogs */

    /**
     * Opens catalog (with extension CAT) or create new one and return its handle.
     * Every handle opened with this function must be closed with call to
     * X2FD_CloseCatalog.
     *
     * You do not need to open catalog prior to opening file from it. It's done
     * automatically. However you must use this function when creating new catalog.
     *
     * Once the catalog is open, both its files (.getCat and .dat) are exclusively
     * locked so no other processes can access them.
     *
     * in: pszName - catalog name, nCreateDisposition - whether catalog should be
     *     created if it does not exist (X2FD_OPEN_EXISTING - catalog must already
     *     exist, X2FD_CREATE_NEW - catalog will be created if it doesn't exist,
     *     otherwise it's opened)
     * ret: catalog handle or 0 on failure
     */
    public XCatalog OpenCatalog(String pszName, XFDFlag nCreateDisposition) {
        return null;
    }

    /**Will cleanUp handle associated with catalog, but not neccessarily the catalog
     * itself if it's opened by another call to X2FD_OpenCatalog or X2FD_OpenFile
     *
     * If some file was modified inside the catalog, the CAT file (the index file
     * with extension .getCat) will be overwriten upon closing to match to order of
     * files in the modified DAT file.
     *
     * There is no way to verify that it was sucessfull.
     *
     * in: catalog to cleanUp
     * ret: 0 if handle is 0 - otherwise non zero
     */
    public boolean CloseCatalog(XCatalog hCat) {
        return false;
    }

    /**Delete given catalog (both .getCat and .dat file)
     *
     * in: catalog to delete
     * ret: 0 if catalog cannot be deleted (is opened for example), non-zero
     *      otherwise
     */
    public boolean DeleteCatalog(String pszFileName) {
        return false;
    }

    /**
     * Convert time stamp to local time stamp
     * If input is -1, output is also -1
     *
     * in: timestamp
     * ret: timestamp converted to localtime (or -1)
     */
    public long TimeStampToLocalTimeStamp(long TimeStamp) {
        return 0;
    }

    /**
     * Convert time stamp to OLE DataTime (Variant Date)
     * If input is -1, output is 0
     *
     * in: timestamp
     * ret: converted time
     */
    public double TimeStampToOLEDateTime(long TimeStamp) {
        return 0.0;
    }

    /**
     * change the last modification, last access and creation time on a file
     * (for real files) and modification time (for files inside archives)
     *
     * in: file handle, time as time_t
     * ret: 0 on failure, nonzero in success
     */
    public boolean SetFileTime(XFile hFile, long mtime) {
        return false;
    }

    /**
     * works like API FindFirstFile but within catalog
     * it will find all occurrences of file positionDATName in catalog
     *
     * you can use wildcards (* ?) in file name the same way as in
     * API FindFirstFile
     *
     * in: catalog, file name to look for, address of structure for info
     * ret: search handle for use with X2FD_CatFindNextFile and X2FD_CatFindClose
     *      0 if no file name was found, nonzero otherwise
     */
    public XFind CatFindFirstFile(XCatalog hCat, String pszFileName, XCatFileInfo Info) {
        return null;
    }

    /**
     * works like API FindNextFile but within catalog
     * it will find next occurrences of file specified with call to
     * X2FD_CatFindFirstFile
     *
     * in: search handle returned by X2FD_CatFindFirstFile, address of structure for
     *     info
     * ret: 0 if no more occurrences were found, nonzero if there are more
     */
    public boolean CatFindNextFile(XFind hFind, XCatFileInfo info) {
        return false;
    }

    /**
     * works like API FindClose
     * it will delete all getData associated with given search handle
     * you cannot call any search functions with this handle after that
     *
     * in: search handle returned by X2FD_CatFindFirst
     * ret: 0 on failure, nonzero otherwise
     */
    public boolean CatFindClose(XFind hFind) {
        return false;
    }

    /**
     * will move getData from one file to another
     *
     * in: source file, destination file
     * ret: 0 on failure, nonzero otherwise
     */
    public boolean CopyFile(XFile hSource, XFile hDestination) {
        return false;
    }
}
