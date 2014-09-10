package org.dnacorp.xencyclopedia.converter.bob.base;

import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:57.
 */
public class BOBError {

    public BOBErrorCodes code;
    public BOBErrorSeverity severity;
    public String text;

    public List<BOBError> errors;
}
