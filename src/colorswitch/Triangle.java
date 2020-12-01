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
            if (pink.getFill().equals(ball.getFill())) {
                return 0;
            }
            else
            {
                System.out.println("Collision detected Pink");
                return 1;
            }
        }

        if (cyanIntersect.getBoundsInLocal().getWidth() != -1) {
            if (cyan.getFill().equals(ball.getFill())) {
                return 0;
            }
            else
            {
                System.out.println("Collision detected Cyan");
                return 1;
            }
        }

        if (purpleIntersect.getBoundsInLocal().getWidth() != -1) {
            if (purple.getFill().equals(ball.getFill())) {
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
