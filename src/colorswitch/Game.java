package colorswitch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Game extends Application {
    HashMap<Integer,Obstacle> obstacles;
    HashMap<Integer,ColorSwitcher> colorswitchers;
    HashMap<Integer,Star> stars;
    Player player;

    void addObstacles(){

    }
    Obstacle removeObstacles(){
        return new colorswitch.Circle("Solid",0,0,10.5);
    }
    void addColorSwitcher(){

    }
    ColorSwitcher removeColorSwitcher(){
        return new ColorSwitcher(0,0);
    }
    void addStar(){

    }
    Star addStars(){
       return new Star(1,0,0);
    }
    int startGame(){
        launch();
        return 0;
    }
    int pause(){
        return -1;
    }
    int hitObstacle(){
        return 3;
    }
    int collectStars(){
        return -1;
    }
    String collectColorSwitcher(){
        return " ";
    }
    int fall(){
        return 2;
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/gui/StartGameScene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Circle o1 = new Circle("jk",10,10,10);
        o1.appear(root);
        Ball b = new Ball(10,0);
        b.appear(root);
        BackgroundImage myBI= new BackgroundImage(new Image("/assets/cs-06.png",100,100,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button pause = new Button("");
        pause.setBackground(new Background(myBI));
        pause.setPrefHeight(100);
        pause.setPrefWidth(100);
        pause.setTranslateX(500);
        root.getChildren().add(pause);
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();
    }
}
