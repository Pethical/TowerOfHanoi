/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.towers;

import hu.pethical.common.stack.StringStack;
import hu.pethical.common.stack.errors.InvalidStackOperationException;

import java.util.regex.Pattern;

public class StringTower extends StringStack implements Tower {

    private final String name;

    public StringTower(char delimiter, String name) {
        super(delimiter);
        this.name = name;
    }

    @Override
    public boolean canAccept(String disk) {
        return true;
    }

    @Override
    public StringTower push(String disk) throws InvalidStackOperationException {
        if (!canAccept(disk))
            throw new InvalidStackOperationException(String.format("StringTower can't accept %s", disk));
        super.push(disk);
        return this;
    }

    @Override
    public String toString() {
        return getStack().replaceAll(Pattern.quote(String.valueOf(getDelimiter())), " ");
    }

    @Override
    public String getName() {
        return name;
    }
}
