/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.stack;

import hu.pethical.common.stack.generic.GenericStack;
import hu.pethical.common.stack.generic.GenericStackTest;

import java.util.Random;

class IntStackTest extends GenericStackTest<Integer> {

    @Override
    protected Integer[] getSampleValues(int count) {
        Integer[] values = new Integer[count];
        Random r = new Random();
        for(int i=0;i<count;i++){
            values[i] = r.nextInt();
        }
        return values;
    }

    @Override
    protected GenericStack.TypeConverter<Integer> getTypeConverter() {
        return new GenericStack.TypeConverter<Integer>() {
            @Override
            public Integer convertFromString(String value) {
                return Integer.parseInt(value);
            }

            @Override
            public String convertToString(Integer value) {
                return String.valueOf(value);
            }
        };
    }
}