package org.rssms.exception;

/**
 *
 * Created by Eezo on 17.03.2016.
 */
public class ProjectNotFoundException extends Exception {

    public ProjectNotFoundException(){
        super();
    }

    public ProjectNotFoundException(String message){
        super(message);
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
