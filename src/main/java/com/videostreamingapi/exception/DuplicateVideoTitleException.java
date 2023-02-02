package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;

public class DuplicateVideoTitleException extends StatusCodeException {

    public DuplicateVideoTitleException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
