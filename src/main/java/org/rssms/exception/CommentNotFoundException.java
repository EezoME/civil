package org.rssms.exception;

/**
 * Created by Eezo on 13.04.2016.
 */
public class CommentNotFoundException extends Exception {

    public CommentNotFoundException() {
        super();
    }

    public CommentNotFoundException(String message) {
        super("Comment with " + message + " was not found.");
    }

    public CommentNotFoundException(String message, Throwable cause) {
        super("Comment with " + message + " was not found.", cause);
    }
}
