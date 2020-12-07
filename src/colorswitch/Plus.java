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
    double side;
    Rectangle yellow, pink, cyan, purple;
    Group g;
    RotateTransition rotater1;
    ArrayList<Shape> shapeList;

    Plus(String ty, int x, int y, double s) {
        super(ty, x, y);
        side = s;
        shapeList=new ArrayList<>();

        yellow = new Rectangle(100, 15, Color.YELLOW);
        pink = new Rectangle(15, 100, Color.DEEPPINK);
        cyan = new Rectangle(100, 15, Color.CYAN);
        purple = new Rectangle(15, 100, Color.PURPLE);

        yellow.relocate(215, 300);
        pink.relocate(200, 200);
        cyan.relocate(100, 300);
        purple.relocate(200, 315);

        g = new Group();
        g.getChildren().addAll(yellow, pink, cyan, purple);

        rotater1 = new RotateTransition(Duration.seconds(4), g);
        rotater1.setCycleCount(1500);

        shapeList.add(yellow);
        shapeList.add(pink);
        shapeList.add(cyan);
        shapeList.add(purple);
    }


    @Override
    public void disappear(AnchorPane root) {
        root.getChildren().remove(g);
    }

    @Override
    public void appear(AnchorPane root) {
        g.setLayoutY(-400);
        rotater1.setByAngle(360);
        rotater1.play();
        root.getChildren().add(g);
    }

    @Override
    public int checkCollision(Shape ball) {
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
        g.setLayoutY(g.getLayoutY() + 55); //3 is step or velocity
    }



    public double getLayoutY()
    {
        return g.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        g.setLayoutY(dy);

    }

    public Group getGroup()
    {
        return g;
    }

    public void setCyan(Color color)
    {
        cyan.setFill(color);
    }

    public void setYellow(Color color)
    {
        yellow.setFill(color);
    }
}
