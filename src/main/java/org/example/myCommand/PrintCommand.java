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
        System.out.println(context.peekTop());
    }

    @Override
    public String toString() {
        return "PrintCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }
}
