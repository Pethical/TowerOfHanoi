/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack.generic;

/**
 * Generic stack implementation with a string storage field
 *
 * @param <T> the type of elements in this stack
 */
public class GenericStack<T> extends AbstractGenericStack<T> {

    private final TypeConverter<T> typeConverter;

    public GenericStack(char delimiter, TypeConverter<T> converter) {
        super(delimiter);
        typeConverter = converter;
    }

    @Override
    protected TypeConverter<T> getTypeConverter() {
        return typeConverter;
    }
}
