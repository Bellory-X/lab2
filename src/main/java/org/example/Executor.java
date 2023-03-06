package org.example;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.myCommand.AbstractCommand;
import org.example.myExceptions.ExecutorException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Класс исполнения команд для калькулятора
 */
public class Executor {
    private final Context context = new Context();
    private final List<Data> dataList;
    private static final Logger logger = LogManager.getLogger(Executor.class.getName());

    public Executor(List<Data> dataList) {
        this.dataList = dataList;
    }

    private AbstractCommand fabricMethod(Data data) {
        AbstractCommand command;

        try {
            command = (AbstractCommand) Class.forName(data.getCommandClassName())
                    .getDeclaredConstructor(Context.class, List.class)
                    .newInstance(context, data.getArguments());
        }
        catch (NullPointerException | ClassNotFoundException | InstantiationException |
               NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ExecutorException("one of the command is not defined");
        }

        return command;
    }

    public String executeCalculator () {

        logger.info("starting executing calculator");

        dataList.forEach(data -> {
            AbstractCommand command = fabricMethod(data);

            try {
                command.doOperation();
            }
            catch (RuntimeException e) {
                logger.error(e.getMessage());
//                e.printStackTrace();
//                throw new ExecutorException("error while executing command");
            }
        });

        logger.info("stop executing calculator and writing result");

        return context.peekTop();
    }




}
