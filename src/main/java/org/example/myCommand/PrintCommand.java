package org.example.myCommand;

import org.example.Context;

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

        System.out.println(context.peekTop());
    }
}
