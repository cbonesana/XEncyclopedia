package org.dnacorp.xencyclopedia.converter.bob.dom;

import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBError;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorSeverity;
import org.dnacorp.xencyclopedia.converter.bob.base.BOBWithErrors;
import org.dnacorp.xencyclopedia.converter.bob.cut.BOBDomCUT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.dnacorp.xencyclopedia.converter.bob.BOBSection.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 20:54.
 */
public class BOBDomDocument {

    public BOBDomBOB bob = null;
    public BOBDomCUT cut = null;

    private Settings settings;
    private List<Error> errorList = new ArrayList<>();

    void error(ErrorFacility facility, ErrorSeverity severity, ErrorCode code, String format, String ... str) {
        verror(facility, severity, code, format, str);
    }

    private void verror(ErrorFacility facility, ErrorSeverity severity, ErrorCode code, String format, String ... str){
        Error e = new Error();

        e.makeCode(facility, severity, code);
        StringBuilder sb = new StringBuilder();
        for (String s : str)
            sb.append(s);
        e.text = String.format("%s", sb.toString());
        errorList.add(e);
    }

    private void vbobError(BOBErrorSeverity severity, BOBErrorCodes code, String format, String ... str){
        ErrorCode c;
        ErrorSeverity s;
        ErrorFacility f = ErrorFacility.F_BOBLoader;
        switch (code) {
            case e_badEndHeader:
                c=ErrorCode.E_BadEndHeader;
                break;
            case e_badHeader:
                c=ErrorCode.E_BadHeader;
                break;
            case e_moreData:
                c=ErrorCode.E_MoreData;
                break;
            case e_noError:
                c=ErrorCode.E_NoError;
                s=ErrorSeverity.S_OK;
                break;
            case e_format_noStatFormat:
                c=ErrorCode.E_NoStatFormat;
                f=ErrorFacility.F_StatLoader;
                break;
            case e_format_notEnoughData:
                c=ErrorCode.E_NotEnoughData;
                f=ErrorFacility.F_StatLoader;
                break;
            case e_format_UserWarning:
                c=ErrorCode.E_FormatUserWarning;
                s=ErrorSeverity.S_Warning;
                f=ErrorFacility.F_StatLoader;
                break;
            case e_notEnoughData:
                c=ErrorCode.E_NotEnoughData;
                break;
            case e_unkPointFlags:
                c=ErrorCode.E_UnkPointFlags;
                break;
            case e_pointNotFound:
                c=ErrorCode.E_PointNotFound;
                break;
            case e_badVersion:
                c=ErrorCode.E_BadVersion;
                break;
            case e_error: // nobreak
            default:
                c=ErrorCode.E_Error;
        }
        s = (severity == BOBErrorSeverity.s_warning ? ErrorSeverity.S_Warning : ErrorSeverity.S_Error);
        verror(f, s, c, format, str);
    }

    private void bobError(BOBErrorSeverity severity, BOBErrorCodes code, String format, String ... str){
        vbobError(severity, code, format, str);
    }

    public BOBDomDocument(Settings settings) {
        this.settings = settings;
    }

    public boolean fromFile(DataInputStream dis) throws IOException {
        boolean bRes = false;

        BOBWithErrors e = null;
        String name = "";

        switch(peek(dis)) {
            case BOBDomBOB.HDR_BEGIN:
                bob = new BOBDomBOB(settings);
                e = bob;
                name = "bob";
                bRes = bob.load(dis);
                break;
            case BOBDomCUT.HDR_BEGIN:
                cut = new BOBDomCUT(settings);
                e = cut;
                name = "cut";
                bRes = cut.load(dis);
                break;
            default:
                error(ErrorFacility.F_BOBLoader, ErrorSeverity.S_Error, ErrorCode.E_BadHeader, "Bad document header.");
        }

        if (e != null){
            for (BOBError it : e.errors) {
                bobError(it.severity, it.code, "%s->%s", name, it.text);
            }
        }

        return bRes;
    }

    public boolean convert(DataInputStream dis, DataOutputStream dos) throws IOException {
        boolean bRes = false;
        BOBWithErrors e = null;
        String pszTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String name = "";

        dos.writeChars("// Converted with x2bc from \"" + /* TODO: dis.name() + */"\" at " + pszTime + "\n");
        if (settings.rawMode()) {
            dos.writeChars("// Raw mode - values are not converted \n");
        }
        dos.writeChars("\n");

        switch(peek(dis)) {
            case BOBDomBOB.HDR_BEGIN:
                bob = new BOBDomBOB(settings);
                e = bob;
                name = "bob";
                bRes = bob.load(dis);
                if (bRes) bRes = bob.toTextFile(dos);
                break;
            case BOBDomCUT.HDR_BEGIN:
                cut = new BOBDomCUT(settings);
                e = cut;
                name = "cut";
                bRes = cut.convert(dis, dos);
                break;
            default:
                error(ErrorFacility.F_BOBLoader, ErrorSeverity.S_Error, ErrorCode.E_BadHeader, "Bad document header.");
        }

        if (e != null){
            for (BOBError it : e.errors) {
                bobError(it.severity, it.code, "%s->%s", name, it.text);
            }
        }

        return false;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        if (bob != null)
            return bob.toBinaryFile(dos);
        else if (cut != null)
            return cut.toBinaryFile(dos);
        return false;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars("// Converted with x2bc\n");
        if (settings.rawMode())
            dos.writeChars("// Raw mode - values are not converted\n");
        dos.writeChars("\n");

        if (bob != null)
            return bob.toTextFile(dos);
        else if (cut != null)
            return cut.toTextFile(dos);

        return false;
    }
}
