package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;

public class StatusCodeException extends RuntimeException {

    private final HttpStatus httpStatus;

    public StatusCodeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
