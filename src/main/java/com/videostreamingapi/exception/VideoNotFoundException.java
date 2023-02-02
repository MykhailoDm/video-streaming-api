package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;

public class VideoNotFoundException extends StatusCodeException {

    public VideoNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
