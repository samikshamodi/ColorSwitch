package colorswitch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.animation.AnimationTimer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Ball extends GameElements {
    javafx.scene.shape.Circle ball;

    public Ball(int x, int y) {
        super(x, y);
        ball = new javafx.scene.shape.Circle(10, Color.YELLOW);
        ball.relocate(295, 600);
    }

    public Color getColor()
    {
        return (Color) ball.getFill();
    }

    public void setColor(Color color) {
        ball.setFill(color);
    }

    public void jump() {
        AnimationTimer animationTimer = new AnimationTimer() {
            int x=0;
            @Override
            public void handle(long l) {
                if(x>=55)
                {
                    stop();
                }
                ball.setLayoutY(ball.getLayoutY()-10);
                x+=10;
            }
        };
        animationTimer.start();
    }

    @Override
    public void moveDown() {
        ball.setLayoutY(ball.getLayoutY() + 3); //3 is step or velocity
    }

    public void stay() {
        ball.setLayoutY(ball.getLayoutY() + 55);
    }

    public double getRadius() {
        return ball.getRadius();
    }

    @Override
    public double getLayoutY() {
        return ball.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        ball.setLayoutY(dy);
    }


    public Shape getShape() {
        return ball;
    }

    @Override
    public void appear(AnchorPane root) {
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