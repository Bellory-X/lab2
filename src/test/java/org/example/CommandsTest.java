package org.example;

import org.example.commands.*;
import org.example.exceptions.ArgsAmountException;
import org.example.exceptions.CommandExecutingException;
import org.example.exceptions.ContextException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandsTest {

    @Test
    void checkCommentOperation() {
        Context context = new Context();
        context.push("elem");
        context.setDefine("a1", 1.0);

        CommentCommand command = new CommentCommand(context, List.of("a2", "elem2"));
        command.doOperation();

        assertEquals("elem", context.peekTop());
        assertEquals(1.0, context.getDefine("a1"));
        assertEquals(1, context.sizeStack());
        assertEquals(1, context.sizeDefines());
    }

    @Test
    void checkDefineCommand() {
        Context context = new Context();
        DefineCommand command = new DefineCommand(context, List.of("a2", "4"));
        command.doOperation();

        assertEquals(context.getDefine("a2"), 4.0);
        assertEquals(0, context.sizeStack());
        assertEquals(1, context.sizeDefines());

        RuntimeException thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new DefineCommand(new Context(), List.of("a2", "4", "3")).doOperation());
        assertEquals("DEFINE: wrong number of arguments", thrown.getMessage());

        thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new DefineCommand(new Context(), List.of("a2")).doOperation());
        assertEquals("DEFINE: wrong number of arguments", thrown.getMessage());

        thrown = Assertions.assertThrows(
                CommandExecutingException.class,
                () -> new DefineCommand(new Context(), List.of("2", "4")).doOperation());
        assertEquals("incorrect name of defining variable", thrown.getMessage());
    }

    @Test
    void checkDivideCommand() {
        Context context = new Context();

        RuntimeException thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new DivideCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("13");

        thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new DivideCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("13");
        context.push("39");
        DivideCommand command = new DivideCommand(context, null);
        command.doOperation();

        assertEquals(context.peekTop(), String.valueOf(39.0 / 13));
        assertEquals(1, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new DivideCommand(context, List.of("13")).doOperation());
        assertEquals("/: should not be arguments", thrown.getMessage());

        context.push("0");
        context.push("39");

        thrown = Assertions.assertThrows(
                CommandExecutingException.class,
                () -> new DivideCommand(context, null).doOperation());
        assertEquals("division by zero", thrown.getMessage());
    }

    @Test
    void checkMultiplyCommand() {
        Context context = new Context();

        RuntimeException thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new MultiplyCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("13");

        thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new MultiplyCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("13");
        context.push("39");
        MultiplyCommand command = new MultiplyCommand(context, null);
        command.doOperation();

        thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new MultiplyCommand(new Context(), List.of("13")).doOperation());
        assertEquals("*: should not be arguments", thrown.getMessage());

        assertEquals(context.peekTop(), String.valueOf(39.0 * 13));
        assertEquals(1, context.sizeStack());
        assertEquals(0, context.sizeDefines());
    }

    @Test
    void checkPlusCommand() {
        Context context = new Context();

        RuntimeException thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new PlusCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("13");

        thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new PlusCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("13");
        context.push("39");
        PlusCommand command = new PlusCommand(context, null);
        command.doOperation();

        thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new PlusCommand(new Context(), List.of("13")).doOperation());
        assertEquals("+: should not be arguments", thrown.getMessage());

        assertEquals(context.peekTop(), String.valueOf(39.0 + 13));
        assertEquals(1, context.sizeStack());
        assertEquals(0, context.sizeDefines());
    }

    @Test
    void checkPopCommand() {
        Context context = new Context();

        RuntimeException thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new PopCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("test");
        context.push("349");
        PopCommand command = new PopCommand(context, null);

        assertEquals(context.sizeStack(), 2);
        assertEquals(context.peekTop(), "349");
        assertEquals(0, context.sizeDefines());

        thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new PopCommand(context, List.of("3")).doOperation());
        assertEquals("POP: should not be arguments", thrown.getMessage());

        command.doOperation();
        assertEquals(context.sizeStack(), 1);
        assertEquals(context.peekTop(), "test");
        assertEquals(0, context.sizeDefines());

        command.doOperation();
        assertEquals(context.sizeStack(), 0);
        assertEquals(0, context.sizeDefines());
    }

    @Test
    void checkPrintOperation() {
        Context context = new Context();

        RuntimeException thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new PrintCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new PrintCommand(context, List.of("3")).doOperation());
        assertEquals("PRINT: should not be arguments", thrown.getMessage());

        context.push("elem");

        PrintCommand command = new PrintCommand(context, null);
        command.doOperation();

        assertEquals("elem", context.peekTop());
        assertEquals(1, context.sizeStack());
        assertEquals(0, context.sizeDefines());
    }

    @Test
    void checkPushCommand() {
        Context context = new Context();
        PushCommand command = new PushCommand(context, List.of("test"));

        RuntimeException thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new PushCommand(new Context(), null).doOperation());
        assertEquals("PUSH: wrong number of arguments", thrown.getMessage());

        thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new PushCommand(new Context(), List.of("test", "ttt")).doOperation());
        assertEquals("PUSH: wrong number of arguments", thrown.getMessage());

        assertEquals(context.sizeStack(), 0);
        assertEquals(0, context.sizeDefines());

        command.doOperation();
        assertEquals(context.sizeStack(), 1);
        assertEquals(context.peekTop(), "test");
        assertEquals(0, context.sizeDefines());
    }

    @Test
    void checkSqrtCommand() {
        Context context = new Context();
        context.push("144");

        RuntimeException thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new SqrtCommand(context, List.of("tt")).doOperation());
        assertEquals("SQRT: should not be arguments", thrown.getMessage());

        SqrtCommand command = new SqrtCommand(context, null);
        command.doOperation();

        assertEquals(context.peekTop(), String.valueOf(Math.sqrt(144)));
        assertEquals(context.sizeStack(), 1);
        assertEquals(0, context.sizeDefines());

        context.push("-144");
        thrown = Assertions.assertThrows(
                CommandExecutingException.class,
                () -> new SqrtCommand(context, null).doOperation());
        assertEquals("negative number SQRT", thrown.getMessage());
    }

    @Test
    void checkSubCommand() {
        Context context = new Context();

        RuntimeException thrown = Assertions.assertThrows(
                ArgsAmountException.class,
                () -> new SubCommand(context, List.of("tt")).doOperation());
        assertEquals("-: should not be arguments", thrown.getMessage());

        context.push("13");

        thrown = Assertions.assertThrows(
                ContextException.class,
                () -> new SubCommand(context, null).doOperation());
        assertEquals("trying to get element from empty stack", thrown.getMessage());

        context.push("13");
        context.push("39");
        SubCommand command = new SubCommand(context, null);
        command.doOperation();

        assertEquals(context.peekTop(), String.valueOf(39.0 - 13));
        assertEquals(context.sizeStack(), 1);
        assertEquals(0, context.sizeDefines());
    }
}
