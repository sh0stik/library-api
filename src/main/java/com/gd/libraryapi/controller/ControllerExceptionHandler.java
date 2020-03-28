package com.gd.libraryapi.controller;

import com.gd.libraryapi.exceptions.LibraryNotFoundException;
import com.gd.libraryapi.exceptions.LibraryServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ControllerExceptionHandler is class to handle exceptions and send response with right status
 */
@ControllerAdvice(annotations = RestController.class)
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {LibraryNotFoundException.class})
    public final ResponseEntity<Object> handleNotFoundException(LibraryNotFoundException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        String message = "Not Found";
        return handleExceptionInternal(ex, message, headers, HttpStatus.NOT_FOUND, request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(LibraryServerException.class)
    public final ResponseEntity<Object> handleAllExceptions(LibraryServerException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        String message = "Not Found";
        return handleExceptionInternal(ex, message, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

