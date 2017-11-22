package com.codecool.snake;

import com.codecool.snake.entities.enemies.SlowingEnemy;
import com.codecool.snake.entities.enemies.AnotherEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class Game extends Pane {

    public Game() {
        init();
    }

    public void init() {
        Globals.init();

        new SnakeHead(this, 300, 500, "Player2");
        new SnakeHead(this, 800, 500, "Player1");

//        new SimpleEnemy(this);
//        new SimpleEnemy(this);
//        new SimpleEnemy(this);
//        new SimpleEnemy(this);
        new AnotherEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new SlowingEnemy(this);
        new SlowingEnemy(this);
        new SlowingEnemy(this);
        new SlowingEnemy(this);
    }

    public void start() {

        Scene scene = getScene();
        restartButton(this);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case A:  Globals.AKeyDown  = true; break;
                case D: Globals.DKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case A:  Globals.AKeyDown  = false; break;
                case D: Globals.DKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }


    public void restartButton(Pane pane) {
        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(20);
        restartButton.setLayoutY(650);
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
    public void setTableBackground(Image tableBackground, boolean repeat) {
        BackgroundRepeat backgroundRepeat = repeat ? BackgroundRepeat.REPEAT : BackgroundRepeat.NO_REPEAT;
        setBackground(new javafx.scene.layout.Background(new BackgroundImage(tableBackground,
                backgroundRepeat, backgroundRepeat,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

}
