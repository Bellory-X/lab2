package org.example.myCommand;


import org.example.Context;

import java.util.List;


/**
 * Абстрактный класс от которого мы реализуем команды калькулятора
 */
public abstract class AbstractCommand {
    protected Context context;
    protected List<String> arguments;

    public AbstractCommand(Context context, List<String> arguments) {
        this.context = context;
        this.arguments = arguments;
    }

    public abstract void doOperation();

    @Override
    public String toString() {
        return "AbstractCommand{" +
                "context=" + context +
                ", arguments=" + arguments +
                '}';
    }

    protected double checkNumberOrVariable(String variable) {
        boolean numeric;
        numeric = variable.matches("-?\\d+(\\.\\d+)?");

        if(numeric)
            return Double.parseDouble(variable);

        return context.getDefine(variable);
    }
}
