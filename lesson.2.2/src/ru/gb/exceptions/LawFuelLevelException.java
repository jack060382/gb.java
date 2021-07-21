package ru.gb.exceptions;

public class LawFuelLevelException extends Exception{
    public LawFuelLevelException(String message) {
        super(message);
    }
    public LawFuelLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
