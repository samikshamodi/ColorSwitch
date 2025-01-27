package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class Square extends Obstacle {
    private transient Rectangle yellow, pink, cyan, purple;
    Square(int y,double a) {
        super(y,a);
        create();
    }
    public void create(){
        g=new Group();
        shapeList=new ArrayList<>();
        yellow = new Rectangle(200, 15, Color.YELLOW);
        pink = new Rectangle(15, 200, Color.DEEPPINK);
        cyan = new Rectangle(200, 15, Color.CYAN);
        purple = new Rectangle(15, 200, Color.PURPLE);


        yellow.setArcWidth(15.0);
        yellow.setArcHeight(15.0);
        pink.setArcWidth(15.0);
        pink.setArcHeight(15.0);
        cyan.setArcWidth(15.0);
        cyan.setArcHeight(15.0);
        purple.setArcWidth(15.0);
        purple.setArcHeight(15.0);

        yellow.relocate(200, 200);
        pink.relocate(200, 200);
        cyan.relocate(200, 385);
        purple.relocate(385, 200);

        g.getChildren().addAll(yellow, pink, cyan, purple);

        rotater1 = new RotateTransition(Duration.seconds(speed), g);
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
        visible=true;
    }
    @Override
    public void moveDown(){
        super.moveDown();
        //System.out.println("Hello");
    }
}