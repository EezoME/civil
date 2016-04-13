package org.rssms.exception;

/**
 * Created by Eezo on 14.04.2016.
 */
public class InvalidBudgetItemException extends Exception {

    public InvalidBudgetItemException() {
        super();
    }

    public InvalidBudgetItemException(String message) {
        super(message);
    }

    public InvalidBudgetItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
