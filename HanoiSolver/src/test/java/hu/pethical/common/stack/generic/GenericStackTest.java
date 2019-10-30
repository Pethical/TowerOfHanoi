/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack.generic;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class GenericStackTest<T> {

    protected abstract T[] getSampleValues(int count);
    protected abstract GenericStack.TypeConverter<T> getTypeConverter();
    private final char delimiter = '_';

    @Test
    void getStack() throws InvalidStackOperationException {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        T[] values = getSampleValues(2);
        stack.push(values[0]).push(values[1]);
        String expected = getTypeConverter().convertToString(values[0])+delimiter+getTypeConverter().convertToString(values[1]);
        assertEquals(expected, stack.getStack());
    }

    @Test
    void getDelimiter() {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        assertEquals(delimiter, stack.getDelimiter());
    }

    @Test
    void push() throws InvalidStackOperationException {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        T[] values = getSampleValues(2);
        assertEquals(0, stack.count());
        stack.push(values[0]);
        assertEquals(1, stack.count());
        assertEquals(values[0], stack.top());
        stack.push(values[1]);
        assertEquals(2, stack.count());
        assertEquals(values[1], stack.top());
    }

    @Test
    void pop() throws InvalidStackOperationException, EmptyStackException {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        T[] values = getSampleValues(2);
        stack.push(values[0]).push(values[1]);
        assertEquals(2, stack.count());
        T item = stack.pop();
        assertEquals(1, stack.count());
        assertEquals(values[1], item);
        item = stack.pop();
        assertEquals(0, stack.count());
        assertEquals(values[0], item);
        assertTrue(stack.isEmpty());
    }

    @Test
    void top() throws InvalidStackOperationException {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        assertNull(stack.top());
        T[] values = getSampleValues(2);
        stack.push(values[0]).push(values[1]);
        T item = stack.top();
        assertEquals(values[1], item);
    }

    @Test
    void popEmpty() {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void isEmpty() throws InvalidStackOperationException {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        assertTrue(stack.isEmpty());
        T[] values = getSampleValues(1);
        stack.push(values[0]);
        assertFalse(stack.isEmpty());
    }

    @Test
    void count() throws InvalidStackOperationException, EmptyStackException {
        GenericStack<T> stack = new GenericStack<>(delimiter, getTypeConverter());
        T[] values = getSampleValues(2);
        assertEquals(0, stack.count());
        stack.push(values[0]);
        assertEquals(1, stack.count());
        stack.push(values[1]);
        assertEquals(2, stack.count());
        stack.pop();
        assertEquals(1, stack.count());
    }
}
