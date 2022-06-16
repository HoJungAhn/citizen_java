package com.skinnovation.citizen.common.exception.exceptions;

@SuppressWarnings("serial")
public class NotLoginException extends RuntimeException {
    public NotLoginException() {
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }
}
