package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUserFromAuthenticationException extends RuntimeException {

    public InvalidUserFromAuthenticationException() {
    }

    public InvalidUserFromAuthenticationException(String message) {
        super(message);
    }
}