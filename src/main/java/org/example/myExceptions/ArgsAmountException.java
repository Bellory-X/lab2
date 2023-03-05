package org.example.myExceptions;

public class ArgsAmountException extends RuntimeException{

    public ArgsAmountException(String message) {
        super(message);
    }

    public ArgsAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
