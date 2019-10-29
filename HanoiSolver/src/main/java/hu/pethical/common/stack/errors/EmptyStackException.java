/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack.errors;

/**
 * Thrown by methods in the Stack implementation to indicate that the stack is empty
 */
public final class EmptyStackException extends Exception {
    public EmptyStackException(String message) {
        super(message);
    }
}
