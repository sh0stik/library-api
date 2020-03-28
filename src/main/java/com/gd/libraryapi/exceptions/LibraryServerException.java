package com.gd.libraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * LibraryServerException is class with response status internal server error
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LibraryServerException extends RuntimeException {
    public LibraryServerException() {
    }

    public LibraryServerException(String message) {
        super(message);
    }

    public LibraryServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryServerException(Throwable cause) {
        super(cause);
    }

    public LibraryServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
