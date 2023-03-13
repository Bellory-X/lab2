package org.example.commands;

import org.example.Context;
import org.example.exceptions.*;

import java.util.List;

/**
 * Класс команды "/" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class DivideCommand extends AbstractCommand {

    public DivideCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation /");

        if (!arguments.isEmpty())
            throw new ArgsAmountException("/: should not be arguments");

        var number1 = checkNumberOrVariable(context.pop());
        var number2 = checkNumberOrVariable(context.pop());

        if (number2 == 0.0)
            throw new CommandExecutingException("division by zero");

        logger.info("result operation /: " + (number1 / number2));

        context.push(String.valueOf(number1 / number2));
    }
}
