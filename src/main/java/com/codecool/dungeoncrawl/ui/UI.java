package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.elements.StatusPane;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Set;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;
    private MainStage mainStage;
    private StatusPane statusPane;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;


    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.statusPane = new StatusPane();
        this.keyHandlers = keyHandlers;
    }

    private void loadGameOver() {
        GameLogic.setVisionRange(30);
        if (logic.isDefeat()) {
            logic.setMap(MapLoader.loadMap("defeat"));
        }
        if (logic.isWin()) {
            logic.setMap(MapLoader.loadMap("victory"));

        }
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
//        mainStage.reload(canvas);
    }

    public void restartGame() {

//        logic = new GameLogic();
//        logic.setup();
        logic.restart();

        logic.setMap(MapLoader.loadMap("map"));
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);

        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            if (!logic.isDefeat() && !logic.isWin()) {
                keyHandler.perform(keyEvent, logic.getMap(), this);
            } else {
                loadGameOver();
                keyHandler.perform(keyEvent, logic.getMap(), this);
            }
        }
        refresh();
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        refreshLabels();
        logic.isGameOver();
    }
    private void refreshLabels(){
        mainStage.setHealthLabelText(logic.getPlayerHealth());
        mainStage.setDamageLabelText(logic.getPlayerDamage());
        mainStage.setInventoryLabelText(logic.getInventoryItems());
        mainStage.setGameOverLabelText(logic.getGameOverText());
    }
}
