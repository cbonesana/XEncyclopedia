package org.dnacorp.xencyclopedia.converter.bob.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 18:57.
 */
public class BOBError {

    public BOBErrorCodes code        = null;
    public BOBErrorSeverity severity = null;
    public String text               = "";

    public List<BOBError> errors     = new ArrayList<>();
}
