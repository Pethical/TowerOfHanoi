/*
 * Copyright (c) 2019 i-Cell Mobilsoft Zrt. All rights reserved
 * Author: peter.nemeth
 * This code is licensed under MIT license (see LICENSE.md for details)
 */
package hu.pethical.common.towers;

import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.common.stack.generic.RestrictedStack;
import hu.pethical.common.stack.generic.Stack;

public interface Tower extends  RestrictedStack<String> {
    boolean canAccept(String disk);

    String getName();
}
