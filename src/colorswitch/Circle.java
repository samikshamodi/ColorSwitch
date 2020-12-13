package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class Circle extends Obstacle {
    double radius;
    Arc yellow, pink, cyan, purple;
    Group g;
    RotateTransition rotater1;
    ArrayList<Shape> shapeList;

    public Circle(String ty, int x, int y, double r) {
        super(ty, x, y);
        radius = r;
        shapeList=new ArrayList<>();

        /*setting properties of all the shapes*/
        yellow = new Arc();
        yellow.setCenterX(0);
        yellow.setCenterY(0);
        yellow.setRadiusX(100);
        yellow.setRadiusY(100);
        yellow.setStartAngle(0+45);
        yellow.setLength(88);
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
        pink.setLength(88);
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
        cyan.setLength(88);
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
        purple.setLength(88);
        purple.setStroke(Color.PURPLE);
        purple.setType(ArcType.OPEN);
        purple.setStrokeWidth(15);
        purple.setFill(Color.TRANSPARENT);

        yellow.relocate(216, 184);
        pink.relocate(180, 218);
        cyan.relocate(213, 367);
        purple.relocate(363, 220);

        /*Putting all the shapes in 1 group*/
        g = new Group();
        g.getChildren().addAll(yellow, pink, cyan, purple);

        /*Rotating the group*/
        rotater1 = new RotateTransition(Duration.seconds(4), g);
        rotater1.setCycleCount(1500);

        /*adding all the javafx shapes to shapeList to letter traverse it and check for collision*/
        shapeList.add(yellow);
        shapeList.add(pink);
        shapeList.add(cyan);
        shapeList.add(purple);

        super.setGroup(g);
        super.setShapeList(shapeList);
    }

    @Override
    public void appear(AnchorPane root) {
        g.setLayoutY(-400);
        rotater1.setByAngle(360);
        rotater1.play();
        root.getChildren().add(g);
    }

    public Group getGroup()
    {
        return g;
    }

    public void setPink(Color color)
    {
        pink.setStroke(color);
    }

    public void setPurple(Color color)
    {
        purple.setStroke(color);
    }

    @Override
    public int checkCollision(Shape ball) {
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

}
