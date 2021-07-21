package ru.gb.exceptions;

public class UnstableElectricityException extends Exception{
    public UnstableElectricityException(String message) {
        super(message);
    }
    public UnstableElectricityException(String message, Throwable cause) {
        super(message, cause);
    }
}
