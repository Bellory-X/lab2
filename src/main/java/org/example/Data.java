package org.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.commands.*;
import org.example.exceptions.CommandExecutingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Преобразует строку со входа в данные, с которыми может работать калькулятор
 */
public class Data {
    private static final Logger logger = LogManager.getLogger(Data.class.getName());
    private final String operation;
    private final List<String> arguments;

    public Data(String operation, List<String> arguments) {
        this.operation = getOperationNameFromString(operation);
        this.arguments = arguments == null ? new ArrayList<>() : arguments;
    }

    public String operation() {
        return operation;
    }

    public List<String> arguments() {
        return arguments;
    }

    private String getOperationNameFromString(String operation) {
        return switch (operation) {
            case "#" -> CommentCommand.class.getName();
            case "/" -> DivideCommand.class.getName();
            case "*" -> MultiplyCommand.class.getName();
            case "+" -> PlusCommand.class.getName();
            case "-" -> SubCommand.class.getName();
            case "POP" -> PopCommand.class.getName();
            case "PUSH" -> PushCommand.class.getName();
            case "SQRT" -> SqrtCommand.class.getName();
            case "PRINT" -> PrintCommand.class.getName();
            case "DEFINE" -> DefineCommand.class.getName();
            default -> {
                logger.error("Not found command: " + arguments.get(0));
                throw new CommandExecutingException("wrong operation type in Data constructor");
            }
        };
    }
}