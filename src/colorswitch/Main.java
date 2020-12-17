package colorswitch;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {
    ObjectInputStream in;
    DataBase db;
    ArrayList<Integer> leader;
    static MediaPlayer mediaGameTrack;

    public void saveGame(DataBase db) throws IOException{
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("saves.txt"));
            out.writeObject(db);
        }
        finally{
            if(out!=null)
                out.close();
        }
    }

    public void loadGame() throws IOException,ClassNotFoundException{
        try{
            in = new ObjectInputStream(new FileInputStream("saves.txt"));
            db=(DataBase) in.readObject();
        }
        finally{
            if(in!=null)
                in.close();
        }
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
        //addMusic();
        try {
            loadGame();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //db = new DataBase();
        BackgroundImage myBI1 = new BackgroundImage(new Image("/assets/newGameButton.png", 120, 123, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Button play = new Button("");
        play.setBackground(new Background(myBI1));
        play.setPrefHeight(120);
        play.setPrefWidth(123);
        play.setTranslateX(240);
        play.setTranslateY(253);
        play.setOnAction(e -> {
            db.newGame(primaryStage,this);
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

        Button resume = new Button("Resume Game");
        resume.setMinWidth(400);
        resume.setMinHeight(50);
        resume.setLayoutX(100);
        resume.setLayoutY(472);
        resume.setFont(new Font("Courier New Bold", 40));
        resume.setTextFill(Color.WHITE);
        resume.setStyle("-fx-background-color: black");
        resume.setOnAction(e -> {
            try {
                db.printSavedGames(primaryStage,this);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        root.getChildren().addAll(img1, img2, img3, leaderboard, play,resume);
    }

    public void viewLeaderBoard(Stage stage,AnchorPane root,int score) throws IOException{

        root = FXMLLoader.load(Main.class.getResource("/gui/ViewLeaderboardScene.fxml"));
        try{
            in = new ObjectInputStream(new FileInputStream("leaderBoard.txt"));
            leader =(ArrayList<Integer>) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(in!=null)
                in.close();
        }
        if(leader == null){
            System.out.println("Hello");
            leader =new ArrayList<>();
        }
        leader.add(score);
        leader.sort(Collections.reverseOrder());
        for(int i = 0; i< leader.size(); i++){
            if(i<5){
                Label sc = new Label(i+1+") "+ leader.get(i));
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
            out.writeObject(leader);
        }
        finally{
            if(out!=null)
                out.close();
        }
    }
    private void addMusic() {
        mediaGameTrack = new MediaPlayer(new Media(getClass().getResource("/assets/gameTrack.mp3").toString()));
        mediaGameTrack.setAutoPlay(true);
        mediaGameTrack.setCycleCount(MediaPlayer.INDEFINITE);
        mediaGameTrack.play();
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