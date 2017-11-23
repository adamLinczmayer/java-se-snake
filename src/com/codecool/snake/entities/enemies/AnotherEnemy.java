package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class AnotherEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 20;

    public AnotherEnemy(Pane pane) {
        super(pane);

        setImage(Globals.anotherEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        double position = rnd.nextDouble();

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        for (GameEntity entity : Globals.getGameObjects()) {
            if (entity instanceof SnakeHead){
                while (position == entity.getX() || position==entity.getY() ){
                    position = rnd.nextDouble();
                    setX(position * Globals.WINDOW_WIDTH);
                    setY(position * Globals.WINDOW_HEIGHT);
                }
            }
        }

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        if (isOutOfBounds()) {
            int speed = 1;
            double direction = this.getRotate() + 60;
            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());;
        }

    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "20 damage";
    }
}