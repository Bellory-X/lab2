package org.example;

import org.example.exceptions.ContextException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContextTest {

    @Test
    public void checkPopOperation() {
        Context context = new Context();

        ContextException thrown = Assertions.assertThrows(ContextException.class, context::pop);
        assertEquals(thrown.getMessage(), "trying to get element from empty stack");

        context.push("ttt");
        context.push("rrr");

        assertEquals(context.peekTop(), "rrr");
        assertEquals(2, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        context.pop();

        assertEquals(context.peekTop(), "ttt");
        assertEquals(1, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        context.pop();
        assertEquals(0, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        thrown = Assertions.assertThrows(ContextException.class, context::pop);
        assertEquals(thrown.getMessage(), "trying to get element from empty stack");
    }

    @Test
    public void checkPushOperation() {
        Context context = new Context();

        assertEquals(0, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        context.push("ttt");

        assertEquals(context.peekTop(), "ttt");
        assertEquals(1, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        context.push("rrr");

        assertEquals(context.peekTop(), "rrr");
        assertEquals(2, context.sizeStack());
        assertEquals(0, context.sizeDefines());
    }

    @Test
    void checkPeekTopOperation() {
        Context context = new Context();

        ContextException thrown = Assertions.assertThrows(ContextException.class, context::peekTop);
        assertEquals(thrown.getMessage(), "trying to get element from empty stack");

        context.push("ttt");
        context.push("rrr");
        context.peekTop();

        assertEquals(context.peekTop(), "rrr");
        assertEquals(2, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        context.pop();
        context.peekTop();

        assertEquals(context.peekTop(), "ttt");
        assertEquals(1, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        context.pop();

        thrown = Assertions.assertThrows(ContextException.class, context::peekTop);
        assertEquals(thrown.getMessage(), "trying to get element from empty stack");
    }

    @Test
    void checkSetDefineOperation() {
        Context context = new Context();

        assertEquals(0, context.sizeStack());
        assertEquals(0, context.sizeDefines());

        context.setDefine("ttt", 0);

        assertEquals(context.getDefine("ttt"), 0);
        assertEquals(0, context.sizeStack());
        assertEquals(1, context.sizeDefines());

        context.setDefine("rrr", 0);

        assertEquals(context.getDefine("ttt"), 0);
        assertEquals(context.getDefine("rrr"), 0);
        assertEquals(0, context.sizeStack());
        assertEquals(2, context.sizeDefines());

        context.setDefine("rrr", 1);

        assertEquals(context.getDefine("ttt"), 0);
        assertEquals(context.getDefine("rrr"), 1);
        assertEquals(0, context.sizeStack());
        assertEquals(2, context.sizeDefines());
    }

    @Test
    void checkGetDefineOperation() {
        Context context = new Context();
        context.setDefine("ttt", 0);
        context.getDefine("ttt");

        assertEquals(context.getDefine("ttt"), 0);
        assertEquals(0, context.sizeStack());
        assertEquals(1, context.sizeDefines());

        context.setDefine("rrr", 0);

        assertEquals(context.getDefine("ttt"), 0);
        assertEquals(context.getDefine("rrr"), 0);
        assertEquals(0, context.sizeStack());
        assertEquals(2, context.sizeDefines());

        context.setDefine("rrr", 1);

        assertEquals(context.getDefine("ttt"), 0);
        assertEquals(context.getDefine("rrr"), 1);
        assertEquals(0, context.sizeStack());
        assertEquals(2, context.sizeDefines());

        ContextException thrown = Assertions.assertThrows(ContextException.class, () -> context.getDefine("tt"));
        assertEquals(thrown.getMessage(), "can't find specified key in map");
    }

    @Test
    void checkSizeStackOperation() {
        Context context = new Context();

        assertEquals(0, context.sizeStack());

        context.push("ttt");

        assertEquals(1, context.sizeStack());

        context.push("rrr");

        assertEquals(2, context.sizeStack());

        context.pop();

        assertEquals(1, context.sizeStack());
    }

    @Test
    void checkSizeDefinesOperation() {
        Context context = new Context();

        assertEquals(0, context.sizeDefines());

        context.setDefine("ttt", 0);

        assertEquals(1, context.sizeDefines());

        context.setDefine("rrr", 0);

        assertEquals(2, context.sizeDefines());

        context.setDefine("rrr", 1);

        assertEquals(2, context.sizeDefines());
    }
}
