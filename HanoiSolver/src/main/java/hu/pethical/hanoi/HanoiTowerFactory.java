/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi;

import hu.pethical.common.towers.ThreeTowerCollection;

import java.util.Comparator;

public class HanoiTowerFactory implements ThreeTowerCollection.TowerFactory<HanoiTower> {
    private final Comparator<String> comparator;
    private String[] names = new String[] {"A","B","C"};

    public HanoiTowerFactory(Comparator<String> comparator) {
        if(comparator==null)
            throw new IllegalArgumentException("Comparator can not be null");
        this.comparator = comparator;
    }

    public HanoiTowerFactory(Comparator<String> comparator, String[] names) {
        if(comparator==null)
            throw new IllegalArgumentException("Comparator can not be null");
        if(names == null || names.length!=3){
            throw new IllegalArgumentException("Please supply 3 names");
        }
        this.comparator = comparator;
        this.names = names;
    }

    @Override
    public HanoiTower createTower(int index) {
        return new HanoiTower(names[index-1], comparator);
    }
}
