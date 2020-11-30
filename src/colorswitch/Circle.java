package colorswitch;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Circle extends Obstacle {
    double radius;
    Rectangle yellow, pink, cyan, purple;
    Group g;

    public Circle(String ty, int x, int y, double r) {
        super(ty, x, y);
        radius = r;
    }

    @Override
    public void disappear(AnchorPane root) {

    }

    @Override
    public void appear(AnchorPane root) {

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
            } else {
                System.out.println("Collision detected Yellow");
                return 1;
            }
        }

        if (pinkIntersect.getBoundsInLocal().getWidth() != -1) {
            if (pinkIntersect.getFill().equals(ball.getFill())) {
                return 0;
            } else {
                System.out.println("Collision detected Pink");
                return 1;
            }
        }

        if (cyanIntersect.getBoundsInLocal().getWidth() != -1) {
            if (cyanIntersect.getFill().equals(ball.getFill())) {
                return 0;
            } else {
                System.out.println("Collision detected Cyan");
                return 1;
            }
        }

        if (purpleIntersect.getBoundsInLocal().getWidth() != -1) {
            if (purpleIntersect.getFill().equals(ball.getFill())) {
                return 0;
            } else {
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
    public double getLayoutY() {
        return g.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        g.setLayoutY(-400);
    }


}
