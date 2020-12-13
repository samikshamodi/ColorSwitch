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
        ball = new Ball(10, 0);
        obstacles = new ArrayList<>();
        createObstacles();
        stars = new ArrayList<>();
        createStars();
        colorSwitchers = new ArrayList<>();
        createColorSwitchers();
    }

    void createObstacles() {
        for (int i = 0; i < 2; i++) {
            obstacles.add(new Square("square", 1, 1, 1));
            obstacles.add(new Circle("circle", 1, 1, 1));
            obstacles.add(new SpecialCircle("doubleCircle", 1, 1, 1));
            obstacles.add(new Triangle("triangle", 1, 1, 1));
            obstacles.add(new Plus("doublePlus", 1, 1, 1));
            obstacles.add(new Special("plus", 1, 1));
        }
    }

    void createStars() {
        for (int i = 0; i < N; i++) {
            stars.add(new Star(1, 1, 1));
        }
    }

    void createColorSwitchers() {
        for (int i = 0; i < N; i++) {
            colorSwitchers.add(new ColorSwitcher(1, 1));
        }
    }

}
