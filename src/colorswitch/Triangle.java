package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class Triangle extends Obstacle{
    private transient Polygon yellow, pink, cyan, purple;

    Triangle(int y,double a){
        super(y,a);
        create();
    }
    public void create(){
        g=new Group();
        shapeList=new ArrayList<>();
        yellow = new Polygon();
        yellow.getPoints().addAll(0.0,50.0,50.0,0.0,50.0,100.0);
        yellow.setFill(Color.YELLOW);

        pink = new Polygon();
        pink.getPoints().addAll(0.0,0.0,50.0,50.0,0.0,100.0);
        pink.setFill(Color.DEEPPINK);

        cyan = new Polygon();
        cyan.getPoints().addAll(0.0,0.0,100.0,0.0,50.0,50.0);
        cyan.setFill(Color.CYAN);

        purple = new Polygon();
        purple.getPoints().addAll(50.0,0.0,0.0,50.0,100.0,50.0);
        purple.setFill(Color.PURPLE);

        yellow.relocate(320, 200);
        pink.relocate(375, 300);
        cyan.relocate(275, 300);
        purple.relocate(375, 250);


        g.getChildren().addAll(yellow, pink, cyan, purple);

        rotater1 = new RotateTransition(Duration.seconds(4), g);
        rotater1.setCycleCount(1500);

        shapeList.add(yellow);
        shapeList.add(pink);
        shapeList.add(cyan);
        shapeList.add(purple);
    }

    @Override
    public void appear(AnchorPane root) {
        g.setLayoutY(positionY);
        rotater1.setByAngle(angle);
        rotater1.play();
        root.getChildren().add(g);
    }
}
