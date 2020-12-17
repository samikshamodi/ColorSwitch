package colorswitch;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    private final GameModel newG;
    private Label score;
    private Button pause;
    private AnimationTimer animationTimer;

    Game(GameModel G) {
        newG = G;
    }

    private void addObstacles(AnchorPane root, boolean loaded) {
        newG.obstacles.get(newG.i % newG.N).create();
        newG.obstacles.get((newG.i + 1) % newG.N).create();
        newG.obstacles.get((newG.i + 2) % newG.N).create();
        newG.obstacles.get((newG.i) % newG.N).appear(root);
        newG.obstacles.get((newG.i + 1) % newG.N).appear(root);
        newG.obstacles.get((newG.i + 2) % newG.N).appear(root);
        if (!loaded) {
            newG.obstacles.get((newG.i) % newG.N).setLayoutY(0);
            newG.obstacles.get((newG.i + 2) % newG.N).setLayoutY(-800);
        }
    }


    private void addBall(AnchorPane root) {
        newG.ball.appear(root);
    }


    private void addStars(AnchorPane root, boolean loaded) {
        newG.stars.get(newG.i % newG.N).create();
        newG.stars.get((newG.i + 1) % newG.N).create();
        newG.stars.get((newG.i + 2) % newG.N).create();
        if (!loaded || newG.stars.get((newG.i) % newG.N).getVisible()) {
            newG.stars.get((newG.i) % newG.N).appear(root);
        }
        if (!loaded || newG.stars.get((newG.i + 1) % newG.N).getVisible()) {
            newG.stars.get((newG.i + 1) % newG.N).appear(root);
        }
        if (!loaded || newG.stars.get((newG.i + 2) % newG.N).getVisible()) {
            newG.stars.get((newG.i + 2) % newG.N).appear(root);
        }
        if (!loaded) {
            newG.stars.get((newG.i) % newG.N).setLayoutY(0);
            newG.stars.get((newG.i + 2) % newG.N).setLayoutY(-800);
        }
    }

    private void addColorSwitchers(AnchorPane root, boolean loaded) {
        newG.colorSwitchers.get(newG.i % newG.N).create();
        newG.colorSwitchers.get((newG.i + 1) % newG.N).create();
        newG.colorSwitchers.get((newG.i + 2) % newG.N).create();

        if (!loaded || newG.colorSwitchers.get((newG.i) % newG.N).getVisible()) {
            newG.colorSwitchers.get((newG.i) % newG.N).appear(root);
        }
        if (!loaded || newG.colorSwitchers.get((newG.i) % newG.N).getVisible()) {
            newG.colorSwitchers.get((newG.i + 1) % newG.N).appear(root);
        }
        if (!loaded || newG.colorSwitchers.get((newG.i) % newG.N).getVisible()) {
            newG.colorSwitchers.get((newG.i + 2) % newG.N).appear(root);
        }
        if (!loaded) {
            newG.colorSwitchers.get((newG.i) % newG.N).setLayoutY(50);
            newG.colorSwitchers.get((newG.i + 2) % newG.N).setLayoutY(-700);
        }
    }


    public void startGame(Stage stage, boolean loaded) throws IOException {
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

        score = new Label("" + newG.currentScore);
        score.setFont(new Font("Comic Sans", 74));
        score.setTextFill(Color.WHITE);
        score.prefWidth(140);
        score.prefHeight(90);
        score.setLayoutX(37);
        score.setLayoutY(9);
        root.getChildren().addAll(pause, score);

        //changing objects in scene
        addObstacles(root, loaded);
        addBall(root);
        addStars(root, loaded);
        addColorSwitchers(root, loaded);

        playGame(stage, root);

        //set Scene and show stage
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
    }


    public void playGame(Stage stage, AnchorPane root) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                newG.ball.moveDown();
                Bounds bounds = root.getBoundsInLocal();

                if (newG.ball.getLayoutY() >= (bounds.getMaxY() - newG.ball.getRadius())||hitObstacle()==1) {
                    try {
                       animationTimer.stop();
                        gameOver(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                /*to not allow the ball to go above a certain height on screen.*/
                if (newG.ball.getLayoutY() <= 300) {
                    newG.ball.stay();
                    moveElementsDown(root);
                }

                /*check for collection*/
                collectColorSwitcher(root);
                collectStars(root, score);

                /*ensures obstacles are infinite and randomised*/
                if (newG.i % newG.N == 8) {
                    Collections.shuffle(newG.obstacles.subList(0, 8));
                }

                if(newG.currentScore%20==0 && newG.currentScore>0)
                {
                    increaseDifficulty();
                }


            }
        };

        pause.setOnAction(e -> {
            try {
                animationTimer.stop();
                int ans = pauseGame(stage);
                System.out.println("Received: " + ans);
                if (ans == 1) {
                    animationTimer.start();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        root.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {
            int flag = 0;

            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.UP) {
                    if (flag == 1) {
                        newG.ball.jump();
                    } else {
                        newG.ball.jump();
                        flag = 1;
                        animationTimer.start();
                    }
                }
            }
        });
    }

    private void increaseDifficulty() {
        for(int j=0;j<newG.N;j++) {
            newG.obstacles.get(j).increaseSpeed();
        }
    }

    private void gameOver(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/EndGame.fxml")); //TODO fix controller class
        AnchorPane root = loader.load();

        Button continueB = new Button("Continue");
        continueB.setMinWidth(400);
        continueB.setMinHeight(50);
        continueB.setLayoutX(100);
        continueB.setLayoutY(150);
        continueB.setFont(new Font("Courier New Bold", 48));
        continueB.setTextFill(Color.WHITE);
        continueB.setStyle("-fx-background-color: black");
        continueB.setOnAction(e -> {
            saveState();
            newG.resurrect(root);
        });

        Button quit = new Button("End Game");
        quit.setMinWidth(400);
        quit.setMinHeight(50);
        quit.setLayoutX(100);
        quit.setLayoutY(600);
        quit.setFont(new Font("Courier New Bold", 48));
        quit.setTextFill(Color.WHITE);
        quit.setStyle("-fx-background-color: black");
        quit.setOnAction(e -> {
            newG.Endgame(root);
        });

        Label totStars = new Label("Available: "+newG.Total());
        totStars.setFont(new Font("Courier New", 30));
        totStars.setTextFill(Color.WHITE);
        totStars.prefWidth(200);
        totStars.prefHeight(90);
        totStars.setLayoutX(350);
        totStars.setLayoutY(37);

        root.getChildren().addAll(continueB,score,quit,totStars);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveElementsDown(AnchorPane root) {
        //now move the obstacle down to give the illusion of screen moving down
        newG.colorSwitchers.get(newG.i % newG.N).moveDown();
        newG.colorSwitchers.get((newG.i + 1) % newG.N).moveDown();
        newG.colorSwitchers.get((newG.i + 2) % newG.N).moveDown();

        newG.stars.get(newG.i % newG.N).moveDown();
        newG.stars.get((newG.i + 1) % newG.N).moveDown();
        newG.stars.get((newG.i + 2) % newG.N).moveDown();

        newG.obstacles.get(newG.i % newG.N).moveDown();
        newG.obstacles.get((newG.i + 1) % newG.N).moveDown();
        newG.obstacles.get((newG.i + 2) % newG.N).moveDown();

        /* if obstacles are not on screen anymore then remove them from root and add the top screen element*/
        if (newG.obstacles.get(newG.i % newG.N).getLayoutY() >= 800) {
            newG.obstacles.get(newG.i % newG.N).disappear(root);
            System.out.println("Dis " + newG.i);
            newG.i++;
            newG.obstacles.get((newG.i + 2) % newG.N).create();
            newG.stars.get((newG.i + 2) % newG.N).create();
            newG.colorSwitchers.get((newG.i + 2) % newG.N).create();
            newG.obstacles.get((newG.i + 2) % newG.N).appear(root);
            newG.stars.get((newG.i + 2) % newG.N).appear(root);
            newG.colorSwitchers.get((newG.i + 2) % newG.N).appear(root);
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
        resume.setOnAction(e ->{
            saveState();
            try{
                startGame(stage,true);
            }catch(IOException exc){
                exc.printStackTrace();
            }
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
            newG.currentScore=0;
            newG.i=0;
            Collections.shuffle(newG.obstacles);
            newG.ball.setLayoutY(600);
            try{
                startGame(stage,false);
            }catch(IOException exc){
                exc.printStackTrace();
            }

        });

        //save game
        Button save = new Button("Save & Exit Game");
        save.setMinWidth(400);
        save.setMinHeight(50);
        save.setLayoutX(100);
        save.setLayoutY(500);
        save.setFont(new Font("Courier New Bold", 40));
        save.setTextFill(Color.WHITE);
        save.setStyle("-fx-background-color: black");
        save.setOnAction(e -> {
            saveState();
            stage.close();
            newG.save();
        });

        root.getChildren().addAll(resume, restart, save);
        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
        return flag.intValue();
    }

    private int hitObstacle() {
        /*check if collision*/

        int collisionDetected1 = newG.obstacles.get(newG.i % newG.N).checkCollision(newG.ball.getShape());
        int collisionDetected2 = newG.obstacles.get((newG.i + 1) % newG.N).checkCollision(newG.ball.getShape());
        int collisionDetected3 = newG.obstacles.get((newG.i + 2) % newG.N).checkCollision(newG.ball.getShape());
        if (collisionDetected1 == 1 || collisionDetected2 == 1 || collisionDetected3 == 1) {
            return 1;
        }
        return 0;
    }

    private int collectStars(AnchorPane root, Label score) {
        /* check if star collected*/
        int starCollected1 = newG.stars.get(newG.i % newG.N).checkCollision(newG.ball.getShape());
        int starCollected2 = newG.stars.get((newG.i + 1) % newG.N).checkCollision(newG.ball.getShape());
        int starCollected3 = newG.stars.get((newG.i + 2) % newG.N).checkCollision(newG.ball.getShape());
        if (starCollected1 == 1)
            newG.stars.get(newG.i % newG.N).disappear(root);
        else if (starCollected2 == 1)
            newG.stars.get((newG.i + 1) % newG.N).disappear(root);
        else if (starCollected3 == 1)
            newG.stars.get((newG.i + 2) % newG.N).disappear(root);

        /*if star was hit then update score*/
        if (starCollected1 == 1 || starCollected2 == 1 || starCollected3 == 1) {
            root.getChildren().remove(score);
            newG.currentScore += 1;
            score.setText("" + newG.currentScore);
            root.getChildren().add(score);
        }
        return 0;
    }

    private String collectColorSwitcher(AnchorPane root) {

        /*check if color switcher collected*/
        int csCollected1 = newG.colorSwitchers.get(newG.i % newG.N).checkCollision(newG.ball.getShape());
        int csCollected2 = newG.colorSwitchers.get((newG.i + 1) % newG.N).checkCollision(newG.ball.getShape());
        int csCollected3 = newG.colorSwitchers.get((newG.i + 2) % newG.N).checkCollision(newG.ball.getShape());

        //if color switcher collected
        if (csCollected1 == 1 || csCollected2 == 1 || csCollected3 == 1) {
            System.out.println("Color Switcher Collected");
            if (csCollected1 == 1) {
                newG.ball.setColor(newG.colorSwitchers.get(newG.i % newG.N).generateColor(newG.ball.getColor()));
                newG.colorSwitchers.get(newG.i % newG.N).disappear(root);
            } else if (csCollected2 == 1) {
                newG.ball.setColor(newG.colorSwitchers.get(newG.i % newG.N).generateColor(newG.ball.getColor()));
                newG.colorSwitchers.get((newG.i + 1) % newG.N).disappear(root);
            } else if (csCollected3 == 1) {
                newG.ball.setColor(newG.colorSwitchers.get(newG.i % newG.N).generateColor(newG.ball.getColor()));
                newG.colorSwitchers.get((newG.i + 2) % newG.N).disappear(root);
            }
        }
        return " ";
    }

    public void saveState() {
        System.out.println(newG.i);
        for (int j = 0; j < newG.N; j++) {
            newG.obstacles.get(j).save();
            newG.stars.get(j).save();
            newG.colorSwitchers.get(j).save();
            //System.out.println(newG.colorSwitchers.get(j).positionY + " " + newG.stars.get(j).positionY);
            //   System.out.println(newG.obstacles.get(j).positionY + " " + j);
        }
        newG.ball.save();
    }

}
