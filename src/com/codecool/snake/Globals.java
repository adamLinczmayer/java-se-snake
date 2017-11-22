package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image snakeBody2 = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image gameOver = new Image("snakegameover.png");
    //.. put here the other images you want to use

    // images for new powerups
        // red snake
    public static Image redApple = new Image("red_apple.png");
    public static Image redBody = new Image("red_snake_body.png");
        // yellow snake
    public static Image yellowApple = new Image("yellow_apple.png");
    public static Image yellowBody = new Image("yellow_snake_body.png");
        // real snake
    public static Image meat = new Image("meat.png");
    public static Image realSnakeSkin = new Image("snakeskin.png");

    // speed power change
    public static Image fastPower = new Image("fast.png");


    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean AKeyDown;
    public static boolean DKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static int playersAlive;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void init() {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        leftKeyDown = false;
        rightKeyDown = false;
        playersAlive = 2;
        snakeBody = new Image("snake_body.png");
        snakeBody2 = new Image("snake_body.png");
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
