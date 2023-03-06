package org.example.myCommand;

import org.example.Context;
import org.example.myExceptions.ArgsAmountException;
import org.example.myExceptions.CommandExecutingException;

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

        if (arguments != null)
            throw new ArgsAmountException("bad number of args in SqrtCommand: " + context.toString());

        String a1 = context.pop();

        double number1 = checkNumberOrVariable(a1);

        if (number1 < 0.0)
            throw new CommandExecutingException("negative number sqrt: " + context.toString());

        number1 = Math.sqrt(number1);
        String result = String.valueOf(number1);
        context.push(result);
    }
}
