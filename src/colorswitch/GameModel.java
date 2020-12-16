package colorswitch;

import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class GameModel implements Serializable {
    transient Stage stage;
    transient Main m;
    ArrayList<Obstacle> obstacles;
    ArrayList<Star> stars;
    ArrayList<ColorSwitcher> colorSwitchers;
    Ball ball;
    int currentScore;
    int i = 0;
    int N = 8;
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
    }

    void createStars() {
        for (int i = 0; i < N; i++) {
            stars.add(new Star(1, -400));
        }
    }

    void createColorSwitchers() {
        for (int i = 0; i < N; i++) {
            colorSwitchers.add(new ColorSwitcher(-350));
        }
    }

    void setUp(Stage stage,Main main) throws IOException {
        this.stage = stage;
        this.m=main;
        g.startGame(stage);
    }

    void loadGame(Stage stage,Main main) throws IOException {

        g=new Game(this);
        this.stage=stage;
        this.m=main;
        for (int i = 0; i < 12; i++) {
            obstacles.get(i).create();
            System.out.println(obstacles.get(i).positionY+" "+i);
        }
        for(int i=0;i<N;i++){
            stars.get(i).create();
            colorSwitchers.get(i).create();
            System.out.println(colorSwitchers.get(i).positionY+" "+stars.get(i).positionY+" "+i);
        }
        ball.create();
        System.out.println("Loaded "+i);
        g.startGame(stage);
    }

    void save(){
        try{
            m.saveGame(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
