package org.example.myCommand;

import org.example.Context;
import org.example.myExceptions.ArgsAmountException;

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

        if (arguments.size() != 1)
            throw new ArgsAmountException("bad number of args in PushCommand"); //Заменить на свою ошибку

        String a1 = arguments.get(0);

        context.push(a1);
    }

    @Override
    public String toString() {
        return "PushCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }
}
