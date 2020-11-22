package colorswitch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Ball extends GameElements{
    Ball(int x, int y){
        super(x,y);
    }
    public int jump() {
        //launch();
        return 0;
    }

    @Override
    void appear(AnchorPane root) {
        javafx.scene.shape.Circle ball = new javafx.scene.shape.Circle(10, Color.YELLOW);
        ball.relocate(295, 600);
        root.getChildren().add(ball);
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            int flag = 0;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (flag == 1) {
                    ball.setLayoutY(ball.getLayoutY() - 55);
                } else {
                    if (flag == 0) {
                        ball.setLayoutY(ball.getLayoutY() - 55);
                        flag = 1;
                    }
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                        double dy = 3; //Step on y

                        @Override
                        public void handle(ActionEvent t) {
                            //move the ball
                            ball.setLayoutY(ball.getLayoutY() + dy);

                            Bounds bounds = root.getBoundsInLocal();

                            //TODO handling crash condition
                            //If the ball reaches the bottom or top border make the step negative
                            if ((ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) ||
                                    (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius()))) {

                                //dy=-dy; for going up and down
                                dy = bounds.getMinY();
                            }
                        }
                    }));
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        });
    }

    @Override
    void disappear() {

    }

}
