/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTowerCollectionTest {

    private HanoiTowerCollection collection;

    @BeforeEach
    void setUp() throws InvalidStackOperationException {
        collection = new HanoiTowerCollection(String::compareTo);
        collection.getTower1().push("1");
        collection.getTower2().push("2");
        collection.getTower3().push("3");
    }

    @Test
    void constructionTest() {

        assertDoesNotThrow( () -> new HanoiTowerCollection(String::compareTo));
        assertDoesNotThrow( () -> new HanoiTowerCollection(String::compareTo, new HanoiTowerFactory(String::compareTo)));
        assertDoesNotThrow( () -> new HanoiTowerCollection("c","b","a"));
        assertDoesNotThrow( () -> new HanoiTowerCollection("c","b","a", String::compareTo));

        assertThrows(IllegalArgumentException.class, ()-> new HanoiTowerCollection("c","b","a", null));
        assertThrows(IllegalArgumentException.class, ()-> new HanoiTowerCollection(null,"b","a"));
        assertThrows(IllegalArgumentException.class, ()-> new HanoiTowerCollection(null));
        assertThrows(IllegalArgumentException.class, () -> new HanoiTowerCollection(null, new HanoiTowerFactory(String::compareTo)));
        assertThrows(IllegalArgumentException.class, () -> new HanoiTowerCollection(String::compareTo, null));
    }

    @Test
    @Order(1)
    void getTower1() {
        assertEquals(collection.getTower1().top(),"1");
    }

    @Test
    @Order(2)
    void getTower2() {
        assertEquals(collection.getTower2().top(),"2");
    }

    @Test
    @Order(3)
    void getTower3() {
        assertEquals(collection.getTower3().top(),"3");
    }

    @Test
    @Order(7)
    void getEmpty() throws EmptyStackException, InvalidStackOperationException {
        HanoiTowerCollection collection = new HanoiTowerCollection(String::compareTo);
        collection.getTower1().push("1");
        collection.getTower2().push("2");
        collection.getTower3().push("3");
        assertNull(collection.getFirstEmptyTower());
        collection.moveDisk(collection.getTower1(), collection.getTower2());
        assertEquals(collection.getFirstEmptyTower(), collection.getTower1());
    }

    @Test
    @Order(4)
    void getLargestDiscTower() throws InvalidStackOperationException {
        assertEquals(collection.getLargestMovableDiscTower(), collection.getTower3());
        HanoiTowerCollection collection = new HanoiTowerCollection(String::compareTo);
        collection.getTower3().push("c").push("b").push("a");
        assertEquals(collection.getLargestMovableDiscTower(), collection.getTower3());
        collection = new HanoiTowerCollection(String::compareTo);
        collection.getTower2().push("c").push("b").push("a");
        assertEquals(collection.getLargestMovableDiscTower(), collection.getTower2());
    }

    @Test
    @Order(5)
    void getSmallestMovableDiscTower() {
        assertEquals(collection.getSmallestMovableDiscTower(), collection.getTower1());
    }

    @Test
    @Order(6)
    void move() {
        assertDoesNotThrow(()-> collection.moveDisk(collection.getTower1(), collection.getTower2()));
        assertThrows(InvalidStackOperationException.class, () -> collection.moveDisk(collection.getTower3(), collection.getTower2()));
        assertThrows(EmptyStackException.class, () -> collection.moveDisk(collection.getTower1(), collection.getTower3()));
    }

    @Test
    void testToString() {
        assertFalse(collection.toString().isEmpty());
    }

}