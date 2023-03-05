package org.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.myExceptions.CommandExecutingException;
import org.example.myExceptions.ExecutorException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static List<Data> getDataList(Scanner in) {

        logger.info("getting text from file reader");

        List<Data> list = new ArrayList<>();

        while (in.hasNextLine()) {
            String str = in.nextLine();
            list.add(new Data(str));
        }

        return list;
    }

    public static void main(String[] args) {

        try (Scanner in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(System.in);
             FileWriter out = args.length > 1 ? new FileWriter(args[1]) : null)
        {
            List<Data> dataList = getDataList(in);

            Executor executor = new Executor(dataList);

            String result = executor.executeCalculator();
            if (out == null)
                System.out.println(result);
            else
                out.write(result);
        }
        catch (ExecutorException | CommandExecutingException | IOException e) {
            logger.error(e);
        }
    }
}
