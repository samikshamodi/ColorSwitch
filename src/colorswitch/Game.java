package colorswitch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    ArrayList<Obstacle> obstacles;
    ArrayList<Star> stars;
    ArrayList<ColorSwitcher> colorSwitchers;
    Ball ball;
    static int currentScore;
    int i = 0;
    int N = 8;
    Label score;
    Button pause;

    Game() {
        currentScore = 0;
        ball = new Ball(10, 0);
        obstacles = new ArrayList<>();
        createObstacles();
        stars = new ArrayList<>();
        createStars();
        colorSwitchers = new ArrayList<>();
        createColorSwitchers();
    }


    void createObstacles() {
        for (int i = 0; i < 2; i++) {
            obstacles.add(new Square("square", 1, 1, 1));
            obstacles.add(new Circle("circle", 1, 1, 1));
            obstacles.add(new SpecialCircle("doubleCircle", 1, 1, 1));
            obstacles.add(new Triangle("triangle", 1, 1, 1));
            obstacles.add(new Plus("doublePlus", 1, 1, 1));
            obstacles.add(new Special("plus", 1, 1));
        }
    }

    void addObstacles(AnchorPane root) {
        obstacles.get(0).appear(root);
        obstacles.get(1).appear(root);
        obstacles.get(2).appear(root);
        obstacles.get(0).setLayoutY(0);
        obstacles.get(2).setLayoutY(-800);
    }


    void addBall(AnchorPane root) {
        ball.appear(root);
    }


    void createStars() {
        for (int i = 0; i < N; i++) {
            stars.add(new Star(1, 1, 1));
        }
    }

    void addStars(AnchorPane root) {
        stars.get(0).appear(root);
        stars.get(1).appear(root);
        stars.get(2).appear(root);
        stars.get(0).setLayoutY(0);
        stars.get(2).setLayoutY(-800);
    }

    void createColorSwitchers() {
        for (int i = 0; i < N; i++) {
            colorSwitchers.add(new ColorSwitcher(1, 1));
        }
    }

    void addColorSwitchers(AnchorPane root) {
        colorSwitchers.get(0).appear(root);
        colorSwitchers.get(1).appear(root);
        colorSwitchers.get(2).appear(root);
        colorSwitchers.get(0).setLayoutY(50);
        colorSwitchers.get(2).setLayoutY(-3 * 700);
    }


    public int startGame(Stage stage) throws IOException {
        //Setting the game scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/gui/StartGameScene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        BackgroundImage myBI = new BackgroundImage(new Image("/assets/pauseButton.png", 85, 85, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pause = new Button("");
        pause.setBackground(new Background(myBI));
        pause.setPrefHeight(85);
        pause.setPrefWidth(85);
        pause.setTranslateX(500);
        pause.setTranslateY(20);

        score = new Label("0");
        score.setFont(new Font("Comic Sans", 74));
        score.setTextFill(Color.WHITE);
        score.prefWidth(140);
        score.prefHeight(90);
        score.setLayoutX(37);
        score.setLayoutY(9);
        root.getChildren().addAll(pause, score);

        //changing objects in scene
        addObstacles(root);
        addBall(root);
        addStars(root);
        addColorSwitchers(root);

        playGame(stage, root);

        //set Scene and show stage
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();

        return 0;
    }

    public void playGame(Stage stage, AnchorPane root) {
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            int flag = 0;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (flag == 1) {
                    ball.jump();
                } else {
                    if (flag == 0) {
                        ball.jump();
                        flag = 1;
                    }
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            //move the ball
                            ball.moveDown();


                            Bounds bounds = root.getBoundsInLocal();

                            //TODO handling crash condition and game over menu
                            if (ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) {
                                System.out.println("Crashed :(");
                            }

                            //to not allow the ball to go above a certain height on screen.
                            if (ball.getLayoutY() <= 300) {
                                ball.stay();
                                //System.out.println("reached middle of screen");

                                //now move the obstacle down to give the illusion of screen moving down
                                colorSwitchers.get(i % 8).moveDown();
                                colorSwitchers.get((i + 1) % 8).moveDown();
                                colorSwitchers.get((i + 2) % 8).moveDown();

                                stars.get(i % 8).moveDown();
                                stars.get((i + 1) % 8).moveDown();
                                stars.get((i + 2) % 8).moveDown();

                                obstacles.get(i % 8).moveDown();
                                obstacles.get((i + 1) % 8).moveDown();
                                obstacles.get((i + 2) % 8).moveDown();

                                /* if obstacles are not on screen anymore then remove them from roor*/
                                if (obstacles.get(i % 8).getLayoutY() >= 800) {
                                    obstacles.get(i % 8).disappear(root);
                                    i++;
                                    obstacles.get((i + 2) % 8).appear(root);
                                    stars.get((i + 2) % 8).appear(root);
                                    colorSwitchers.get((i + 2) % 8).appear(root);
                                }

                            }

                            hitObstacle();
                            collectColorSwitcher(root);
                            collectStars(root, score);

                            //ensures obstacles are infinite
                            if (i == 4) {
                                Collections.shuffle(obstacles.subList(0, 4));
                            }
                        }
                    }));

                    //TODO move up before ball jump
                    pause.setOnAction(e -> {
                        try {
                            timeline.pause();
                            int ans = pauseGame(stage);
                            System.out.println("Received: " + ans);
                            if (ans == 1) {
                                timeline.play();
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        });
    }

    int pauseGame(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/gui/PauseGame.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        AtomicInteger flag = new AtomicInteger();

        //resume game
        BackgroundImage myBI = new BackgroundImage(new Image("/assets/resumeButton.png", 200, 200, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button resume = new Button("");
        resume.setBackground(new Background(myBI));
        resume.setPrefHeight(200);
        resume.setPrefWidth(200);
        resume.setTranslateX(225);
        resume.setTranslateY(150);
        resume.setOnAction(e -> {
            flag.set(1);
        });

        //restart game
        Button restart = new Button("Restart Game");
        restart.setMinWidth(400);
        restart.setMinHeight(50);
        restart.setLayoutX(100);
        restart.setLayoutY(400);
        restart.setFont(new Font("Courier New Bold", 40));
        restart.setTextFill(Color.WHITE);
        restart.setStyle("-fx-background-color: black");
        restart.setOnAction(e -> {
            flag.set(2);
        });

        //save game
        Button save = new Button("Save Game");
        save.setMinWidth(400);
        save.setMinHeight(50);
        save.setLayoutX(100);
        save.setLayoutY(500);
        save.setFont(new Font("Courier New Bold", 40));
        save.setTextFill(Color.WHITE);
        save.setStyle("-fx-background-color: black");
        save.setOnAction(e -> {
            flag.set(3);
        });

        root.getChildren().addAll(resume, restart, save);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
        return flag.intValue();
    }

    int hitObstacle() {
        /*check if collision*/
        int collisionDetected1 = obstacles.get(i % 8).checkCollision(ball.getShape());
        int collisionDetected2 = obstacles.get((i + 1) % 8).checkCollision(ball.getShape());
        int collisionDetected3 = obstacles.get((i + 2) % 8).checkCollision(ball.getShape());
        if (collisionDetected1 == 1 || collisionDetected2 == 1 || collisionDetected3 == 1) {
            System.out.println("Collision detected");
            System.out.println("Game Over");
            //TODO add the game over menu
        }
        return 0;
    }

    int collectStars(AnchorPane root, Label score) {

        /* check if star collected*/
        int starCollected1 = stars.get(i % 8).checkCollision(ball.getShape());
        int starCollected2 = stars.get((i + 1) % 8).checkCollision(ball.getShape());
        int starCollected3 = stars.get((i + 2) % 8).checkCollision(ball.getShape());
        if (starCollected1 == 1)
            stars.get(i % 8).disappear(root);
        else if (starCollected2 == 1)
            stars.get((i + 1) % 8).disappear(root);
        else if (starCollected3 == 1)
            stars.get((i + 2) % 8).disappear(root);

        /*if star was hit then update score*/
        if (starCollected1 == 1 || starCollected2 == 1 || starCollected3 == 1) {
            root.getChildren().remove(score);
            currentScore += 1;
            score.setText("" + currentScore);
            root.getChildren().add(score);
        }
        return 0;
    }

    String collectColorSwitcher(AnchorPane root) {

        /*check if color switcher collected*/
        int csCollected1 = colorSwitchers.get(i % 8).checkCollision(ball.getShape());
        int csCollected2 = colorSwitchers.get((i + 1) % 8).checkCollision(ball.getShape());
        int csCollected3 = colorSwitchers.get((i + 2) % 8).checkCollision(ball.getShape());

        //if colorswitcher collected
        if (csCollected1 == 1 || csCollected2 == 1 || csCollected3 == 1) {
            System.out.println("Color Switcher Collected");
            if (csCollected1 == 1) {
                ball.setColor(colorSwitchers.get(i % 8).generateColor(ball.getColor()));
                colorSwitchers.get(i % 8).disappear(root);
            } else if (csCollected2 == 1) {
                ball.setColor(colorSwitchers.get(i % 8).generateColor(ball.getColor()));
                colorSwitchers.get((i + 1) % 8).disappear(root);
            } else if (csCollected3 == 1) {
                ball.setColor(colorSwitchers.get(i % 8).generateColor(ball.getColor()));
                colorSwitchers.get((i + 2) % 8).disappear(root);
            }
        }
        return " ";
    }

    int fall() {
        return 2;
    }

}
