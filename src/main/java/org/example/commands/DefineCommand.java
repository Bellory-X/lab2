package org.example.commands;

import org.example.Context;
import org.example.exceptions.*;

import java.util.List;

/**
 * Класс команды DEFINE стэкового калькулятора, имплементирующий AbstractCommand
 */
public class DefineCommand extends AbstractCommand {

    public DefineCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation DEFINE " + String.join(" ", arguments));

        if (arguments.size() != 2)
            throw new ArgsAmountException("DEFINE: wrong number of arguments");

        String str = arguments.get(0);
        if (Character.isLetter(str.charAt(0)))
            context.setDefine(str, checkNumberOrVariable(arguments.get(1)));
        else
            throw new CommandExecutingException("incorrect name of defining variable");
    }
}
