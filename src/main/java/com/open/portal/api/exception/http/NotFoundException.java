package com.open.portal.api.exception.http;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}