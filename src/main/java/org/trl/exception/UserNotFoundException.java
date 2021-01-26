package org.trl.exception;

public class UserNotFoundException extends UserServiceApplicationException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
