package org.example.commands;

import org.example.Context;
import org.example.exceptions.*;
import org.example.exceptions.CommandExecutingException;

import java.util.List;

/**
 * Класс команды "SQRT" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class SqrtCommand extends AbstractCommand {

    public SqrtCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation SQRT");

        if (!arguments.isEmpty())
            throw new ArgsAmountException("SQRT: should not be arguments");

        var number = checkNumberOrVariable(context.pop());

        if (number < 0.0)
            throw new CommandExecutingException("negative number SQRT");

        logger.info("result operation SQRT: " + Math.sqrt(number));

        context.push(String.valueOf(Math.sqrt(number)));
    }
}
