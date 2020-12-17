package colorswitch;

import java.time.LocalDateTime;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class GameModel implements Serializable {
    transient Stage stage;
    DataBase db;
    ArrayList<Obstacle> obstacles;
    ArrayList<Star> stars;
    ArrayList<ColorSwitcher> colorSwitchers;
    Ball ball;
    int currentScore;
    String dT;
    int i = 0;
    int N = 12;
    transient Game g;
    GameModel(){
        currentScore = 0;
        ball = new Ball(600);
        obstacles = new ArrayList<>();
        createObstacles();
        stars = new ArrayList<>();
        createStars();
        colorSwitchers = new ArrayList<>();
        createColorSwitchers();
        g = new Game(this);
    }

    void createObstacles() {
        for (int i = 0; i < 2; i++) {
            obstacles.add(new Square(-400,1));
            obstacles.add(new Circle(-400,1));
            obstacles.add(new SpecialCircle(-400,1));
            obstacles.add(new Triangle(-400,1));
            obstacles.add(new Plus(-400,1));
            obstacles.add(new Special(-400,1));
        }
        //for(int i=0;i<12;i++)
          //  System.out.println(obstacles.get(i).positionY);
    }

    void createStars() {
        for (int i = 0; i < 12; i++) {
            stars.add(new Star(1, -400));
        }
    }

    void createColorSwitchers() {
        for (int i = 0; i < 12; i++) {
            colorSwitchers.add(new ColorSwitcher(-350));
        }
    }

    void setUp(Stage stage,DataBase db) throws IOException {
        this.stage = stage;
        this.db=db;
        g.startGame(stage,false);
    }

    void loadGame(Stage stage,DataBase db) throws IOException {
        g=new Game(this);
        this.db=db;
        this.stage=stage;
        ball.create();
        g.startGame(stage,true);
    }

    void save(){
        LocalDateTime dateTime = LocalDateTime.now();
        dT = dateTime.toString();
        db.saveGameModel(this);
    }
    void resurrect(AnchorPane root){
        if(db.getStars() >= 100) {
            db.setStars();
            try{
                g.startGame(stage,true);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            Label errMessage = new Label("Sorry! Need more stars");
            errMessage.setFont(new Font("Courier New",30));
            errMessage.setMinWidth(400);
            errMessage.setMinHeight(50);
            errMessage.setLayoutX(100);
            errMessage.setLayoutY(500);
            errMessage.setTextFill(Color.WHITE);
            errMessage.setStyle("-fx-background-color: black");
            root.getChildren().add(errMessage);
        }
    }
    void Endgame(AnchorPane root){
        db.end(root,this);
    }
    int Total(){
        return db.getStars();
    }

    @Override
    public String toString(){
        return currentScore+" "+dT;
    }
}
