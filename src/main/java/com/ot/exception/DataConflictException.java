package com.ot.exception;

import org.springframework.http.HttpStatus;

public class DataConflictException extends ApiException {

    public DataConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}