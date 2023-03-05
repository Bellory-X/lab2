package org.example;

import org.example.myCommand.*;
import org.example.myExceptions.CommandExecutingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Преобразует строку со входа в данные, с которыми может работать калькулятор
 */
public class Data {
    private final String operation;
    private final List<String> arguments;

    public Data(String str) {
        arguments = convertStringToList(str);
        operation = getOperationNameFromString(str);
    }

    private List<String> convertStringToList(String str) {
        String[] strSplit = str.split("[^a-zA-Z0-9_.]+");
        List<String> arguments = new ArrayList<>(Arrays.asList(strSplit));

        if (arguments.size() <= 1)
            return null;

        arguments.remove(0);

        return arguments;
    }

    private String getOperationNameFromString(String str) {
        //возможно потом поменять регулярку на более общий разделитьель
        String[] strSplit = str.split(" ");
        List<String> arguments = new ArrayList<>(Arrays.asList(strSplit));

        return switch (arguments.get(0)) {
            case "" -> CommentCommand.class.getName();          //empty strings will be like comments
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
            default -> throw new CommandExecutingException("wrong operation type in Data constructor");
        };
    }

    public String getCommandClassName() {
        return operation;
    }

    public List<String> getArguments(){
        return arguments;
    }

    @Override
    public String toString() {
        return "Data{" +
                "typeOfOperation='" + operation + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
