package colorswitch;

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
    GameModel(){
        currentScore = 0;
        ball = new Ball(10);
        obstacles = new ArrayList<>();
        createObstacles();
        stars = new ArrayList<>();
        createStars();
        colorSwitchers = new ArrayList<>();
        createColorSwitchers();
    }

    void createObstacles() {
        for (int i = 0; i < 2; i++) {
            obstacles.add(new Square(1));
            obstacles.add(new Circle(1));
            obstacles.add(new SpecialCircle(1));
            obstacles.add(new Triangle(1));
            obstacles.add(new Plus(1));
            obstacles.add(new Special(1));
        }
    }

    void createStars() {
        for (int i = 0; i < N; i++) {
            stars.add(new Star(1, 1));
        }
    }

    void createColorSwitchers() {
        for (int i = 0; i < N; i++) {
            colorSwitchers.add(new ColorSwitcher(1));
        }
    }


}
