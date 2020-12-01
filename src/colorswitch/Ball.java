package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Ball extends GameElements{
    javafx.scene.shape.Circle ball;
    public Ball(int x, int y){
        super(x,y);
    }
    public int jump() {
        //launch();
        return 0;
    }

    public void setColor()
    {
        ArrayList<Paint> list=new ArrayList<>();
        list.add(Color.YELLOW);
        list.add(Color.DEEPPINK);
        list.add(Color.CYAN);
        list.add(Color.PURPLE);

        Paint toRemove=ball.getFill();
        list.remove(toRemove);
        Collections.shuffle(list);
        ball.setFill(list.get(0));
        list.add(toRemove);
    }

    public void moveUp()
    {
        ball.setLayoutY(ball.getLayoutY() - 55);

        //For smooth animation
        RotateTransition rotater1 = new RotateTransition(Duration.seconds(4), ball);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(1);
        rotater1.play();
    }

    @Override
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

    @Override
    public double getLayoutY()
    {
        //System.out.println("hi");
        return ball.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        ball.setLayoutY(dy);

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
