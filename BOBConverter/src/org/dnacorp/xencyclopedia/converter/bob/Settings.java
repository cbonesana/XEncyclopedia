package org.dnacorp.xencyclopedia.converter.bob;

import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:00.
 */
public class Settings {

    public enum ErrorType{
        Error,
        Warning
    }

    public enum ErrorCodes {
        E_BadConstantDeclaration,
        E_BadFormatDeclaration,
        E_SectionEmpty,
        W_LineIgnored,
        W_ConstantAlwaysZero
    }

    public enum SwitchValue {
        on,
        off,
        notSet
    }

    public class ErrorMsg {
        ErrorType type;
        ErrorCodes code;
        String message;
    }

    public List<ErrorMsg> errorList;

    private boolean m_bOutputWarnings;      // write warnings to file
    private boolean m_bRawMode;				// don't convert to BOD format
    private boolean m_bXtraPntInfo;         // whether extra point info is outputed to BOD
    private boolean m_bWriteX3Data;         // whether to compute/write x3 vertex data and extra part data

    public Settings() {
        outputWarnings(false);
        rawMode(false);
        extraPntInfo(true);
    }

    private void error(ErrorType type, ErrorCodes code, String message) {
        ErrorMsg e = new ErrorMsg();
        e.type = type;
        e.code = code;
        e.message = message;
        errorList.add(e);
    }

    public boolean loadINI(String pszFileName) {
        return false;
    }

    public boolean outputWarnings() {
        return m_bOutputWarnings;
    }

    public void outputWarnings(boolean val) {
        m_bOutputWarnings=val;
    }

    public boolean rawMode() {
        return m_bRawMode;
    }

    public void rawMode(boolean val) {
        m_bRawMode=val;
    }

    public boolean extraPntInfo() {
        return m_bXtraPntInfo;
    }

    public void extraPntInfo(boolean val) {
        m_bXtraPntInfo=val;
    }

    public void writeX3Data(boolean val) {
        m_bWriteX3Data=val;
    }

    public boolean writeX3Data() {
        return m_bWriteX3Data;
    }

}
