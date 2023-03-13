package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExecutorTest {
    
    @Test
    void checkGoodDataList() {
        Executor executor = new Executor(
                List.of(new Data("DEFINE", List.of("a", "1")),
                        new Data("#", List.of("This is operation")),
                        new Data("PUSH", List.of("a")),
                        new Data("PUSH", List.of("1.5")),
                        new Data("+", null)
                )
        );

        assertEquals(executor.executeCalculator(), "2.5");
    }

    @Test
    void checkBadDataList() {
        Executor executor = new Executor(
                List.of(new Data("DEFINE", List.of("a", "1")),
                        new Data("#", List.of("This is operation")),
                        new Data("-", List.of("1", "4")),
                        new Data("PUSH", List.of("a")),
                        new Data("PUSH", List.of("1.5")),
                        new Data("+", null)
                )
        );

        assertEquals(executor.executeCalculator(), "2.5");
    }
}
