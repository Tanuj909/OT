package com.ot.exception;

import org.springframework.http.HttpStatus;

public class OperationNotAllowedException extends ApiException {

    public OperationNotAllowedException(String message) {
        super(message, HttpStatus.METHOD_NOT_ALLOWED);
    }
}