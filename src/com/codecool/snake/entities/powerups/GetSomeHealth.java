package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class GetSomeHealth extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int demage = 20;

    public GetSomeHealth(Pane pane) {
        super(pane);

        setImage(Globals.whiteRat);
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
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        Random rand = new Random();
        if(rand.nextInt(600) == 1){
            new GetSomeHealth(pane);
        }
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(+demage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Yeahh HEALTH";
    }
}

