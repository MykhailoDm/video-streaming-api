package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUserFromAuthentication extends RuntimeException {

    public InvalidUserFromAuthentication() {
    }

    public InvalidUserFromAuthentication(String message) {
        super(message);
    }
}