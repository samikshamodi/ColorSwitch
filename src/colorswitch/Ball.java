package colorswitch;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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
        Circle ball = new Circle(10, Color.AQUA);
        ball.relocate(295, 600);
        ImageView img = new ImageView("/assets/c1.png");
        img.setFitHeight(150);
        img.setFitWidth(150);
        img.setX(225.5);
        img.setY(200);
        img.setPreserveRatio(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4),img);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1500);
        canvas.getChildren().addAll(ball,img);

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
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
        rotateTransition.play();
        stage.setScene(scene);
        stage.show();
    }
}
