package org.example.myCommand;

import org.example.Context;
import org.example.myExceptions.ArgsAmountException;

import java.util.List;


/**
 * Класс команды "*" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class MultiplyCommand extends AbstractCommand {

    public MultiplyCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {

        if (arguments != null)
            throw new ArgsAmountException("bad number of arguments in MultiplyCommand:" + context.toString());

        String a1 = context.pop();
        String a2 = context.pop();

        double number1 = checkNumberOrVariable(a1);
        double number2 = checkNumberOrVariable(a2);

        String result = String.valueOf(number1 * number2);
        context.push(result);
    }
}
