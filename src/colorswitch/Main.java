package colorswitch;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class Main extends Application {
    ObjectInputStream in;
    Game g;
    GameModel n;

    public void saveGame(GameModel s) throws IOException{
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("saves.txt"));
            out.writeObject(s);
        }
        finally{
            if(out!=null)
                out.close();
        }
        System.out.println("Game saved");
    }

    public void loadGame() throws IOException,ClassNotFoundException{
        try{
            in = new ObjectInputStream(new FileInputStream("saves.txt"));
            n=(GameModel) in.readObject();
        }
        finally{
            if(in!=null)
                in.close();
        }
        System.out.println("Game loaded");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/MainMenu.fxml"));
        BackgroundImage myBI1 = new BackgroundImage(new Image("/assets/newGameButton.png", 120, 123, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button play = new Button("");
        play.setBackground(new Background(myBI1));
        play.setPrefHeight(120);
        play.setPrefWidth(123);
        play.setTranslateX(240);
        play.setTranslateY(253);
        play.setOnAction(e -> {
            //  System.out.println("new game");
            g = new Game();
            try {
                g.startGame(primaryStage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        ImageView img1 = new ImageView("/assets/c1.png");
        img1.setFitHeight(150);
        img1.setFitWidth(150);
        img1.setX(225.5);
        img1.setY(240);
        img1.setPreserveRatio(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4), img1);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1500);
        rotateTransition.play();

        ImageView img2 = new ImageView("/assets/c1.png");
        img2.setFitHeight(190);
        img2.setFitWidth(190);
        img2.setX(205);
        img2.setY(220);
        img2.setPreserveRatio(true);
        RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(4), img2);
        rotateTransition2.setByAngle(-360);
        rotateTransition2.setCycleCount(1500);
        rotateTransition2.play();

        ImageView img3 = new ImageView("/assets/c1.png");
        img3.setFitHeight(240);
        img3.setFitWidth(240);
        img3.setX(180);
        img3.setY(195);
        img3.setPreserveRatio(true);
        RotateTransition rotateTransition3 = new RotateTransition(Duration.seconds(4), img3);
        rotateTransition3.setByAngle(360);
        rotateTransition3.setCycleCount(1500);
        rotateTransition3.play();

        BackgroundImage myBI = new BackgroundImage(new Image("/assets/leaderboard.png", 108, 111, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button leaderboard = new Button("");
        leaderboard.setBackground(new Background(myBI));
        leaderboard.setPrefHeight(108);
        leaderboard.setPrefWidth(111);
        leaderboard.setTranslateX(33);
        leaderboard.setTranslateY(667);
        leaderboard.setTranslateX(250);
        leaderboard.setTranslateY(590);
        RotateTransition rotateTransition4 = new RotateTransition(Duration.seconds(4), leaderboard);
        rotateTransition4.setByAngle(360);
        rotateTransition4.setCycleCount(1500);
        rotateTransition4.play();

        leaderboard.setOnAction(e -> {
            AnchorPane r = null;
            try {
                r = FXMLLoader.load(getClass().getResource("/gui/ViewLeaderboardScene.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Scene scene = new Scene(r);
            primaryStage.setScene(scene);
        });

        root.getChildren().addAll(img1, img2, img3, leaderboard, play);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
//        Main m=new Main();
//        GameModel newk = new GameModel();
//        try{
//            m.saveGame(newk);
//            m.loadGame();
//        }
//        catch (IOException e){
//            System.out.println("IO Error");
//            e.printStackTrace();
//        }
//        catch (ClassNotFoundException e){
//            System.out.println("CNF Error");
//            e.printStackTrace();
//        }
    }
}