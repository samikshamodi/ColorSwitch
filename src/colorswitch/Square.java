package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Square extends Obstacle {
    double side;
    Rectangle yellow, pink, cyan, purple;
    Group g;

    Square(String ty, int x, int y, double s) {
        super(ty, x, y);
        side = s;
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
        Shape yellowIntersect = Shape.intersect(ball, yellow);
        Shape pinkIntersect = Shape.intersect(ball, pink);
        Shape cyanIntersect = Shape.intersect(ball, cyan);
        Shape purpleIntersect = Shape.intersect(ball, purple);

        //Collision with r1 Yellow
        if (yellowIntersect.getBoundsInLocal().getWidth() != -1) {
            if (yellow.getFill().equals(ball.getFill())) {
                return 0;
            }
            else
            {
                System.out.println("Collision detected Yellow");
                return 1;
            }
        }

        if (pinkIntersect.getBoundsInLocal().getWidth() != -1) {
            if (pinkIntersect.getFill().equals(ball.getFill())) {
                return 0;
            }
            else
            {
                System.out.println("Collision detected Pink");
                return 1;
            }
        }

        if (cyanIntersect.getBoundsInLocal().getWidth() != -1) {
            if (cyanIntersect.getFill().equals(ball.getFill())) {
                return 0;
            }
            else
            {
                System.out.println("Collision detected Cyan");
                return 1;
            }
        }

        if (purpleIntersect.getBoundsInLocal().getWidth() != -1) {
            if (purpleIntersect.getFill().equals(ball.getFill())) {
                return 0;
            }
            else
            {
                System.out.println("Collision detected Purple");
                return 1;
            }
        }

        return 0;
    }

    @Override
    public void moveDown() {
       g.setLayoutY(g.getLayoutY() + 55); //3 is step or velocity
    }


    @Override
    public void disappear(AnchorPane root) {

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
