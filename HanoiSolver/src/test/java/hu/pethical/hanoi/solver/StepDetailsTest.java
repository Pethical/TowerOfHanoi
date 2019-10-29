/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi.solver;

import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.hanoi.HanoiTowerCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepDetailsTest {

    @Test
    void getHanoiTowerCollection() {
        HanoiTowerCollection collection = new HanoiTowerCollection(String::compareTo);
        StepDetails args = new StepDetails(collection, collection.getTower1(), collection.getTower2());
        assertEquals(args.getHanoiTowerCollection(), collection);
    }

    @Test
    void getSource() {
        HanoiTowerCollection collection = new HanoiTowerCollection(String::compareTo);
        StepDetails args = new StepDetails(collection, collection.getTower1(), collection.getTower2());
        assertEquals(collection.getTower1(), args.getSource());
    }

    @Test
    void getTarget() {
        HanoiTowerCollection collection = new HanoiTowerCollection(String::compareTo);
        StepDetails args = new StepDetails(collection, collection.getTower1(), collection.getTower2());
        assertEquals(collection.getTower2(), args.getTarget());
    }

    @Test
    void setToStringTemplate() {
       assertThrows(IllegalArgumentException.class, ()-> StepDetails.setToStringTemplate(null));
       assertThrows(IllegalArgumentException.class, ()-> StepDetails.setToStringTemplate(""));
    }

    @Test
    void testToString() throws InvalidStackOperationException {
        HanoiTowerCollection collection = new HanoiTowerCollection(String::compareTo);
        StepDetails.setToStringTemplate("Move [%s] from [%s] to [%s]");
        collection.getTower2().push("a");
        StepDetails args = new StepDetails(collection, collection.getTower1(), collection.getTower2());
        assertEquals("Move [a] from [A] to [B]", args.toString());
    }

}