package org.example.exceptions;

public class CommandExecutingException extends RuntimeException {

    public CommandExecutingException(String message) {
        super(message);
    }

    public CommandExecutingException(String message, Throwable cause) {
        super(message, cause);
    }
}
