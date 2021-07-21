package ru.gb.exceptions;

public class CheckSystemException extends RuntimeException {
    public CheckSystemException(String message) {
        super(message);
    }
    public CheckSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
