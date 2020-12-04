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

import java.util.ArrayList;
import java.util.Collection;

public class SpecialCircle extends Obstacle {
    double radius;
    Arc yellow, pink, cyan, purple;
    Arc yellow2, pink2, cyan2, purple2;
    Group g,g1,g2;

    public SpecialCircle(String ty, int x, int y, double r) {
        super(ty, x, y);
        radius = r;
    }

    @Override
    public void disappear(AnchorPane root) {
        root.getChildren().remove(g);
    }

    @Override
    public void appear(AnchorPane root) {
        yellow = new Arc();
        yellow.setCenterX(0);
        yellow.setCenterY(0);
        yellow.setRadiusX(100);
        yellow.setRadiusY(100);
        yellow.setStartAngle(0+45);
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
        pink.setStartAngle(90+45);
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
        cyan.setStartAngle(180+45);
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
        purple.setStartAngle(270+45);
        purple.setLength(90);
        purple.setStroke(Color.PURPLE);
        purple.setType(ArcType.OPEN);
        purple.setStrokeWidth(15);
        purple.setFill(Color.TRANSPARENT);

        yellow.relocate(110, 190);
        pink.relocate(80, 220);
        cyan.relocate(110, 365);
        purple.relocate(255, 220);

        g1 = new Group();
        g1.getChildren().addAll(yellow, pink, cyan, purple);
        RotateTransition rotater1 = new RotateTransition(Duration.seconds(9), g1);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(1500);
        rotater1.play();


        yellow2 = new Arc();
        yellow2.setCenterX(0);
        yellow2.setCenterY(0);
        yellow2.setRadiusX(100);
        yellow2.setRadiusY(100);
        yellow2.setStartAngle(0+45);
        yellow2.setLength(90);
        yellow2.setStroke(Color.YELLOW);
        yellow2.setType(ArcType.OPEN);
        yellow2.setStrokeWidth(15);
        yellow2.setFill(Color.TRANSPARENT);


        purple2 = new Arc();
        purple2.setCenterX(0);
        purple2.setCenterY(0);
        purple2.setRadiusX(100);
        purple2.setRadiusY(100);
        purple2.setStartAngle(90+45);
        purple2.setLength(90);
        purple2.setStroke(Color.PURPLE);
        purple2.setType(ArcType.OPEN);
        purple2.setStrokeWidth(15);
        purple2.setFill(Color.TRANSPARENT);

        cyan2 = new Arc();
        cyan2.setCenterX(0);
        cyan2.setCenterY(0);
        cyan2.setRadiusX(100);
        cyan2.setRadiusY(100);
        cyan2.setStartAngle(180+45);
        cyan2.setLength(90);
        cyan2.setStroke(Color.CYAN);
        cyan2.setType(ArcType.OPEN);
        cyan2.setStrokeWidth(15);
        cyan2.setFill(Color.TRANSPARENT);

        pink2 = new Arc();
        pink2.setCenterX(0);
        pink2.setCenterY(0);
        pink2.setRadiusX(100);
        pink2.setRadiusY(100);
        pink2.setStartAngle(270+45);
        pink2.setLength(90);
        pink2.setStroke(Color.DEEPPINK);
        pink2.setType(ArcType.OPEN);
        pink2.setStrokeWidth(15);
        pink2.setFill(Color.TRANSPARENT);

        yellow2.relocate(330, 190);
        purple2.relocate(300, 220);
        cyan2.relocate(330, 365);
        pink2.relocate(475, 220);

        g2 = new Group();
        g2.getChildren().addAll(yellow2, pink2, cyan2, purple2);
        RotateTransition rotater2 = new RotateTransition(Duration.seconds(9), g2);
        rotater2.setByAngle(-360);
        rotater2.setCycleCount(1500);
        rotater2.play();

        g=new Group();
        g.getChildren().addAll(g1,g2);
        g.setLayoutY(-400);
        root.getChildren().add(g);
    }

    @Override
    public int checkCollision(Shape ball) {
        ArrayList<Shape> shapeList=new ArrayList<>();
        shapeList.add(yellow);
        shapeList.add(yellow2);
        shapeList.add(pink);
        shapeList.add(pink2);
        shapeList.add(cyan);
        shapeList.add(cyan2);
        shapeList.add(purple);
        shapeList.add(purple2);

        for(Shape s:shapeList)
        {
            Shape shapeIntersect=Shape.intersect(ball,s);
            //Collision happened
            if (shapeIntersect.getBoundsInLocal().getWidth() != -1) {
                if (s.getStroke().equals(ball.getFill())) {
                    return 0;
                }
                else
                {
                    System.out.println("Collision detected "+s.getStroke());
                    return 1;
                }
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
