package org.example.commands;

import org.example.Context;
import org.example.exceptions.*;

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

        if (!arguments.isEmpty())
            throw new ArgsAmountException("POP: should not be arguments");

        context.pop();
    }
}
