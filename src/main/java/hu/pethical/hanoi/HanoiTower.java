/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi;

import hu.pethical.common.towers.Tower;

import java.util.Comparator;

public final class HanoiTower extends Tower {

    private static final char delimiter = '_';
    private Comparator<String> comparator;

    public HanoiTower(String name, Comparator<String> comparator) {
        super(delimiter, name);
        if (comparator == null)
            throw new IllegalArgumentException("Comparator must not be null");
        this.comparator = comparator;
    }

    @Override
    public final boolean canAccept(String disk) {

        if (disk == null || disk.isEmpty())
            throw new IllegalArgumentException("Disk must not be null or empty");

        if (isEmpty()) return true;
        if (count()>2) return false;

        return comparator.compare(top(), disk) > 0;
    }
}
