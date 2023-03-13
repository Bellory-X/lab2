package org.example;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.commands.AbstractCommand;
import org.example.exceptions.ExecutorException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Класс исполнения команд калькулятора
 */
public class Executor {
    private final Context context = new Context();
    private final List<Data> dataList;
    private static final Logger logger = LogManager.getLogger(Executor.class.getName());

    public Executor(List<Data> dataList) {
        this.dataList = dataList;
    }

    private AbstractCommand getCommand(Data data) {
        try {
            return (AbstractCommand) Class.forName(data.operation())
                    .getDeclaredConstructor(Context.class, List.class)
                    .newInstance(context, data.arguments());
        }
        catch (NullPointerException | ClassNotFoundException | InstantiationException |
               NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ExecutorException("one of the command is not defined");
        }
    }

    public String executeCalculator () {
        logger.info("starting executing calculator");

        dataList.forEach(data -> {
            try {
                getCommand(data).doOperation();
            }
            catch (RuntimeException e) {
                logger.error(e.getMessage());
            }
        });

        logger.info("stop executing calculator and writing result");

        return context.peekTop();
    }
}
