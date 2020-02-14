package com.dzone.mancala.web.exceptions;

public class ApiConnectionException extends RuntimeException {
    public ApiConnectionException(String message) {
        super(message);
    }
}
