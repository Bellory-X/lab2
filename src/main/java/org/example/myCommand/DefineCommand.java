package org.example.myCommand;

import org.example.Context;
import org.example.myExceptions.ArgsAmountException;
import org.example.myExceptions.CommandExecutingException;

import java.util.List;

/**
 * Класс команды DEFINE стэкового калькулятора, имплементирующий AbstractCommand
 */
public class DefineCommand extends AbstractCommand {

    public DefineCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation DEFINE");

        if (arguments.size() != 2)
            throw new ArgsAmountException("DEFINE: not enough arguments");

        String str = arguments.get(0);
        double value;

        if (Character.isLetter(str.charAt(0)))
            value = checkNumberOrVariable(arguments.get(1));
        else
            throw new CommandExecutingException("incorrect name of defining variable");

        context.setDefine(str, value);
    }
}
