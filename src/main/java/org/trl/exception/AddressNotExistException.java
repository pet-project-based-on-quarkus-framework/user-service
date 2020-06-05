package org.trl.exception;

public class AddressNotExistException extends RuntimeException {

    public AddressNotExistException(String message) {
        super(message);
    }
}
