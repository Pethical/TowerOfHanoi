/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack.generic;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;


/**
 * @param <T> the type of elements in this stack
 */
public interface Stack<T> {

    Stack<T> push(T item) throws InvalidStackOperationException;

    T pop() throws EmptyStackException;

    T top();

    boolean isEmpty();

    int count();
}
