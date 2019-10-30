/*
 * Copyright (c) 2019 Péter Németh. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */
package hu.pethical.hanoiGui;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.common.towers.Tower;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HanoiTowerPane extends GridPane implements Tower {
    private final Tower tower;

    public HanoiTowerPane(Tower tower) {
        this.tower = tower;
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));
        setMinWidth(220);
        setAlignment(Pos.CENTER);
        setMinHeight(200);
        setPrefHeight(200);
        setAlignment(Pos.BOTTOM_CENTER);
        GridPane.setValignment(this, VPos.BOTTOM);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("tower.png",
                        64,
                        32,
                        true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(24, 50, false, true, false, false));
        setBackground(new Background(backgroundImage));

    }

    @Override
    public boolean canAccept(String disk) {
        return tower.canAccept(disk);
    }

    @Override
    public String getName() {
        return tower.getName();
    }

    @Override
    public HanoiTowerPane push(String item) throws InvalidStackOperationException {
        tower.push(item);
        return this;
    }

    @Override
    public String pop() throws EmptyStackException {
        return tower.pop();
    }

    @Override
    public String top() {
        return tower.top();
    }

    @Override
    public boolean isEmpty() {
        return tower.isEmpty();
    }

    @Override
    public int count() {
        return tower.count();
    }
}
