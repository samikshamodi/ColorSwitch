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
    RotateTransition rotater1;
    ArrayList<Shape> shapeList;

    Triangle(String ty, int x, int y, double s){
        super(ty,x,y);
        side=s;
        shapeList=new ArrayList<>();

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

        rotater1 = new RotateTransition(Duration.seconds(4), g);
        rotater1.setCycleCount(1500);

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
}
