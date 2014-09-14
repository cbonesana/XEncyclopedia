package org.dnacorp.xencyclopedia.converter.bob.dom;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorSeverity;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 12.09.2014 18:52.
 */
public class Error {

    private ErrorFacility f;
    private ErrorSeverity s;
    private ErrorCode e;

    public int code;
    public String text;

    public void makeCode(ErrorFacility f, ErrorSeverity s, ErrorCode e) {
        this.f = f;
        this.s = s;
        this.e = e;

        code = s.ordinal() << 24;
        code |= f.ordinal() << 16;
        code |= e.ordinal();
    }

    public int severity() {
        return s.ordinal();
    }

    public int facility() {
        return f.ordinal();
    }

    public boolean failed() {
        return (severity() & BOBErrorSeverity.s_error.ordinal()) > 0;
    }

    public boolean warning() {
        return (severity() & ErrorSeverity.S_Warning.ordinal()) > 0;
    }

    public boolean succeeded() {
        return (severity() & ErrorSeverity.S_OK.ordinal()) == 0;
    }

    public int getCode() {
        return code;
    }
}
