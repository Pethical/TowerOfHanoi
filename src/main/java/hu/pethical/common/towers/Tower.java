/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.towers;

import hu.pethical.common.stack.StringStack;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.common.stack.generic.RestrictedStack;

import java.util.regex.Pattern;

public class Tower extends StringStack implements RestrictedStack<String> {

    private final String name;

    public Tower(char delimiter, String name) {
        super(delimiter);
        this.name = name;
    }

    public boolean canAccept(String disk) {
        return true;
    }

    @Override
    public Tower push(String disk) throws InvalidStackOperationException {
        if (!canAccept(disk))
            throw new InvalidStackOperationException(String.format("Tower can't accept %s", disk));
        super.push(disk);
        return this;
    }

    @Override
    public String toString() {
        return getStack().replaceAll(Pattern.quote(String.valueOf(getDelimiter())), " ");
    }

    public String getName() {
        return name;
    }
}
