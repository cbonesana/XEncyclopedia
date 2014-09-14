package org.dnacorp.xencyclopedia.converter.bob.base;

import org.dnacorp.xencyclopedia.converter.bob.BOBErrorStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:55.
 */
public class BOBWithErrors {

    public List<BOBError> errors = new ArrayList<>();

    protected void error(BOBErrorCodes code) {
        error(BOBErrorSeverity.s_error ,code);
    }

    protected void error(BOBErrorSeverity severity, BOBErrorCodes code) {
        error(severity, code, "%s", BOBErrorStrings.bobTranslateError(code));
    }

    protected void error(BOBErrorCodes code, String format, String ... str) {
        error(BOBErrorSeverity.s_error, code, format, str);
    }

    protected void error(BOBErrorSeverity severity, BOBErrorCodes code, String format, String ... str) {
        StringBuilder sb = new StringBuilder();
        for (String s : str)
            sb.append(s);
        verror(severity, code, format, sb.toString());
    }

    protected void verror(BOBErrorSeverity severity, BOBErrorCodes code, String format, String str) {
        BOBError e = new BOBError();
        e.text = String.format(format, str);
        e.code = code;
        e.severity = severity;
        errors.add(e);
    }
}
