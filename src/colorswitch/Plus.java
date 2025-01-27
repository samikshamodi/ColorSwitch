package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class Plus extends Obstacle {
    private transient Rectangle yellow, pink, cyan, purple;


    Plus(int y,double a) {
        super(y,a);
        create();
    }
    public void create(){
        g=new Group();
        shapeList=new ArrayList<>();
        yellow = new Rectangle(100, 15, Color.YELLOW);
        pink = new Rectangle(15, 100, Color.DEEPPINK);
        cyan = new Rectangle(100, 15, Color.CYAN);
        purple = new Rectangle(15, 100, Color.PURPLE);

        yellow.setArcWidth(15.0);
        yellow.setArcHeight(15.0);
        pink.setArcWidth(15.0);
        pink.setArcHeight(15.0);
        cyan.setArcWidth(15.0);
        cyan.setArcHeight(15.0);
        purple.setArcWidth(15.0);
        purple.setArcHeight(15.0);

        yellow.relocate(215, 300);
        pink.relocate(200, 200);
        cyan.relocate(100, 300);
        purple.relocate(200, 315);


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

    protected Group getGroup()
    {
        return g;
    }

    protected void setCyan(Color color)
    {
        cyan.setFill(color);
    }

    protected void setYellow(Color color)
    {
        yellow.setFill(color);
    }
}
