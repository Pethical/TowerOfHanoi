/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */
package hu.pethical.hanoiGui;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Disk extends Rectangle {

    private final String value;

    public Disk(String value) {
        this.value = value;
    }

    public Disk(double width, double height, String value) {
        super(width, height);
        this.value = value;
    }

    public Disk(double width, double height, Paint fill, String value) {
        super(width, height, fill);
        this.value = value;
    }

    public Disk(double x, double y, double width, double height, String value) {
        super(x, y, width, height);
        this.value = value;
    }


    public String getValue() {
        return value;
    }

}
