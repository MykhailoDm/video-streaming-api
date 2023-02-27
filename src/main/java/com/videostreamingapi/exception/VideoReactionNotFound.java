package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;

public class VideoReactionNotFound extends StatusCodeException {

    public VideoReactionNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
