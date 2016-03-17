package org.rssms.exception;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */
public class InvalidUserException extends Exception {

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
