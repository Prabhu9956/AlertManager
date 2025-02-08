package com.example.notification.exception;

public class NotificationNotFoundException extends BaseException {
    public NotificationNotFoundException(String message) {
        super(message, 404);
    }
}
