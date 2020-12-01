package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.Collection;

public class Circle extends Obstacle {
    double radius;
    Arc yellow, pink, cyan, purple;
    Group g;

    public Circle(String ty, int x, int y, double r) {
        super(ty, x, y);
        radius = r;
    }

    @Override
    public void disappear(AnchorPane root) {

    }

    @Override
    public void appear(AnchorPane root) {
        yellow = new Arc();
        yellow.setCenterX(0);
        yellow.setCenterY(0);
        yellow.setRadiusX(100);
        yellow.setRadiusY(100);
        yellow.setStartAngle(0);
        yellow.setLength(90);
        yellow.setStroke(Color.YELLOW);
        yellow.setType(ArcType.OPEN);
        yellow.setStrokeWidth(15);
        yellow.setFill(Color.TRANSPARENT);


        pink = new Arc();
        pink.setCenterX(0);
        pink.setCenterY(0);
        pink.setRadiusX(100);
        pink.setRadiusY(100);
        pink.setStartAngle(90);
        pink.setLength(90);
        pink.setStroke(Color.DEEPPINK);
        pink.setType(ArcType.OPEN);
        pink.setStrokeWidth(15);
        pink.setFill(Color.TRANSPARENT);

        cyan = new Arc();
        cyan.setCenterX(0);
        cyan.setCenterY(0);
        cyan.setRadiusX(100);
        cyan.setRadiusY(100);
        cyan.setStartAngle(180);
        cyan.setLength(90);
        cyan.setStroke(Color.CYAN);
        cyan.setType(ArcType.OPEN);
        cyan.setStrokeWidth(15);
        cyan.setFill(Color.TRANSPARENT);

        purple = new Arc();
        purple.setCenterX(0);
        purple.setCenterY(0);
        purple.setRadiusX(100);
        purple.setRadiusY(100);
        purple.setStartAngle(270);
        purple.setLength(90);
        purple.setStroke(Color.PURPLE);
        purple.setType(ArcType.OPEN);
        purple.setStrokeWidth(15);
        purple.setFill(Color.TRANSPARENT);

        yellow.relocate(290, 200);
        pink.relocate(190, 200);
        cyan.relocate(190, 300);
        purple.relocate(290, 300);

        g = new Group();
        g.getChildren().addAll(yellow, pink, cyan, purple);
        g.setLayoutY(-400);
        RotateTransition rotater1 = new RotateTransition(Duration.seconds(4), g);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(1500);
        rotater1.play();
        root.getChildren().add(g);
    }

    @Override
    public int checkCollision(Shape ball) {
        Shape yellowIntersect = Shape.intersect(ball, yellow);
        Shape pinkIntersect = Shape.intersect(ball, pink);
        Shape cyanIntersect = Shape.intersect(ball, cyan);
        Shape purpleIntersect = Shape.intersect(ball, purple);

        //Collision with r1 Yellow
        if (yellowIntersect.getBoundsInLocal().getWidth() != -1) {
            if (yellow.getStroke().equals(ball.getFill())) {
                return 0;
            } else {
                System.out.println("Collision detected Yellow");
                return 1;
            }
        }

        if (pinkIntersect.getBoundsInLocal().getWidth() != -1) {
            if (pink.getStroke().equals(ball.getFill())) {
                return 0;
            } else {
                System.out.println("Collision detected Pink");
                return 1;
            }
        }

        if (cyanIntersect.getBoundsInLocal().getWidth() != -1) {
            if (cyan.getStroke().equals(ball.getFill())) {
                return 0;
            } else {
                System.out.println("Collision detected Cyan");
                return 1;
            }
        }

        if (purpleIntersect.getBoundsInLocal().getWidth() != -1) {
            if (purple.getStroke().equals(ball.getFill())) {
                return 0;
            } else {
                System.out.println("Collision detected Purple");
                return 1;
            }
        }

        return 0;
    }

    @Override
    public void moveDown() {
        g.setLayoutY(g.getLayoutY() + 55); //3 is step or velocity
    }

    @Override
    public double getLayoutY() {
        return g.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        g.setLayoutY(dy);
    }


}
