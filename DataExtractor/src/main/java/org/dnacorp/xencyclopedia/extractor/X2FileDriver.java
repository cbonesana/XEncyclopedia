package org.dnacorp.xencyclopedia.extractor;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 17:28
 */
public class X2FileDriver {

    public X2File OpenFile(String filename, X2FDFlag read, X2FDFlag openExisting, X2FDFlag filetypeAuto) throws X2FileDriverException {
        return null;
    }

    public void FileStatByHandle(X2File file, X2FileInfo fi) {

    }

    public int FileSize(X2File file) {
        return 0;
    }

    public X2FileInfo FileStatByHandle(X2File file) {
        return null;
    }

    public String ReadFile(X2File file) {
        return null;
    }

    public boolean EOF(X2File file) {
        return false;
    }

    public String ReadFile(X2File file, int size) {
        return null;
    }

    public void SeekFile(X2File file, int i, X2FDFlag seekSet) {

    }

    public boolean CloseFile(X2File file) {
        return false;
    }

    public void WriteFile(X2File file, String text) {

    }

    public void SetEndOfFile(X2File file) {

    }

    public X2Catalog OpenCatalog(String cat, X2FDFlag createNew) {
        return null;
    }

    public void CloseCatalog(X2Catalog catalog) {

    }

    public X2Find CatFindFirstFile(X2Catalog cat, String s, X2CatFileInfo info) {
        return null;
    }

    public Object CatFindNextFile(X2Find hFind, X2CatFileInfo info) {
        return null;
    }

    public void CatFindClose(X2Find hFind) {

    }
}
