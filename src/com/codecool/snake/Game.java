package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class Game extends Pane {

    public Game() {
        init();
    }

    public void init() {
        Globals.init();
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }

    public void start() {

        Scene scene = getScene();
        restartButton(this);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }


    public void restartButton(Pane pane) {
        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(10);
        restartButton.setLayoutY(20);
        EventHandler<MouseEvent> restart = e -> {
            restartGame(pane);
        };
        restartButton.setOnMouseClicked(restart);
        pane.getChildren().add(restartButton);
    }

    private void restartGame(Pane pane) {
        System.out.println("Game restarted.");
        pane.getChildren().clear();
        init();
        restartButton(pane);
        Globals.gameLoop.start();
    }

}
