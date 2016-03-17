package org.rssms.exception;

/**
 * Created by User on 16.03.2016.
 */
public class InvalidDonationException extends Exception {
    public InvalidDonationException() {
        super();
    }

    public InvalidDonationException(String message) {
        super(message);
    }

    public InvalidDonationException(String message, Throwable cause) {
        super(message, cause);
    }
}
