package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class RedSnake extends GameEntity implements Interactable {

    public RedSnake(Pane pane){
        super(pane);
        setImage(Globals.redApple);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakehead){
        snakehead.changeToRed(snakehead);
        destroy();
    }

    @Override
    public String getMessage(){
        return "Dresscode : RED";
    }

}
