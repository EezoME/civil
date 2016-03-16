package org.rssms.exception;

/**
 * Do we need this?
 * Created by Eezo on 17.03.2016.
 */
public class InvalidProjectStatusException extends Exception {

    public InvalidProjectStatusException(){
        super("Can't change privileged status for banned project.");
    }

    public InvalidProjectStatusException(String message){
        super(message);
    }
}
