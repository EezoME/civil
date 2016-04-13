package org.rssms.exception;

/**
 * Created by Eezo on 14.04.2016.
 */
public class BudgetItemNotFoundException extends Exception {

    public BudgetItemNotFoundException() {
        super();
    }

    public BudgetItemNotFoundException(String message) {
        super("Comment with " + message + " was not found.");
    }

    public BudgetItemNotFoundException(String message, Throwable cause) {
        super("Budget item with " + message + " was not found.", cause);
    }
}
