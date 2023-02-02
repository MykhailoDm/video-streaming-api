package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;

public class InvalidUserFromAuthenticationException extends StatusCodeException {

    public InvalidUserFromAuthenticationException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}