package com.eleflow.swapi.infrastructure.exception;

public class ServerException extends GenericException {

    private static int DEFAULT_STATUS = 500;


    public ServerException(String message) {
        super(DEFAULT_STATUS, message);
    }

    public ServerException(String message, Throwable cause) {
        super(DEFAULT_STATUS, message, cause);
    }

    public ServerException(String message, Object... args) {
        super(DEFAULT_STATUS, message, args);
    }

    public ServerException(String message, Throwable cause, Object... args) {
        super(DEFAULT_STATUS, message, cause, args);
    }
}
