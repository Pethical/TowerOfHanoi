/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.towers;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.towers.Tower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {

    @Test
    void push() throws EmptyStackException {
        Tower tower = new Tower('_',"");
        assertEquals(0, tower.count());
        assertDoesNotThrow(()-> tower.push("b"));
        assertEquals(1, tower.count());
        assertEquals("b", tower.top());
        assertDoesNotThrow(()-> tower.push("a"));
        assertEquals(2, tower.count());
        assertEquals("a", tower.top());

    }

    @Test
    void getName() {
        Tower tower = new Tower('_',"test name");
        assertEquals("test name", tower.getName());
    }
}