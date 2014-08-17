package org.dnacorp.xencyclopedia.extractor.exception;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 21:27.
 */
public class X2FileDriverException extends Exception implements X2FileDriverError {

    public X2FileDriverException(String message, int error) {
        super(message + " (" + error + ")");
    }
}
