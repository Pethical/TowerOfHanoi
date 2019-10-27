/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.common.towers;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;

public class ThreeTowerCollection {

    public static final int NUMBER_OF_TOWERS = 3;

    private final Tower tower1;
    private final Tower tower2;
    private final Tower tower3;

    public ThreeTowerCollection(TowerFactory towerFactory) {

        if(towerFactory==null)
            throw new IllegalArgumentException("TowerFactory can not be null");

        tower1 = towerFactory.createTower(1);
        tower2 = towerFactory.createTower(2);
        tower3 = towerFactory.createTower(3);
    }

    public Tower getTower1() {
        return tower1;
    }

    public Tower getTower2() {
        return tower2;
    }

    public Tower getTower3() {
        return tower3;
    }

    public Tower getFirstEmptyTower() {
        if (getTower1().isEmpty()) return getTower1();
        if (getTower2().isEmpty()) return getTower2();
        if (getTower3().isEmpty()) return getTower3();
        return null;
    }

    public void moveDisk(Tower source, Tower target) throws EmptyStackException, InvalidStackOperationException {
        target.push(source.pop());
    }

    @Override
    public String toString() {
        return getTower1().getName() + ": [ " + getTower1() + " ]\n" +
                getTower2().getName() + ": [ " + getTower2() + " ]\n" +
                getTower3().getName() + ": [ " + getTower3() + " ]";
    }

    public interface TowerFactory{
        Tower createTower(int index);
    }

}
