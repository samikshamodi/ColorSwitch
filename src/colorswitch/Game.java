package colorswitch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Game {
    HashMap<Integer, Obstacle> obstacles;
    HashMap<Integer, ColorSwitcher> colorswitchers;
    HashMap<Integer, Star> stars;
    Star st;    //TODO remove??
    ColorSwitcher cs; //TODO remove??
    Obstacle o1;
    Ball ball;

    void addObstacles(AnchorPane root) {
        o1 = new Square("jk", 10, 10, 10);
        o1.appear(root);
    }

    Obstacle removeObstacles() {
        return new colorswitch.Circle("Solid", 0, 0, 10.5);
    }

    void addBall(AnchorPane root) {
        ball = new Ball(10, 0);
        ball.appear(root);
    }

    void addColorSwitcher() {

    }

    ColorSwitcher removeColorSwitcher() {
        return new ColorSwitcher(0, 0);
    }

    void addStar(AnchorPane root) {
        st = new Star(1, 0, 0);
        st.appear(root);
    }

    void addColorSwitcher(AnchorPane root) {
        cs = new ColorSwitcher(0, 0);
        cs.appear(root);
    }

    Star removeStar() {
        return new Star(1, 0, 0);
    }

    public int startGame(Stage stage) throws IOException {

        //Setting the game scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/gui/StartGameScene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        BackgroundImage myBI = new BackgroundImage(new Image("/assets/pauseButton.png", 85, 85, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button pause = new Button("");
        pause.setBackground(new Background(myBI));
        pause.setPrefHeight(85);
        pause.setPrefWidth(85);
        pause.setTranslateX(500);
        pause.setTranslateY(20);
        pause.setOnAction(e -> {
            try {
                pause(stage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        root.getChildren().add(pause);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();

        //changing objects in scene
        addBall(root);
        addObstacles(root);
        addStar(root);
        addColorSwitcher(root);
        //TODO figure out how and when to add colorswitcher and stars

        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            int flag = 0;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (flag == 1) {
                    ball.moveUp();
                } else {
                    if (flag == 0) {
                        ball.moveUp();
                        flag = 1;
                    }
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            //move the ball
                            ball.moveDown();


                            Bounds bounds = root.getBoundsInLocal();

                            //TODO handling crash condition and game over menu
                            //If the ball reaches the bottom or top border make the step negative
                            //if (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius())||ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) {
                            if (ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) {

                                //dy=-dy; for going up and down
                                //dy = bounds.getMinY();
                                System.out.println("Crashed :(");
                            }

                            //TODO add code to move the obstacle down
                            //to not allow the ball to go above a certain height on screen.
                            if (ball.getLayoutY() <= 300) {
                                ball.stay();
                                System.out.println("reached middle of screen");
                                //now move the obstacle down to give the illusion of screen moving down
                            }

                            //if return value is 1 that means the star was collected.
                            //if the return value is 0 that means the star was not collected
                            //check if star collected
                            int starCollected = st.checkCollision(ball.getShape());
                            if (starCollected == 1) {
                                //TODO add sound and multiple stars and +1
                                //TODO update player score
                                System.out.println("collected star");
                                st.disappear(root);
                            }

                            //if return value is 1 that means the color switcher was collected.
                            //if the return value is 0 that means the color switcher was not collected
                            //check if color switcher collected
                            int colorSwitcherCollected = cs.checkCollision(ball.getShape());
                            if (colorSwitcherCollected == 1) {
                                //TODO add code for changing color of the ball and sound effect
                                System.out.println("collected color switcher");
                                cs.disappear(root);
                            }

                            //check if collision
                            //collisionDetected has value 1 if ball collides with the obstacle which is not of the
                            //same colour as ball
                            //else it returns 0
                            int collisionDetected = o1.checkCollision(ball.getShape());
                            if (collisionDetected == 1) {
                                System.out.println("Collision detected");
                                System.out.println("Game Over");
                                //stage.close();  //TODO remove
                                //TODO add the game over menu
                            }
                        }
                    }));
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        });

        return 0;
    }

    int pause(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/gui/PauseGame.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        BackgroundImage myBI = new BackgroundImage(new Image("/assets/resumeButton.png", 200, 200, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button resume = new Button("");
        resume.setBackground(new Background(myBI));
        resume.setPrefHeight(200);
        resume.setPrefWidth(200);
        resume.setTranslateX(225);
        resume.setTranslateY(150);
        root.getChildren().add(resume);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
        return 0;
    }

    int hitObstacle() {
        return 3;
    }

    int collectStars() {
        return -1;
    }

    String collectColorSwitcher() {
        return " ";
    }

    int fall() {
        return 2;
    }


}
