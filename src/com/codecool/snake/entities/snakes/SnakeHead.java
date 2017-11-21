package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.objects.Global;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private String player;

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
        if (Globals.leftKeyDown && player == "Player1") {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown && player == "Player1") {
            dir = dir + turnRate;
        }
        if (Globals.AKeyDown && player == "Player2") {
            dir = dir - turnRate;
        }
        if (Globals.DKeyDown && player == "Player2") {
            dir = dir + turnRate;
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
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            Globals.playersAlive --;
            for (GameEntity entity : Globals.getGameObjects()) {
                if (entity instanceof SnakeBody) {
                    if (((SnakeBody) entity).getPlayer() == player){
                        entity.destroy();
                    }
                }
            }
            destroy();
        }

        if (Globals.playersAlive == 0){
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail, player);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }
}
