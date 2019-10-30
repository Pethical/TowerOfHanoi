/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi.solver;

import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.hanoi.HanoiTowerCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HanoiSolverTest {

    @Test
    void construct() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection = new HanoiTowerCollection("ooo", "oo", "o");
        assertDoesNotThrow(() -> new HanoiSolver(hanoiTowerCollection));
        assertThrows(IllegalArgumentException.class, () -> new HanoiSolver(null));
    }

    @Test
    void testEmptyTower(){
        HanoiTowerCollection hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        HanoiSolver solver = new HanoiSolver(hanoiTowerCollection);
        assertThrows(IllegalStateException.class, solver::solve);
    }

    @Test
    void setOnStepListener() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection = new HanoiTowerCollection("ooo", "oo", "o");
        HanoiSolver solver = new HanoiSolver(hanoiTowerCollection);
        solver.setOnStepListener(System.out::println);
        solver.setOnStepListener(null);
    }

    @Test
    @Timeout(1)
    void solveComplicated() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection;
        HanoiSolver solver;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower1().push("nagy").push("kicsi");
        hanoiTowerCollection.getTower2().push("közepes");
        solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }

    @Test
    @Timeout(1)
    void solveEmptyA() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection;
        HanoiSolver solver;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower2().push("nagy").push("kicsi");
        hanoiTowerCollection.getTower3().push("közepes");
        solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }

    @Test
    @Timeout(1)
    void solveEmptyABC() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection;
        HanoiSolver solver;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower1().push("nagy");
        hanoiTowerCollection.getTower2().push("kicsi");
        hanoiTowerCollection.getTower3().push("közepes");
        solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }

    @Test
    @Timeout(1)
    void solveWhenAllOnSecondTower() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection;
        HanoiSolver solver;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower2().push("nagy").push("közepes").push("kicsi");
        solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }

    @Test
    @Timeout(1)
    void solveMixed() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection;
        HanoiSolver solver;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower1().push("közepes");
        hanoiTowerCollection.getTower3().push("nagy").push("kicsi");
        solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }

    @Test
    @Timeout(1)
    void solveWithSameCharacters() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection;
        HanoiSolver solver;
        hanoiTowerCollection = new HanoiTowerCollection("ooo", "oo", "o");
        solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }

    @Test
    @Timeout(1)
    void solveEnglish() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection;
        HanoiSolver solver;
        hanoiTowerCollection = new HanoiTowerCollection("big", "middle", "small", (o1, o2) -> o1.compareTo(o2)*-1);
        solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }

    @Test
    @Timeout(1)
    void solveNormal() throws InvalidStackOperationException {
        HanoiTowerCollection hanoiTowerCollection = new HanoiTowerCollection("nagy","közepes","kicsi");
        HanoiSolver solver = new HanoiSolver(hanoiTowerCollection);
        assertDoesNotThrow(solver::solve);
    }
}