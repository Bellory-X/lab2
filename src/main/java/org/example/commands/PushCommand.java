package org.example.commands;

import org.example.Context;
import org.example.exceptions.*;

import java.util.List;

/**
 * Класс команды "PUSH" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class PushCommand extends AbstractCommand {

    public PushCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation PUSH " + String.join(" ", arguments));

        if (arguments.size() != 1)
            throw new ArgsAmountException("PUSH: wrong number of arguments");

        context.push(arguments.get(0));
    }
}
