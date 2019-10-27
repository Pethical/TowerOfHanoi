/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi;
import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.hanoi.HanoiTower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTowerTest {

    @Test
    void construct() {
        assertDoesNotThrow(()->new HanoiTower("", String::compareTo));
        assertThrows(IllegalArgumentException.class, () -> new HanoiTower("",null));
    }

    @Test
    void invalidMove() throws InvalidStackOperationException {
        HanoiTower hanoiTower = new HanoiTower("", String::compareTo);
        hanoiTower.push("b").push("a");
        assertThrows(InvalidStackOperationException.class, ()-> hanoiTower.push("a"));
    }

    @Test
    void canAccept() throws InvalidStackOperationException, EmptyStackException {
        HanoiTower hanoiTower = new HanoiTower("", String::compareTo);
        assertTrue(hanoiTower.isEmpty());
        assertTrue(hanoiTower.canAccept("a"));
        assertTrue(hanoiTower.canAccept("z"));
        hanoiTower.push("b");
        assertFalse(hanoiTower.canAccept("b"));
        assertTrue(hanoiTower.canAccept("a"));
        hanoiTower.push("a");
        assertFalse(hanoiTower.canAccept("b"));
        assertFalse(hanoiTower.canAccept("a"));
        hanoiTower.pop();
        assertTrue(hanoiTower.canAccept("a"));
        assertThrows(IllegalArgumentException.class, ()->hanoiTower.canAccept(null));
        assertThrows(IllegalArgumentException.class, ()->hanoiTower.canAccept(""));
    }

    @Test
    void testToString() throws InvalidStackOperationException {
        HanoiTower hanoiTower = new HanoiTower("", String::compareTo);
        assertTrue(hanoiTower.toString().isEmpty());
        hanoiTower.push("b");
        assertEquals("b", hanoiTower.toString());
        hanoiTower.push("a");
        assertEquals("b a", hanoiTower.toString());
    }
}