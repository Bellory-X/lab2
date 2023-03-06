package org.example.myCommand;

import org.example.Context;
import org.example.myExceptions.ArgsAmountException;

import java.util.List;

/**
 * Класс команды "POP" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class PopCommand extends AbstractCommand {

    public PopCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation POP");

        if (arguments != null)
            throw new ArgsAmountException("bad number of arguments in PopCommand");

        context.pop();
    }
}
