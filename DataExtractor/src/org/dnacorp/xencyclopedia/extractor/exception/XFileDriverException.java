package org.dnacorp.xencyclopedia.extractor.exception;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 16.08.2014 21:27.
 */
public class XFileDriverException extends Exception implements XFileDriverError {

    public XFileDriverException(String message, int error) {
        super(message + " (" + error + ")");
    }
}
