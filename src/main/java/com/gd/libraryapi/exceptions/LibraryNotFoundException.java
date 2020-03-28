package com.gd.libraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * LibraryNotFoundException is class with response status not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LibraryNotFoundException extends RuntimeException {

    public LibraryNotFoundException() {
    }

    public LibraryNotFoundException(String message) {
        super(message);
    }

    public LibraryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryNotFoundException(Throwable cause) {
        super(cause);
    }

    public LibraryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
