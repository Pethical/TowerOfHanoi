/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package hu.pethical.hanoi.solver;

import hu.pethical.common.towers.Tower;
import hu.pethical.hanoi.HanoiTowerCollection;

public final class StepDetails {

    private static String TOSTRING_TEMPLATE = "Moved %s from %s to %s";

    private final HanoiTowerCollection hanoiTowerCollection;
    private final Tower source;
    private final Tower target;

    StepDetails(HanoiTowerCollection hanoiTowerCollection, Tower source, Tower target) {
        this.hanoiTowerCollection = hanoiTowerCollection;
        this.source = source;
        this.target = target;
    }

    public HanoiTowerCollection getHanoiTowerCollection() {
        return hanoiTowerCollection;
    }

    public Tower getSource() {
        return source;
    }

    public Tower getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return String.format(TOSTRING_TEMPLATE, target.top(), source.getName(), target.getName());
    }

    public static void setToStringTemplate(String toStringTemplate) {
        if(toStringTemplate == null || toStringTemplate.isEmpty()){
            throw new IllegalArgumentException("toStringTemplate can not be null");
        }
        TOSTRING_TEMPLATE = toStringTemplate;
    }


}
