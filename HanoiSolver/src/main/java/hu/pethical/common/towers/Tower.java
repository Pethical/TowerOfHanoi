/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.towers;

import hu.pethical.common.stack.generic.RestrictedStack;

public interface Tower extends  RestrictedStack<String> {
    boolean canAccept(String disk);

    String getName();
}
