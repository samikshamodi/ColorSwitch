package colorswitch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

import java.io.IOException;
import java.util.*;

public class Game {
    HashMap<Integer, Obstacle> obstacles;
    HashMap<Integer, ColorSwitcher> colorswitchers;
    HashMap<Integer, Star> stars;
    ArrayList<Obstacle> obstacleList = new ArrayList<>();
    ArrayList<Star> starList = new ArrayList<>();
    ArrayList<ColorSwitcher> csList = new ArrayList<>();
    Ball ball;


    void createObstacles() {
        obstacleList.add(new Square("ty", 1, 1, 1));
        //list.add(new Triangle("ty", 1, 1, 1));
        obstacleList.add(new Circle("ty", 1, 1, 1));
        // list.add(new Special("ty",1, 1));
        obstacleList.add(new Square("ty", 1, 1, 1));

        for (int i = 0; i < 100; i++) {
            obstacleList.add(new Circle("ty", 1, 1, 1));
            obstacleList.add(new Square("ty", 1, 1, 1));
            obstacleList.add(new Triangle("ty", 1, 1, 1));
            obstacleList.add(new Special("ty", 1, 1));
        }
        Collections.shuffle(obstacleList.subList(3, obstacleList.size()));//TODO remove comments
    }

    void addObstacles(AnchorPane root) {
        obstacleList.get(0).appear(root);
        obstacleList.get(1).appear(root);
        obstacleList.get(2).appear(root);
        obstacleList.get(0).setLayoutY(0);
        obstacleList.get(2).setLayoutY(-800);
    }


    Obstacle removeObstacles() {
        return new colorswitch.Circle("Solid", 0, 0, 10.5);
    }

    void addBall(AnchorPane root) {
        ball = new Ball(10, 0);
        ball.appear(root);
    }

    ColorSwitcher removeColorSwitcher() {
        return new ColorSwitcher(0, 0);
    }

    void createStars() {
        for (int i = 0; i < 100; i++) {
            starList.add(new Star(1, 1, 1));
        }
    }

    void addStars(AnchorPane root) {
        starList.get(0).appear(root);
        starList.get(1).appear(root);
        starList.get(2).appear(root);
        starList.get(0).setLayoutY(0);
        starList.get(2).setLayoutY(-800);
    }

    void createColorSwitchers() {
        for (int i = 0; i < 100; i++) {
            csList.add(new ColorSwitcher(1, 1));
        }
    }

    void addColorSwitchers(AnchorPane root) {
        csList.get(0).appear(root);
        csList.get(1).appear(root);
        csList.get(2).appear(root);
        csList.get(0).setLayoutY(50);
        csList.get(2).setLayoutY(-700);
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
        createObstacles();
        addObstacles(root);
        addBall(root);
        createStars();
        addStars(root);
        createColorSwitchers();
        addColorSwitchers(root);

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
                        int i = 0;

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

                            //to not allow the ball to go above a certain height on screen.
                            if (ball.getLayoutY() <= 300) {
                                ball.stay();
                                //System.out.println("reached middle of screen");


                                //now move the obstacle down to give the illusion of screen moving down
                                csList.get(i).moveDown();
                                csList.get(i + 1).moveDown();
                                csList.get(i + 2).moveDown();

                                starList.get(i).moveDown();
                                starList.get(i + 1).moveDown();
                                starList.get(i + 2).moveDown();

                                obstacleList.get(i).moveDown();
                                obstacleList.get(i + 1).moveDown();
                                obstacleList.get(i + 2).moveDown();

                                if (obstacleList.get(i).getLayoutY() >= 800) {
                                    //System.out.println(i+"-------");
                                    i++;
                                    obstacleList.get(i + 2).appear(root);
                                    starList.get(i + 2).appear(root);
                                    csList.get(i + 2).appear(root);

                                }

                            }

                            //ensures obstacles are infinite
                            if (i == 90) {
                                createObstacles();
                                createStars();
                                createColorSwitchers();
                            }

                            /*if return value is 1 that means the star was collected.
                            if the return value is 0 that means the star was not collected*/
                            // check if star collected
                            int starCollected1 = starList.get(i).checkCollision(ball.getShape());
                            int starCollected2 = starList.get(i + 1).checkCollision(ball.getShape());
                            int starCollected3 = starList.get(i + 2).checkCollision(ball.getShape());
                            if (starCollected1 == 1 || starCollected2 == 1 || starCollected3 == 1) {
                                System.out.println("Star Collected");
                                if (starCollected1 == 1)
                                    starList.get(i).disappear(root);
                                else if (starCollected2 == 1)
                                    starList.get(i + 1).disappear(root);
                                else if (starCollected1 == 3)
                                    starList.get(i + 2).disappear(root);
                            }


                            /*if return value is 1 that means the color switcher was collected.
                            if the return value is 0 that means the color switcher was not collected*/
                            //check if color switcher collected
                            int csCollected1 = csList.get(i).checkCollision(ball.getShape());
                            int csCollected2 = csList.get(i + 1).checkCollision(ball.getShape());
                            int csCollected3 = csList.get(i + 2).checkCollision(ball.getShape());
                            if (csCollected1 == 1 || csCollected2 == 1 || csCollected3 == 1) {
                                System.out.println("Color Switcher Collected");
                                if (csCollected1 == 1)
                                    csList.get(i).disappear(root);
                                else if (csCollected2 == 1)
                                    csList.get(i + 1).disappear(root);
                                else if (csCollected1 == 3)
                                    csList.get(i + 2).disappear(root);
                                ball.setColor();
                            }

                            //check if collision
                            //collisionDetected has value 1 if ball collides with the obstacle which is not of the
                            //same colour as ball
                            //else it returns 0
                            int collisionDetected1 = obstacleList.get(i).checkCollision(ball.getShape());
                            int collisionDetected2 = obstacleList.get(i + 1).checkCollision(ball.getShape());
                            int collisionDetected3 = obstacleList.get(i + 2).checkCollision(ball.getShape());
                            if (collisionDetected1 == 1 || collisionDetected2 == 1 || collisionDetected3 == 1) {
                                System.out.println("Collision detected");
                                System.out.println("Game Over");
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
