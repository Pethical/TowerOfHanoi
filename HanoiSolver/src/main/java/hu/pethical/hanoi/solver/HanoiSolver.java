/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi.solver;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.common.towers.Tower;
import hu.pethical.hanoi.HanoiTowerCollection;

import java.util.function.Consumer;

public class HanoiSolver {

    private HanoiTowerCollection hanoiTowerCollection;
    private Consumer<StepDetails> onStepListener;

    private Tower a;
    private Tower b;
    private Tower c;

    public HanoiSolver(HanoiTowerCollection hanoiTowerCollection) {

        if (hanoiTowerCollection == null)
            throw new IllegalArgumentException("HanoiTowerCollection must not be null");

        this.hanoiTowerCollection = hanoiTowerCollection;

        a = hanoiTowerCollection.getTower1();
        b = hanoiTowerCollection.getTower2();
        c = hanoiTowerCollection.getTower3();
    }

    public void setOnStepListener(Consumer<StepDetails> onStepListener) {
        this.onStepListener = onStepListener;
    }

    public void solve() throws EmptyStackException, InvalidStackOperationException {
        while (hanoiTowerCollection.getTower3().count()!=3) {
            step();
        }
    }

    private void step() throws EmptyStackException, InvalidStackOperationException {
        Tower source = getNextSource();
        Tower target = getNextTarget(source);
        hanoiTowerCollection.moveDisk(source, target);
        if (onStepListener != null) onStepListener.accept(new StepDetails(hanoiTowerCollection, source, target));
        if (c != hanoiTowerCollection.getTower3() && c.count() > 2) {
            Tower temp = a;
            a = c;
            c = temp;
            if (c != hanoiTowerCollection.getTower3()) {
                b = c;
                c = hanoiTowerCollection.getTower3();
            }
        }

        if (hanoiTowerCollection.getFirstEmptyTower() == null) {
            Tower smallest = hanoiTowerCollection.getSmallestMovableDiscTower();
            Tower largest = hanoiTowerCollection.getLargestMovableDiscTower();
            if (c != smallest && c != largest) {
                if (b == smallest) {
                    b = c;
                    c = smallest;
                } else {
                    a = c;
                    c = smallest;
                }
            }
            if (b == largest && a != smallest) {
                b = a;
                a = largest;
            }
        }
    }

    private Tower getNextSource()  {
        if (c.count() > 1 && c != hanoiTowerCollection.getLargestMovableDiscTower())
            return c;

        if (c == hanoiTowerCollection.getLargestMovableDiscTower() && hanoiTowerCollection.getFirstEmptyTower() == null) {
            return a == hanoiTowerCollection.getSmallestMovableDiscTower() ? b : a;
        }
        if (!a.isEmpty() && (b.isEmpty() || c.isEmpty()))
            return a;

        if (hanoiTowerCollection.getSmallestMovableDiscTower() == c && !a.isEmpty() && !b.isEmpty()) {
            return c;
        }

        if (!b.isEmpty()) return b;

        throw new IllegalStateException("Can't move");
    }

    private Tower getNextTarget(Tower source) {

        if ((c.count() == HanoiTowerCollection.NUMBER_OF_DISCS - 1)
                && c.canAccept(source.top()))
            return c;

        if (c.isEmpty()) return c;
        if (b.isEmpty()) return b;
        if (a.isEmpty()) return a;

        if (c.canAccept(source.top())) return c;

        if (hanoiTowerCollection.getLargestMovableDiscTower() != b)
            if (b.canAccept(source.top())) return b;

        if (a.canAccept(source.top())) return a;

        return c;
    }

}
