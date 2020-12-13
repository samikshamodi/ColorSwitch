package colorswitch;

import javafx.animation.AnimationTimer;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Star> stars;
    private ArrayList<ColorSwitcher> colorSwitchers;
    private Ball ball;
    static int currentScore;    //TODO make private??
    private int i = 0;
    private int N = 8;
    private Label score;
    private Button pause;
    private AnimationTimer animationTimer;

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
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<>() {
            int flag = 0;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (flag == 1) {
                    ball.jump();
                } else {
                    ball.jump();
                    flag = 1;

                    animationTimer = new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            ball.moveDown();
                            Bounds bounds = root.getBoundsInLocal();

                            //TODO handling crash condition and game over menu
                            if (ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) {
                                System.out.println("Crashed :(");
                            }

                            /*to not allow the ball to go above a certain height on screen.*/
                            if (ball.getLayoutY() <= 300) {
                                ball.stay();
                                moveElementsDown(root);
                            }

                            /*check for all collisions*/
                            hitObstacle();
                            collectColorSwitcher(root);
                            collectStars(root, score);

                            /*ensures obstacles are infinite and randomised*/
                            if (i == 4) {
                                Collections.shuffle(obstacles.subList(0, 4));
                            }
                        }
                    };

                    animationTimer.start();
                }

                pause.setOnAction(e -> {
                    try {
                        animationTimer.stop();
                        int ans = pauseGame(stage);
                        System.out.println("Received: " + ans);//TODO check this condition
                        if (ans == 1) {
                            animationTimer.start();
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            }
        });
    }

    private void moveElementsDown(AnchorPane root) {
        //now move the obstacle down to give the illusion of screen moving down
        colorSwitchers.get(i % N).moveDown();
        colorSwitchers.get((i + 1) % N).moveDown();
        colorSwitchers.get((i + 2) % N).moveDown();

        stars.get(i % N).moveDown();
        stars.get((i + 1) % N).moveDown();
        stars.get((i + 2) % N).moveDown();

        obstacles.get(i % N).moveDown();
        obstacles.get((i + 1) % N).moveDown();
        obstacles.get((i + 2) % N).moveDown();

        /* if obstacles are not on screen anymore then remove them from root and add the top screen element*/
        if (obstacles.get(i % N).getLayoutY() >= 800) {
            obstacles.get(i % N).disappear(root);
            i++;
            obstacles.get((i + 2) % N).appear(root);
            stars.get((i + 2) % N).appear(root);
            colorSwitchers.get((i + 2) % N).appear(root);
        }
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
        resume.setOnAction(e -> flag.set(1));

        //restart game
        Button restart = new Button("Restart Game");
        restart.setMinWidth(400);
        restart.setMinHeight(50);
        restart.setLayoutX(100);
        restart.setLayoutY(400);
        restart.setFont(new Font("Courier New Bold", 40));
        restart.setTextFill(Color.WHITE);
        restart.setStyle("-fx-background-color: black");
        restart.setOnAction(e -> flag.set(2));

        //save game
        Button save = new Button("Save Game");
        save.setMinWidth(400);
        save.setMinHeight(50);
        save.setLayoutX(100);
        save.setLayoutY(500);
        save.setFont(new Font("Courier New Bold", 40));
        save.setTextFill(Color.WHITE);
        save.setStyle("-fx-background-color: black");
        save.setOnAction(e -> flag.set(3));

        root.getChildren().addAll(resume, restart, save);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
        return flag.intValue();
    }

    int hitObstacle() {
        /*check if collision*/
        int collisionDetected1 = obstacles.get(i % N).checkCollision(ball.getShape());
        int collisionDetected2 = obstacles.get((i + 1) % N).checkCollision(ball.getShape());
        int collisionDetected3 = obstacles.get((i + 2) % N).checkCollision(ball.getShape());
        if (collisionDetected1 == 1 || collisionDetected2 == 1 || collisionDetected3 == 1) {
            System.out.println("Collision detected");
            System.out.println("Game Over");
            //TODO add the game over menu
        }
        return 0;
    }

    int collectStars(AnchorPane root, Label score) {

        /* check if star collected*/
        int starCollected1 = stars.get(i % N).checkCollision(ball.getShape());
        int starCollected2 = stars.get((i + 1) % N).checkCollision(ball.getShape());
        int starCollected3 = stars.get((i + 2) % N).checkCollision(ball.getShape());
        if (starCollected1 == 1)
            stars.get(i % N).disappear(root);
        else if (starCollected2 == 1)
            stars.get((i + 1) % N).disappear(root);
        else if (starCollected3 == 1)
            stars.get((i + 2) % N).disappear(root);

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
        int csCollected1 = colorSwitchers.get(i % N).checkCollision(ball.getShape());
        int csCollected2 = colorSwitchers.get((i + 1) % N).checkCollision(ball.getShape());
        int csCollected3 = colorSwitchers.get((i + 2) % N).checkCollision(ball.getShape());

        //if colorswitcher collected
        if (csCollected1 == 1 || csCollected2 == 1 || csCollected3 == 1) {
            System.out.println("Color Switcher Collected");
            if (csCollected1 == 1) {
                ball.setColor(colorSwitchers.get(i % N).generateColor(ball.getColor()));
                colorSwitchers.get(i % N).disappear(root);
            } else if (csCollected2 == 1) {
                ball.setColor(colorSwitchers.get(i % N).generateColor(ball.getColor()));
                colorSwitchers.get((i + 1) % N).disappear(root);
            } else if (csCollected3 == 1) {
                ball.setColor(colorSwitchers.get(i % N).generateColor(ball.getColor()));
                colorSwitchers.get((i + 2) % N).disappear(root);
            }
        }
        return " ";
    }

    int fall() {
        return 2;
    }

}
