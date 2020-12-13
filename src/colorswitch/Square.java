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
    double side;
    Rectangle yellow, pink, cyan, purple;
    Group g;
    RotateTransition rotater1;
    ArrayList<Shape> shapeList;

    Square(String ty, int x, int y, double s) {
        super(ty, x, y);
        side = s;
        shapeList=new ArrayList<>();

        yellow = new Rectangle(200, 15, Color.YELLOW);
        pink = new Rectangle(15, 200, Color.DEEPPINK);
        cyan = new Rectangle(200, 15, Color.CYAN);
        purple = new Rectangle(15, 200, Color.PURPLE);

        yellow.relocate(200, 200);
        pink.relocate(200, 215);
        cyan.relocate(200, 400);
        purple.relocate(385, 200);

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
