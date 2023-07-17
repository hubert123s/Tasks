package com.example.tasks.handler;

import com.example.tasks.exception.NotFoundCurrencyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundCurrencyException.class)
    ResponseEntity<String> handleNotFoundException(NotFoundCurrencyException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}