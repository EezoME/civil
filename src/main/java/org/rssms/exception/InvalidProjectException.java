package org.rssms.exception;

/**
 *
 * Created by Eezo on 17.03.2016.
 */
public class InvalidProjectException extends Exception {

    public InvalidProjectException() {
        super();
    }

    public InvalidProjectException(String message) {
        super(message);
    }

    public InvalidProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
