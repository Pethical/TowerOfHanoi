/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi;

import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.common.towers.ThreeTowerCollection;
import hu.pethical.common.towers.Tower;

import java.util.Comparator;

public class HanoiTowerCollection extends ThreeTowerCollection {

    public static final int NUMBER_OF_DISCS = 3;
    private final Comparator<String> comparator;

    public HanoiTowerCollection(Comparator<String> comparator, TowerFactory towerFactory) {

        super(towerFactory);

        if (comparator == null)
            throw new IllegalArgumentException("Comparator must not be null");

        this.comparator = comparator;
    }

    public HanoiTowerCollection(Comparator<String> comparator) {
        this(comparator, new HanoiTowerFactory(comparator));

    }

    public HanoiTowerCollection(String largeDisk, String middleDisk, String smallDisk, Comparator<String> comparator) throws InvalidStackOperationException {
        this(comparator);
        getTower1().push(largeDisk).push(middleDisk).push(smallDisk);
    }

    public HanoiTowerCollection(String largeDisk, String middleDisk, String smallDisk) throws InvalidStackOperationException {
        this(largeDisk, middleDisk, smallDisk, String::compareTo);
    }

    public Tower getLargestMovableDiscTower() {

        Tower max = getFirstNonEmptyTower();

        if (!getTower2().isEmpty() && (comparator.compare(getTower2().top(), max.top()) > 0)) max = getTower2();
        if (!getTower3().isEmpty() && (comparator.compare(getTower3().top(), max.top()) > 0)) max = getTower3();

        return max;
    }

    public Tower getSmallestMovableDiscTower() {

        Tower min = getFirstNonEmptyTower();
        if (!getTower2().isEmpty() && (comparator.compare(getTower2().top(), min.top()) < 0)) min = getTower2();
        if (!getTower3().isEmpty() && (comparator.compare(getTower3().top(), min.top()) < 0)) min = getTower3();

        return min;
    }

    private Tower getFirstNonEmptyTower() {
        Tower tower = getTower1();
        if (tower.isEmpty() && !getTower2().isEmpty()) tower = getTower2();
        if (tower.isEmpty() && !getTower3().isEmpty()) tower = getTower3();
        return tower;
    }
}
