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
    ArrayList<Obstacle> list = new ArrayList<>();
    ArrayList<Star> starList = new ArrayList<>();
    ArrayList<ColorSwitcher> csList = new ArrayList<>();
    Star st;    //TODO remove??
    ColorSwitcher cs; //TODO remove??
    Ball ball;


    void createObstacles() {
        list.add(new Square("ty", 1, 1, 1));
        //list.add(new Triangle("ty", 1, 1, 1));
        list.add(new Circle("ty", 1, 1, 1));
        list.add(new Square("ty", 1, 1, 1));

        for (int i = 0; i < 100; i++) {
            list.add(new Circle("ty", 1, 1, 1));
            list.add(new Square("ty", 1, 1, 1));
            list.add(new Triangle("ty", 1, 1, 1));
        }
       // Collections.shuffle(list);//TODO remove comments
    }

    void addObstacles(AnchorPane root) {
        list.get(0).appear(root);
        list.get(1).appear(root);
        list.get(2).appear(root);
        list.get(0).setLayoutY(0);
        list.get(2).setLayoutY(-800);
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

    void createStar() {
        for (int i = 0; i < 100; i++) {
            starList.add(new Star(1, 1, 1));
        }
    }

    void addStar(AnchorPane root) {
        starList.get(0).appear(root);
        starList.get(1).appear(root);
        starList.get(2).appear(root);
        starList.get(0).setLayoutY(0);
        starList.get(2).setLayoutY(-800);
    }

    void createColorSwitcher() {
        for (int i = 0; i < 100; i++) {
            csList.add(new ColorSwitcher(1, 1));
        }
    }

    void addColorSwitcher(AnchorPane root) {
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
        createStar();
        addStar(root);
        createColorSwitcher();
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

                            //TODO add code to move the obstacle down
                            //to not allow the ball to go above a certain height on screen.
                            if (ball.getLayoutY() <= 300) {
                                ball.stay();
                                //System.out.println("reached middle of screen");


                                //now move the obstacle down to give the illusion of screen moving down
                                //st.moveDown();
                               // cs.moveDown();
                                csList.get(i).moveDown();
                                csList.get(i+1).moveDown();
                                csList.get(i+2).moveDown();

                                starList.get(i).moveDown();
                                starList.get(i + 1).moveDown();
                                starList.get(i + 2).moveDown();

                                list.get(i).moveDown();
                                list.get(i + 1).moveDown();
                                list.get(i + 2).moveDown();

                                if (list.get(i).getLayoutY() >= 800) {
                                    //System.out.println(i+"-------");
                                    i++;
                                    list.get(i + 2).appear(root);
                                    starList.get(i + 2).appear(root);
                                    csList.get(i + 2).appear(root);

                                }

                            }

                            //ensures obstacles are infinite
                            if (i == 90) {
                                createObstacles();
                                createStar();
                                createColorSwitcher();
                            }

                            //if return value is 1 that means the star was collected.
                            //if the return value is 0 that means the star was not collected
                            //check if star collected
                            /*int starCollected = st.checkCollision(ball.getShape());
                            if (starCollected == 1) {
                                //TODO add sound and multiple stars and +1
                                //TODO update player score
                                System.out.println("collected star");
                                st.disappear(root);
                            }*/

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


                            //if return value is 1 that means the color switcher was collected.
                            //if the return value is 0 that means the color switcher was not collected
                            //check if color switcher collected
                          /*  int colorSwitcherCollected = cs.checkCollision(ball.getShape());
                            if (colorSwitcherCollected == 1) {
                                //TODO add code for changing color of the ball and sound effect
                                System.out.println("collected color switcher");
                                cs.disappear(root);
                            }*/
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
                            int collisionDetected1 = list.get(i).checkCollision(ball.getShape());
                            int collisionDetected2 = list.get(i + 1).checkCollision(ball.getShape());
                            int collisionDetected3 = list.get(i + 2).checkCollision(ball.getShape());
                            if (collisionDetected1 == 1 || collisionDetected2 == 1 || collisionDetected3 == 1) {
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
