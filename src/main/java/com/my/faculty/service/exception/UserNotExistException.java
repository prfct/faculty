package com.my.faculty.service.exception;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserNotExistException extends Exception {
    public UserNotExistException() {
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
