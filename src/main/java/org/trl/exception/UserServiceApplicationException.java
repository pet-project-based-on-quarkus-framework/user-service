package org.trl.exception;

public abstract class UserServiceApplicationException extends RuntimeException {

    public UserServiceApplicationException(String message) {
        super(message);
    }

    public UserServiceApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceApplicationException(Throwable cause) {
        super(cause);
    }

}
