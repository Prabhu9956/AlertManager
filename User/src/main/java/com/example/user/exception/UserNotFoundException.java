package com.example.user.exception;


public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message, 404);
    }
}
