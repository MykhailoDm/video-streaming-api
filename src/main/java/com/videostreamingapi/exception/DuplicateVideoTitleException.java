package com.videostreamingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateVideoTitleException extends RuntimeException {
    public DuplicateVideoTitleException() {
    }

    public DuplicateVideoTitleException(String message) {
        super(message);
    }
}
