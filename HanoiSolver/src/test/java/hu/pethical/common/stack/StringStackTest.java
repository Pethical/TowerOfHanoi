/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringStackTest {

    private final char delimiter = '_';

    @Test
    void getStack() throws InvalidStackOperationException {
        StringStack stack = new StringStack(delimiter);
        stack.push("b").push("a");
        assertEquals("b"+delimiter+"a", stack.getStack());
    }

    @Test
    void getDelimiter() {
        StringStack stack = new StringStack(delimiter);
        assertEquals(delimiter, stack.getDelimiter());
    }

    @Test
    void push() throws InvalidStackOperationException {
        StringStack stack = new StringStack(delimiter);
        assertEquals(0, stack.count());
        stack.push("b");
        assertEquals(1, stack.count());
        assertEquals("b", stack.top());
        stack.push("a");
        assertEquals(2, stack.count());
        assertEquals("a",stack.top() );
    }

    @Test
    void pop() throws InvalidStackOperationException, EmptyStackException {
        StringStack stack = new StringStack(delimiter);
        stack.push("b").push("a");
        assertEquals(2,stack.count());
        String item = stack.pop();
        assertEquals(1, stack.count());
        assertEquals("a", item);
        item = stack.pop();
        assertEquals(0, stack.count());
        assertEquals("b", item);
        assert stack.isEmpty();
    }

    @Test
    void top() throws InvalidStackOperationException {
        StringStack stack = new StringStack(delimiter);
        assertNull(stack.top());
        stack.push("b").push("a");
        String item = stack.top();
        assertEquals("a", item);
    }

    @Test
    void popEmpty() {
        StringStack stack = new StringStack(delimiter);
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void isEmpty() throws InvalidStackOperationException {
        StringStack stack = new StringStack(delimiter);
        assertTrue(stack.isEmpty());
        stack.push("a");
        assertFalse(stack.isEmpty());
    }

    @Test
    void count() throws InvalidStackOperationException, EmptyStackException {
        StringStack stack = new StringStack(delimiter);
        assertEquals(0, stack.count());
        stack.push("a");
        assertEquals(1, stack.count());
        stack.push("bsdf");
        assertEquals(2, stack.count());
        stack.pop();
        assertEquals(1, stack.count());
    }
}