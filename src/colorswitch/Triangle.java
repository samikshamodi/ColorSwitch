package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class Triangle extends Obstacle{
    double side;
    Polygon yellow, pink, cyan, purple;
    Group g;
    Triangle(String ty, int x, int y, double s){
        super(ty,x,y);
        side=s;
    }

    @Override
    public void disappear(AnchorPane root) {
        root.getChildren().remove(g);
    }

    @Override
    public void appear(AnchorPane root) {
        yellow = new Polygon();
        yellow.getPoints().addAll(new Double[]{0.0,50.0,50.0,0.0,50.0,100.0});
        yellow.setFill(Color.YELLOW);

        pink = new Polygon();
        pink.getPoints().addAll(new Double[]{0.0,0.0,50.0,50.0,0.0,100.0});
        pink.setFill(Color.DEEPPINK);

        cyan = new Polygon();
        cyan.getPoints().addAll(new Double[]{0.0,0.0,100.0,0.0,50.0,50.0});
        cyan.setFill(Color.CYAN);

        purple = new Polygon();
        purple.getPoints().addAll(new Double[]{50.0,0.0,0.0,50.0,100.0,50.0});
        purple.setFill(Color.PURPLE);

        yellow.relocate(320, 200);
        pink.relocate(375, 300);
        cyan.relocate(275, 300);
        purple.relocate(375, 250);


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
        ArrayList<Shape> shapeList=new ArrayList<>();
        shapeList.add(yellow);
        shapeList.add(pink);
        shapeList.add(cyan);
        shapeList.add(purple);

        for(Shape s:shapeList)
        {
            Shape shapeIntersect=Shape.intersect(ball,s);
            //Collision happened
            if (shapeIntersect.getBoundsInLocal().getWidth() != -1) {
                if (s.getFill().equals(ball.getFill())) {
                    return 0;
                }
                else
                {
                    System.out.println("Collision detected "+s.getFill());
                    return 1;
                }
            }

        }
        return 0;
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

}
