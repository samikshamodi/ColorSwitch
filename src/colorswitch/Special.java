package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Special extends Obstacle{
    Rectangle yellow, pink, cyan, purple;
    Rectangle yellow2, pink2, cyan2, purple2;
    Group g,g1,g2;
    Special(String ty,int x,int y){
        super(ty,x,y);
    }

    @Override
    public void disappear(AnchorPane root) {

    }


    @Override
    public void appear(AnchorPane root) {
        yellow = new Rectangle(100, 15, Color.YELLOW);
        pink = new Rectangle(15, 100, Color.DEEPPINK);
        cyan = new Rectangle(100, 15, Color.CYAN);
        purple = new Rectangle(15, 100, Color.PURPLE);

        yellow.relocate(215, 300);
        pink.relocate(200, 200);
        cyan.relocate(100, 300);
        purple.relocate(200, 315);

        g1 = new Group();
        g1.getChildren().addAll(yellow, pink, cyan, purple);
        RotateTransition rotater1 = new RotateTransition(Duration.seconds(5), g1);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(1500);
        rotater1.play();

        yellow2 = new Rectangle(100, 15, Color.YELLOW);
        pink2 = new Rectangle(15, 100, Color.DEEPPINK);
        cyan2 = new Rectangle(100, 15, Color.CYAN);
        purple2 = new Rectangle(15, 100, Color.PURPLE);

        yellow2.relocate(315, 300);
        pink2.relocate(415, 200);
        cyan2.relocate(430, 300);
        purple2.relocate(415, 315);

        g2 = new Group();
        g2.getChildren().addAll(yellow2, pink2,cyan2, purple2);
        RotateTransition rotater2 = new RotateTransition(Duration.seconds(5), g2);
        rotater2.setByAngle(-360);
        rotater2.setCycleCount(1500);
        rotater2.play();

        g=new Group();
        g.getChildren().addAll(g1,g2);
        g.setLayoutY(-400);
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
