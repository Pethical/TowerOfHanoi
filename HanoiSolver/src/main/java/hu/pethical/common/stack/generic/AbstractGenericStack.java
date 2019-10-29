/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack.generic;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;

import java.util.regex.Pattern;

public abstract class AbstractGenericStack<T> implements Stack<T> {

    private final char delimiter;

    private String stack = "";

    public AbstractGenericStack(char delimiter) {
        this.delimiter = delimiter;
    }

    protected abstract TypeConverter<T> getTypeConverter();

    protected char getDelimiter() {
        return delimiter;
    }

    protected String getStack() {
        return stack;
    }

    @Override
    public Stack<T> push(T item) throws InvalidStackOperationException {
        String elem = getTypeConverter().convertToString(item);
        if (isEmpty()) {
            stack = elem;
        } else {
            stack += delimiter + elem;
        }
        return this;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (stack.isEmpty()) throw new EmptyStackException("Stack is empty");
        T elem = top();
        if (stack.indexOf(delimiter) > 0) {
            stack = stack.substring(0, stack.lastIndexOf(delimiter));
        } else {
            stack = "";
        }
        return elem;
    }

    @Override
    public T top()  {
        if (isEmpty()) return null;
        return getTypeConverter().convertFromString(stack.substring(stack.lastIndexOf(delimiter) + 1));
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int count() {
        if (stack.isEmpty()) return 0;
        return stack.split(Pattern.quote(String.valueOf(delimiter))).length;
    }

    public interface TypeConverter<T2> {
        T2 convertFromString(String value);

        String convertToString(T2 value);
    }
}
