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

    Square(String ty, int x, int y, double s) {
        super(ty, x, y);
        side = s;
    }


    @Override
    public void disappear(AnchorPane root) {

    }

    @Override
    public void appear(AnchorPane root) {
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
}
