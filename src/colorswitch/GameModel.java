package colorswitch;

import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class GameModel implements Serializable {
    ArrayList<Obstacle> obstacles;
    ArrayList<Star> stars;
    ArrayList<ColorSwitcher> colorSwitchers;
    Ball ball;
    int currentScore;
    int i = 0;
    int N = 8;
    Game g;
    GameModel(){
        currentScore = 0;
        ball = new Ball(10);
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
        for (int i = 0; i < 2; i++) {
            stars.add(new Star(1, 1));
            stars.add(new Star(1, 1));
            stars.add(new Star(1, 1));
            stars.add(new Star(2, 1));
        }
    }

    void createColorSwitchers() {
        for (int i = 0; i < N; i++) {
            colorSwitchers.add(new ColorSwitcher(1));
        }
    }

    void setUp(Stage stage) throws IOException {
        g.startGame(stage);
    }


}
