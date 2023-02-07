package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;

public class VideoReactionAlreadyExists extends StatusCodeException {
    public VideoReactionAlreadyExists(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
