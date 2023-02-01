package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// fix response status
@ResponseStatus(HttpStatus.NOT_FOUND)
public class VideoNotFoundException extends RuntimeException {

    public VideoNotFoundException(String message) {
        super(message);
    }
}
