package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class Obstacle extends GameElements {
    protected double angle;
    protected transient Group g;
    protected boolean visible;
    protected transient ArrayList<Shape> shapeList;
    protected transient RotateTransition rotater1;
    protected transient double speed;

    Obstacle(int y, double a) {
        super(y);
        angle = a * 360;
        g = new Group();
        shapeList = new ArrayList<>();
        speed = 4;
    }

    int rotate() {
        return 0;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public void moveDown() {
        g.setLayoutY(g.getLayoutY() + 55);
    }

    @Override
    public double getLayoutY() {
        return g.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        g.setLayoutY(dy);
    }

    public void disappear(AnchorPane root) {
        positionY = -400;
        positionX = g.getLayoutX();
        root.getChildren().remove(g);
        visible = false;
    }

    @Override
    public int checkCollision(Shape ball) {
        for (Shape s : shapeList) {
            Shape shapeIntersect = Shape.intersect(ball, s);
            //Collision happened
            if (shapeIntersect.getBoundsInLocal().getWidth() != -1) {
                if (s.getFill().equals(ball.getFill())) {
                    return 0;
                } else {
                    //System.out.println("Collision detected "+s.getFill());
                    return 1;
                }
            }

        }
        return 0;
    }

    public void save() {
        if (visible) {
            positionX = g.getLayoutX();
            positionY = g.getLayoutY();
        }
        //  System.out.println(positionY+" ");
    }

    public void increaseSpeed() {
        if (!visible) {
            rotater1.stop();
            if (speed - 0.005 > 0)
                speed -= 0.005;
        }
    }
}
