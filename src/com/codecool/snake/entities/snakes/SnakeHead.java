package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SlowingEnemy;
import com.codecool.snake.entities.enemies.AnotherEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.*;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Objects;
import java.util.Random;

public class SnakeHead extends GameEntity implements Animatable {

    private double speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private String player;
    private Text healthNote;

    public SnakeHead(Pane pane, int xc, int yc, String player) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        this.player = player;


        addPart(4);
    }

    public void step() {

        double dir = getRotate();
        if (Globals.leftKeyDown && Objects.equals(player, "Player1")) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown && Objects.equals(player, "Player1")) {
            dir = dir + turnRate;
        }
        if (Globals.AKeyDown && Objects.equals(player, "Player2")) {
            dir = dir - turnRate;
        }
        if (Globals.DKeyDown && Objects.equals(player, "Player2")) {
            dir = dir + turnRate;
        }

        Random rand = new Random();
        if(rand.nextInt(400) == 1){
            new SimpleEnemy(pane);
            new SimplePowerup(pane);
            new SlowingEnemy(pane);
            new AnotherEnemy(pane);
            new FastSnake(pane);
            new YellowSnake(pane);
            new RedSnake(pane);
            new RealSnake(pane);
            new GetSomeHealth(pane);
        }


        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
                if (entity instanceof SnakeBody && !Objects.equals(((SnakeBody) entity).getPlayer(), player)) {
                    for (GameEntity entity2 : Globals.getGameObjects()) {
                        if (entity2 instanceof SnakeBody) {
                            if (Objects.equals(((SnakeBody) entity2).getPlayer(), player)){
                                entity2.destroy();
                            }
                        }
                    }
                    destroy();
                    changeHealth(-health);
                }
            }
        }


        // check for game over condition
        if (isOutOfBounds() || health <= 0 || speed <= 0) {
            Globals.playersAlive --;
            for (GameEntity entity : Globals.getGameObjects()) {
                if (entity instanceof SnakeBody) {
                    if (!Objects.equals(((SnakeBody) entity).getPlayer(), player)) {
                        continue;
                    }
                    entity.destroy();
                }
            }
            destroy();
            changeHealth(-health);
        }

        if (Globals.playersAlive == 0){
            System.out.println("Game Over");
            ImageView gameOver = new ImageView(Globals.gameOver);
            gameOver.setX(400);
            gameOver.setY(0);
            pane.getChildren().add(gameOver);
            Globals.gameLoop.stop();
        }

        pane.getChildren().remove(healthNote);
        healthNote = new Text("Health: " + health);

        if (player.equals("Player1")) {
            healthNote.setX(900);
            healthNote.setY(30);
        }else {
            healthNote.setX(30);
            healthNote.setY(30);
        }
        pane.getChildren().add(healthNote);
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            tail = new SnakeBody(pane, tail, player);
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }
    
    public void changeToRed(SnakeHead snakeHead){
        for(GameEntity entity : Globals.getGameObjects()){
            if(entity instanceof SnakeBody && ((SnakeBody) entity).getPlayer().equals(snakeHead.player)){
                entity.setImage(Globals.redBody);
            }
        }
        if(snakeHead.player.equals("Player1")){
            Globals.snakeBody = Globals.redBody;
        } else{
            Globals.snakeBody2 = Globals.redBody;
        }
    }

    public void changeToYellow(SnakeHead snakeHead){
        for(GameEntity entity : Globals.getGameObjects()){
            if(entity instanceof  SnakeBody && ((SnakeBody) entity).getPlayer().equals(snakeHead.player)){
                entity.setImage(Globals.yellowBody);
            }
        if(snakeHead.player.equals("Player1")){
            Globals.snakeBody = Globals.yellowBody;
        } else{
            Globals.snakeBody2 = Globals.yellowBody;
        }
        }
    }

    public void changeToRealSnake(SnakeHead snakeHead) {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (entity instanceof SnakeBody && ((SnakeBody) entity).getPlayer().equals(snakeHead.player)) {
                entity.setImage(Globals.realSnakeSkin);
            }
            if (snakeHead.player.equals("Player1")) {
                Globals.snakeBody = Globals.realSnakeSkin;
            } else {
                Globals.snakeBody2 = Globals.realSnakeSkin;
            }
        }
    }

    public void changeSpeed(double differ) {
        speed -= differ;
    }
    public void changeSpeedUp(double differ){
        speed += differ;
    }

    }
