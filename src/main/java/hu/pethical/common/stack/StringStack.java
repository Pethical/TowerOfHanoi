/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack;

import hu.pethical.common.stack.generic.AbstractGenericStack;

/**
 * Stack implementation with String elements
 */
public class StringStack extends AbstractGenericStack<String> {

    public StringStack(char delimiter) {
        super(delimiter);
    }

    @Override
    protected TypeConverter<String> getTypeConverter() {
        return new TypeConverter<String>() {
            @Override
            public String convertFromString(String value) {
                return value;
            }

            @Override
            public String convertToString(String value) {
                return value;
            }
        };
    }

    @Override
    protected String getStack() {
        return super.getStack();
    }

    @Override
    protected char getDelimiter() {
        return super.getDelimiter();
    }
}
