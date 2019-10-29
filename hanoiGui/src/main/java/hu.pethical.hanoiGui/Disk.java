/*
 * Copyright (c) 2019 i-Cell Mobilsoft Zrt. All rights reserved
 * Author: peter.nemeth
 * This code is licensed under MIT license (see LICENSE.md for details)
 */
package hu.pethical.hanoiGui;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Disk extends Rectangle {

    private String value;

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
