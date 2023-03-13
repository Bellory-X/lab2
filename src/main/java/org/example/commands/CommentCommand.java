package org.example.commands;

import org.example.Context;

import java.util.List;


/**
 * Класс команды # стэкового калькулятора, имплементирующий AbstractCommand
 */
public class CommentCommand extends AbstractCommand {

    public CommentCommand(Context context, List<String> arguments) {
        super(context, arguments);
    }

    @Override
    public void doOperation() {
        logger.info("do operation #: " + String.join(" ", arguments));
    }
}
