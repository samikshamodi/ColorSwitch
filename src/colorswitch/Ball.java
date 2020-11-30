package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Ball extends GameElements{
    javafx.scene.shape.Circle ball;
    public Ball(int x, int y){
        super(x,y);
    }
    public int jump() {
        //launch();
        return 0;
    }

    public void moveUp()
    {
        ball.setLayoutY(ball.getLayoutY() - 55);

        //For smooth animation
        RotateTransition rotater1 = new RotateTransition(Duration.seconds(3), ball);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(3);
        rotater1.play();
    }

    public void moveDown()
    {
        ball.setLayoutY(ball.getLayoutY() + 3); //3 is step or velocity
    }

    public void stay()
    {
        ball.setLayoutY(ball.getLayoutY()+55);
    }

    public double getRadius()
    {
        return ball.getRadius();
    }

    public double getLayoutY()
    {
        //System.out.println("hi");
        return ball.getLayoutY();
    }

    public void setLayoutY(double x)
    {
        ball.setLayoutY(x);
    }

    public Shape getShape()
    {
        return ball;
    }

    @Override
    public void appear(AnchorPane root) {
        ball = new javafx.scene.shape.Circle(10, Color.YELLOW);
        ball.relocate(295, 600);
        root.getChildren().add(ball);
    }

    @Override
    public int checkCollision(Shape ball) {
        return 0;
    }

    @Override
    public void disappear(AnchorPane root) {

    }

}
