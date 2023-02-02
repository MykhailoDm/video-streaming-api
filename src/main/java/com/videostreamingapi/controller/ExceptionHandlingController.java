package com.videostreamingapi.controller;

import com.videostreamingapi.dto.ExceptionMessageDto;
import com.videostreamingapi.exception.StatusCodeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = StatusCodeException.class)
    public ResponseEntity<Object> handleStatusCodeExceptions(StatusCodeException statusCodeException, WebRequest webRequest) {
        return ResponseEntity.status(statusCodeException.getHttpStatus())
                .body(new ExceptionMessageDto(statusCodeException.getMessage()));
    }
}
