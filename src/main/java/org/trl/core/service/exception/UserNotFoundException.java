package org.trl.core.service.exception;

import org.trl.exception.UserServiceApplicationException;

public class UserNotFoundException extends UserServiceApplicationException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
