package org.example;

import org.example.myExceptions.ContextException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс контекст, содержащий в себе стэк калькулятора, определенные переменные в мапе и все методы для работы с ними
 */
public class Context {
    private final List<String> stack = new ArrayList<>();
    private final Map<String, Double> defines = new HashMap<>();

    public Context() {}

    public String pop() {
        if (stack.isEmpty())
            throw new ContextException("trying to get element from empty stack");

        return stack.remove(stack.size() - 1);
    }

    public String peekTop() {
        if (!stack.isEmpty())
            return stack.get(stack.size() - 1);

        throw new ContextException("trying to get element from empty stack");
    }

    public void push(String element) {
        stack.add(element);
    }

    public void setDefine(String key, double value) {
        defines.put(key, value);
    }

    public double getDefine(String key) {
        if (!defines.containsKey(key))
            throw new ContextException("can't find specified key in map");

        return defines.get(key);
    }

    public boolean stackIsEmpty () {
        return stack.size() == 0;
    }
}
