package org.example.myCommand;

import org.example.Context;
import org.example.myExceptions.ArgsAmountException;
import org.example.myExceptions.CommandExecutingException;

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

        if (arguments != null)
            throw new ArgsAmountException("DIVIDE: should not be arguments");

        String a1 = context.pop();
        String a2 = context.pop();

        double number1 = checkNumberOrVariable(a1);
        double number2 = checkNumberOrVariable(a2);

        if (number2 == 0.0)
            throw new CommandExecutingException("division by zero");

        String result = String.valueOf(number1 / number2);
        context.push(result);
    }
}
