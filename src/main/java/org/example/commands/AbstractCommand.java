package org.example.commands;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.Context;

import java.util.ArrayList;
import java.util.List;


/**
 * Абстрактный класс от которого мы реализуем команды калькулятора
 */
public abstract class AbstractCommand {
    protected Context context;
    protected List<String> arguments;
    protected static final Logger logger = LogManager.getLogger(AbstractCommand.class.getName());

    public AbstractCommand(Context context, List<String> arguments) {
        this.context = context;
        this.arguments = arguments == null ? new ArrayList<>() : arguments;
    }

    public abstract void doOperation();

    protected double checkNumberOrVariable(String variable) {
        if(variable.matches("-?\\d+(\\.\\d+)?"))
            return Double.parseDouble(variable);

        return context.getDefine(variable);
    }
}
