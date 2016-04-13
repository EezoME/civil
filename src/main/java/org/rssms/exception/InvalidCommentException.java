package org.rssms.exception;

/**
 * Created by Eezo on 13.04.2016.
 */
public class InvalidCommentException extends Exception {

    public InvalidCommentException() {
        super();
    }

    public InvalidCommentException(String message) {
        super(message);
    }

    public InvalidCommentException(String message, Throwable cause) {
        super(message, cause);
    }
}
