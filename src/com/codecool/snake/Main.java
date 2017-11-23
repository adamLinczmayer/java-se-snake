package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("Snake Game");
        Pane pane = new Pane();
        Text text = new Text(150, Globals.WINDOW_HEIGHT / 2, "Snake Game by Abba");
        Text text2 = new Text(400, Globals.WINDOW_HEIGHT / 1.5, "Press any key");
        text.setFont(new Font("Verdana", 65));
        text2.setFont(new Font("Verdana", 30));
        pane.getChildren().add(text);
        pane.getChildren().add(text2);
        Scene scene = new Scene(pane, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnKeyPressed(e -> {
                    Game game = new Game();
                    primaryStage.setTitle("Snake Game");
                    game.setTableBackground(new Image("snake_2.png"), false);
                    primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
                    primaryStage.show();
                    game.start();
                }
        );
    }


}
