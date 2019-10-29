/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi;

import hu.pethical.common.towers.Tower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTowerFactoryTest {

    @Test
    void construct() {

        assertDoesNotThrow( () -> new HanoiTowerFactory(String::compareTo));
        assertDoesNotThrow( () -> new HanoiTowerFactory(String::compareTo, new String[]{"A","B","C"}));

        assertThrows(IllegalArgumentException.class, ()->new HanoiTowerFactory(null));
        assertThrows(IllegalArgumentException.class, ()->new HanoiTowerFactory(String::compareTo, null));
        assertThrows(IllegalArgumentException.class, ()->new HanoiTowerFactory(String::compareTo, new String[]{"a"}));
        assertThrows(IllegalArgumentException.class, ()->new HanoiTowerFactory(null, new String[]{"a","b","c"}));
    }

    @Test
    void createTower() {
        assertDoesNotThrow( () -> new HanoiTowerFactory(String::compareTo).createTower(1));
        HanoiTowerFactory factory = new HanoiTowerFactory(String::compareTo, new String[]{"C","B","A"});
        Tower tower = factory.createTower(1);
        assertEquals("C", tower.getName());
        tower = factory.createTower(2);
        assertEquals("B", tower.getName());
        tower = factory.createTower(3);
        assertEquals("A", tower.getName());
    }
}