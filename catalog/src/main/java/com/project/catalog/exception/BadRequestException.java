package com.project.catalog.exception;

public class BadRequestException extends BaseException {

    public BadRequestException(String message, String errorCode) {
        super(message, errorCode);
    }

    public BadRequestException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
