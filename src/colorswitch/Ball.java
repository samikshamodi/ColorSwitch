package colorswitch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ball extends Application {
    public int jump() {
        launch();
        return 0;
    }

    @Override
    public void start(Stage stage) {

        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 600, 800, Color.BLACK);
        Circle ball = new Circle(10, Color.RED);
        ball.relocate(295, 600);

        canvas.getChildren().add(ball);

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            int flag = 0;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (flag == 1) {
                    ball.setLayoutY(ball.getLayoutY() - 40);
                } else {
                    if (flag == 0) {
                        ball.setLayoutY(ball.getLayoutY() - 40);
                        flag = 1;
                    }
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                        double dy = 3; //Step on y

                        @Override
                        public void handle(ActionEvent t) {
                            //move the ball
                            ball.setLayoutY(ball.getLayoutY() + dy);

                            Bounds bounds = canvas.getBoundsInLocal();

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
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();
    }
}
