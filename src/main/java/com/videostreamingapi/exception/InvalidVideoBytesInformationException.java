package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;

public class InvalidVideoBytesInformationException extends StatusCodeException {

    public InvalidVideoBytesInformationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
