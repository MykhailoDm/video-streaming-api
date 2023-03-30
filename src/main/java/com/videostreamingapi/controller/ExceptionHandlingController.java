package com.videostreamingapi.controller;

import com.videostreamingapi.dto.ExceptionMessageDto;
import com.videostreamingapi.exception.StatusCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = StatusCodeException.class)
    public ResponseEntity<Object> handleStatusCodeExceptions(StatusCodeException statusCodeException, WebRequest webRequest) {
        log.error("Status code exception was thrown with message: {}", statusCodeException.getMessage());
        return ResponseEntity.status(statusCodeException.getHttpStatus())
                .body(new ExceptionMessageDto(statusCodeException.getMessage()));
    }
}
