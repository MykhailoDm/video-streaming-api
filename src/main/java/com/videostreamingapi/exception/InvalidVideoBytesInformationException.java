package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidVideoBytesInformationException extends RuntimeException {

    public InvalidVideoBytesInformationException(String message) {
        super(message);
    }
}
