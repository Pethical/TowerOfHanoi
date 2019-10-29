/*
 * Copyright (c) 2019 i-Cell Mobilsoft Zrt. All rights reserved
 * Author: peter.nemeth
 * This code is licensed under MIT license (see LICENSE.md for details)
 */
package hu.pethical.hanoiGui;

import hu.pethical.common.stack.errors.EmptyStackException;
import hu.pethical.common.stack.errors.InvalidStackOperationException;
import hu.pethical.common.towers.ThreeTowerCollection.TowerFactory;
import hu.pethical.hanoi.HanoiTowerCollection;
import hu.pethical.hanoi.solver.HanoiSolver;
import hu.pethical.hanoi.solver.StepDetails;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

public class MainScene extends Application {

    private static Button button;
    private static Disk a = new Disk(100, 25, "a");
    private static Disk b = new Disk(150, 25, "b");
    private static Disk c = new Disk(200, 25, "c");

    private static HanoiTowerCollection hanoiTowerCollection;

    private void start() {
        button.setDisable(true);
        new Thread(() -> {
            StepDetails.setToStringTemplate("[%s] mozgatása [%s] rúdról [%s] rúdra");
            try {
                SolveAndPrint();
            } catch (InvalidStackOperationException | EmptyStackException e) {
                e.printStackTrace();
            } finally {
                SwingUtilities.invokeLater(() -> {
                    button.setDisable(false);
                });
            }
        }).start();
    }

    private void SolveAndPrint() throws EmptyStackException, InvalidStackOperationException {
        HanoiSolver solver;
        solver = new HanoiSolver(hanoiTowerCollection);
        solver.setOnStepListener((s) -> {
            Platform.runLater(() -> {
                String moved = s.getTarget().top();
                Disk disk = c;
                if (a.getValue().equals(moved)) disk = a;
                else if (b.getValue().equals(moved)) disk = b;
                int row = 3 - s.getTarget().count();
                if (s.getTarget() instanceof HanoiTowerPane) {
                    ((HanoiTowerPane) s.getTarget()).add(disk, 0, row);
                }
                if (s.getSource() instanceof HanoiTowerPane) {
                    ((HanoiTowerPane) s.getSource()).getChildren().remove(disk);
                }
            });
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        solver.solve();
    }

    @Override
    public void start(Stage primaryStage) {

        final GridPane container = new GridPane();
        container.setAlignment(Pos.CENTER);
        hanoiTowerCollection = new HanoiTowerCollection(String::compareTo, getTowerFactory(container));

        final VBox border = new VBox();
        border.setAlignment(Pos.CENTER);
        border.getChildren().add(container);
        border.setPadding(new Insets(10, 10, 10, 10));
        border.setSpacing(20);

        Label label = new Label("Állítsd be tetszőlegesen az egér segítségével a korongokat a rudakon, majd nyomd meg a megoldás gombot");
        border.getChildren().add(label);

        button = new Button();
        button.setText("Megoldás");
        button.setOnAction((e) -> start());
        button.setAlignment(Pos.CENTER);
        border.getChildren().add(button);

        a.setFill(Color.RED);
        b.setFill(Color.GREEN);
        c.setFill(Color.BLUE);

        GridPane.setHalignment(a, HPos.CENTER);
        GridPane.setHalignment(b, HPos.CENTER);
        GridPane.setHalignment(c, HPos.CENTER);

        GridPane.setValignment(a, VPos.BOTTOM);
        GridPane.setValignment(b, VPos.BOTTOM);
        GridPane.setValignment(c, VPos.BOTTOM);

        a.setOnDragDetected(new MouseEventEventHandler());
        b.setOnDragDetected(new MouseEventEventHandler());
        c.setOnDragDetected(new MouseEventEventHandler());

        final BorderPane root = new BorderPane();
        root.setCenter(border);
        root.setPadding(new Insets(100, 10, 10, 10));
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hanoi tornyai");
        primaryStage.show();
    }

    private TowerFactory getTowerFactory(GridPane container) {
        return index -> {
            HanoiTowerPane tower =
                    new HanoiTowerPane(new hu.pethical.hanoi.HanoiTower(String.valueOf(index), String::compareTo));
            tower.setOnDragOver((new DragEventEventHandler(tower)));
            tower.setOnDragDropped(new DropEventHandler(tower));

            container.add(tower, index - 1, 0);

            if (index == 1) {
                try {
                    tower.push(c.getValue())
                            .push(b.getValue())
                            .push(a.getValue());
                    tower.add(a, 0, 0);
                    tower.add(b, 0, 1);
                    tower.add(c, 0, 2);
                } catch (InvalidStackOperationException e) {
                    e.printStackTrace();
                }
            }

            return tower;
        };
    }

    private static class MouseEventEventHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            Disk source = (Disk) event.getSource();
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getValue());
            db.setContent(content);
            event.consume();
        }
    }

    private static class DragEventEventHandler implements EventHandler<DragEvent> {
        private final HanoiTowerPane tower;

        DragEventEventHandler(HanoiTowerPane tower) {
            this.tower = tower;
        }

        @Override
        public void handle(DragEvent event) {
            Disk source = (Disk) event.getGestureSource();
            if (source != event.getTarget())
                if (event.getDragboard().hasString()
                        && tower.canAccept(event.getDragboard().getString())
                ) {
                    if (((HanoiTowerPane) source.getParent()).top().equals(source.getValue())) {
                        event.acceptTransferModes(TransferMode.ANY);
                    }
                }
            event.consume();
        }
    }

    private static class DropEventHandler implements EventHandler<DragEvent> {
        private final HanoiTowerPane tower;

        DropEventHandler(HanoiTowerPane tower) {
            this.tower = tower;
        }

        @Override
        public void handle(DragEvent event) {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                try {
                    tower.push(db.getString());
                    Disk disk = a;
                    if (b.getValue().equals(db.getString()))
                        disk = b;
                    if (c.getValue().equals(db.getString()))
                        disk = c;
                    HanoiTowerPane source = ((HanoiTowerPane) (disk.getParent()));
                    source.getChildren().remove(disk);
                    source.pop();
                    tower.add(disk, 0, 3 - tower.count());
                } catch (InvalidStackOperationException | EmptyStackException e) {
                    e.printStackTrace();
                }
                success = true;
            }
            event.setDropCompleted(success);

            event.consume();

        }
    }
}
