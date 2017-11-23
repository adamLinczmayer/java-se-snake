package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class YellowSnake extends GameEntity implements Interactable {

    public YellowSnake(Pane pane){
        super(pane);
        setImage(Globals.yellowApple);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakehead){
        snakehead.changeToYellow(snakehead);
        destroy();
    }

    @Override
    public String getMessage(){
        return "Dresscode : YELLOW";
    }

}
