package org.example.commands;

import org.example.Context;
import org.example.exceptions.ArgsAmountException;

import java.util.List;

/**
 * Класс команды "PRINT" стэкового калькулятора, имплементирующий AbstractCommand
 */
public class PrintCommand extends AbstractCommand {

    public PrintCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation PRINT");

        if (!arguments.isEmpty())
            throw new ArgsAmountException("PRINT: should not be arguments");

        logger.info("result operation PRINT: " + context.peekTop());

        System.out.println(context.peekTop());
    }
}
