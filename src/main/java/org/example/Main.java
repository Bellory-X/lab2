package org.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static List<Data> getDataList(Scanner in) {
        logger.info("getting text from file reader");

        List<Data> dataList = new ArrayList<>();
        while (in.hasNextLine()) {
            List<String> words = List.of(in.nextLine().split("[^a-zA-Z0-9.+*#/-]+"));
            dataList.add(new Data(words.get(0), words.size() == 1 ? null : words.subList(1, words.size())));
        }

        return dataList;
    }

    public static void main(String[] args) {
        try (Scanner in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(System.in);
             FileWriter out = args.length > 1 ? new FileWriter(args[1]) : null)
        {
            if (out == null)
                System.out.println(new Executor(getDataList(in)).executeCalculator());
            else
                out.write(new Executor(getDataList(in)).executeCalculator());
        }
        catch (RuntimeException | IOException e) {
            logger.error(e.getMessage());
        }
    }
}
