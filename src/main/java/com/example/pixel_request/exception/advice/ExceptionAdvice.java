package com.example.pixel_request.exception.advice;

import com.example.pixel_request.exception.InvalidArgumentsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(InvalidArgumentsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String invalidException(InvalidArgumentsException ex) {
        return HttpStatus.NOT_ACCEPTABLE.value() + ": " + ex.getMessage();
    }
}
