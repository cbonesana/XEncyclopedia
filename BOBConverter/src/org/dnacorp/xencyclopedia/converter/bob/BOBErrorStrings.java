package org.dnacorp.xencyclopedia.converter.bob;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 11.09.2014 17:46.
 */
public class BOBErrorStrings {

    public static String bobErrors[]= {
            "No error.",
            "Bad header.",
            "Bad ending header.",
            "Not enough data.",
            "More data than expected.",
            "Unknown Point flags.",
            "General error.",
            "No STAT format available.",
            "Not enough data to match STAT format.",
            "Format marked with warning sign was used.",
            "Point not found in section POIN.",
            "Invalid version",
            "Unsupported bits set in frame flags",
            "Unknown material6 value type"

            // don forget to increase bob_error_count below!
    };

    public static String bobTranslateError(BOBErrorCodes code) {
        if (code.ordinal() >= 0 && code.ordinal() < bobErrors.length)
            return bobErrors[code.ordinal()];
        return bobErrors[BOBErrorCodes.e_error.ordinal()];
    }
}
