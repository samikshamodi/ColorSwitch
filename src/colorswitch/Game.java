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

public class Game {
    HashMap<Integer,Obstacle> obstacles;
    HashMap<Integer,ColorSwitcher> colorswitchers;
    HashMap<Integer,Star> stars;

    void addObstacles(AnchorPane root){
        Circle o1 = new Circle("jk",10,10,10);
        o1.appear(root);
    }
    Obstacle removeObstacles(){
        return new colorswitch.Circle("Solid",0,0,10.5);
    }
    void addBall(AnchorPane root){
        Ball b = new Ball(10,0);
        b.appear(root);
    }
    void addColorSwitcher(){

    }
    ColorSwitcher removeColorSwitcher(){
        return new ColorSwitcher(0,0);
    }
    void addStar(AnchorPane root){
        Star st = new Star(1,0,0);
        st.appear(root);
    }
    Star removeStar(){
       return new Star(1,0,0);
    }
    public int startGame(Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/gui/StartGameScene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        addObstacles(root);
        addBall(root);
        addStar(root);
        BackgroundImage myBI= new BackgroundImage(new Image("/assets/pauseButton.png",85,85,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button pause = new Button("");
        pause.setBackground(new Background(myBI));
        pause.setPrefHeight(85);
        pause.setPrefWidth(85);
        pause.setTranslateX(500);
        pause.setTranslateY(20);
        pause.setOnAction(e-> pause());
        root.getChildren().add(pause);
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();
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


}
