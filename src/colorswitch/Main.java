package colorswitch;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;

public class Main extends Application {
    ObjectInputStream in;
    GameModel n;
    ArrayList<Integer> leaderboard;

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
        addResources(primaryStage,root);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void addResources(Stage primaryStage,AnchorPane root) {
        BackgroundImage myBI1 = new BackgroundImage(new Image("/assets/newGameButton.png", 120, 123, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button play = new Button("");
        play.setBackground(new Background(myBI1));
        play.setPrefHeight(120);
        play.setPrefWidth(123);
        play.setTranslateX(240);
        play.setTranslateY(253);
        play.setOnAction(e -> {
            //  System.out.println("new game");
            n=new GameModel();
            try {
                //loadGame();
                //n.loadGame(primaryStage,this);
                  n.setUp(primaryStage,this);
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }/*catch(ClassNotFoundException en){
                System.out.println("H");
                en.printStackTrace();
            }*/
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
            AnchorPane r=null;
            try {
                viewLeaderBoard(primaryStage,r,-1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        root.getChildren().addAll(img1, img2, img3, leaderboard, play);
    }

    public void viewLeaderBoard(Stage stage,AnchorPane root,int score) throws IOException{

        root = FXMLLoader.load(Main.class.getResource("/gui/ViewLeaderboardScene.fxml"));
        try{
            in = new ObjectInputStream(new FileInputStream("leaderBoard.txt"));
            leaderboard=(ArrayList<Integer>) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(in!=null)
                in.close();
        }
        if(leaderboard == null){
            System.out.println("Hello");
            leaderboard=new ArrayList<>();
        }
        leaderboard.add(score);
        leaderboard.sort(Collections.reverseOrder());
        for(int i =0 ; i<leaderboard.size();i++){
            if(i<5){
                Label sc = new Label(i+1+") "+leaderboard.get(i));
                sc.setFont(new Font("Courier New Italic", 30));
                sc.setTextFill(Color.WHITE);
                sc.prefWidth(400);
                sc.prefHeight(50);
                sc.setLayoutX(80);
                sc.setLayoutY(250+i*65);
                root.getChildren().add(sc);
            }
            else{
                break;
            }
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("leaderBoard.txt"));
            out.writeObject(leaderboard);
        }
        finally{
            if(out!=null)
                out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        launch(args);
//        Main m=new Main();
//        //GameModel newk = new GameModel();
//        try{
//            //m.saveGame(newk);
//            m.loadGame();
//            m.n.loadGame();
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