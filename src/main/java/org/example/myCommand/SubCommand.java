package org.example.myCommand;

import org.example.Context;
import org.example.myExceptions.ArgsAmountException;

import java.util.List;

/**
 * Класс команды "-" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class SubCommand extends AbstractCommand {

    public SubCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation -");

        if (arguments != null)
            throw new ArgsAmountException("bad number of args in SubCommand: " + context.toString());

        String a1 = context.pop();
        String a2 = context.pop();

        double number1 = checkNumberOrVariable(a1);
        double number2 = checkNumberOrVariable(a2);

        String result = String.valueOf(number1 - number2);
        context.push(result);
    }
}
