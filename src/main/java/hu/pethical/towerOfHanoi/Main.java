/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.towerOfHanoi;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.hanoi.HanoiTowerCollection;
import hu.pethical.hanoi.HanoiTowerFactory;
import hu.pethical.hanoi.solver.HanoiSolver;
import hu.pethical.hanoi.solver.StepDetails;

public class Main {

    public static void main(String[] args) throws InvalidStackOperationException, EmptyStackException {
        StepDetails.setToStringTemplate("[%s] mozgatása [%s] rúdról [%s] rúdra");
        CustomFactory();
        SimpleSample();
        InEnglish();
        WithOOO();
        Complicated();
        StartFromTower2();
        MixedStart();
    }

    private static void SolveAndPrint(HanoiTowerCollection hanoiTowerCollection) throws EmptyStackException, InvalidStackOperationException {
        HanoiSolver solver;
        solver = new HanoiSolver(hanoiTowerCollection);
        System.out.println("Kezdeti állapot:");
        System.out.println(hanoiTowerCollection);
        solver.setOnStepListener(System.out::println);
        solver.solve();
        System.out.println("Végső állapot:");
        System.out.println(hanoiTowerCollection);
        System.out.println("--------------------------------------------------");
    }

    private static void MixedStart() throws InvalidStackOperationException, EmptyStackException {
        HanoiTowerCollection hanoiTowerCollection;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower1().push("nagy").push("kicsi");
        hanoiTowerCollection.getTower2().push("közepes");
        SolveAndPrint(hanoiTowerCollection);
    }

    private static void StartFromTower2() throws InvalidStackOperationException, EmptyStackException {
        HanoiTowerCollection hanoiTowerCollection;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower2().push("nagy").push("közepes").push("kicsi");
        SolveAndPrint(hanoiTowerCollection);
    }

    private static void Complicated() throws InvalidStackOperationException, EmptyStackException {
        HanoiTowerCollection hanoiTowerCollection;
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo);
        hanoiTowerCollection.getTower1().push("közepes");
        hanoiTowerCollection.getTower3().push("nagy").push("kicsi");
        SolveAndPrint(hanoiTowerCollection);
    }

    private static void WithOOO() throws InvalidStackOperationException, EmptyStackException {
        HanoiTowerCollection hanoiTowerCollection;
        hanoiTowerCollection = new HanoiTowerCollection("ooo", "oo", "o");
        SolveAndPrint(hanoiTowerCollection);
    }

    private static void InEnglish() throws InvalidStackOperationException, EmptyStackException {
        HanoiTowerCollection hanoiTowerCollection;
        hanoiTowerCollection = new HanoiTowerCollection("big", "middle", "small", (o1, o2) -> o1.compareTo(o2) * -1);
        SolveAndPrint(hanoiTowerCollection);
    }

    private static void SimpleSample() throws InvalidStackOperationException, EmptyStackException {
        HanoiTowerCollection hanoiTowerCollection;
        hanoiTowerCollection = new HanoiTowerCollection("nagy", "közepes", "kicsi");
        SolveAndPrint(hanoiTowerCollection);
    }

    private static void CustomFactory() throws InvalidStackOperationException, EmptyStackException {
        HanoiTowerCollection hanoiTowerCollection = new HanoiTowerCollection(String::compareTo, new HanoiTowerFactory(String::compareTo, new String[]{ "Első", "Második", "Harmadik" }));
        hanoiTowerCollection.getTower1().push("nagy").push("közepes").push("kicsi");
        SolveAndPrint(hanoiTowerCollection);
    }
}