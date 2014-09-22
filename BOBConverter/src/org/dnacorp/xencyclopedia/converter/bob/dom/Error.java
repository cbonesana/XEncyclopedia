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
    public String text = "";

    public void makeCode(ErrorFacility f, ErrorSeverity s, ErrorCode e) {
        this.f = f;
        this.s = s;
        this.e = e;

        code = s.value() << 24;
        code |= f.value() << 16;
        code |= e.value();
    }

    public int severity() {
        return s.value();
    }

    public int facility() {
        return f.value();
    }

    public boolean failed() {
        return (severity() & BOBErrorSeverity.s_error.value()) > 0;
    }

    public boolean warning() {
        return (severity() & ErrorSeverity.S_Warning.value()) > 0;
    }

    public boolean succeeded() {
        return (severity() & ErrorSeverity.S_OK.value()) == 0;
    }

    public int getCode() {
        return code;
    }
}
