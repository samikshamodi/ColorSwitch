package colorswitch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataBase implements Serializable {
    private int Stars,Count;
    private static DataBase db = null;
    transient Stage stage;
    transient Main m;
    private HashMap<String,GameModel> savedGames;

    public DataBase() {
        Stars=0;
        Count=0;
        savedGames=new HashMap<>();
    }
    public static DataBase getInstance(){
        if(db == null){
            db = new DataBase();
        }
        return db;
    }
    public void printSavedGames(Stage stage,Main m) throws IOException {
        this.stage=stage;
        this.m=m;
        AnchorPane root = FXMLLoader.load(Main.class.getResource("/gui/SelectGameScene.fxml"));
        Label sc;
        if(savedGames.isEmpty()){
            sc = new Label("No saved games");
            sc.setFont(new Font("Courier New Italic", 36));
            sc.setTextFill(Color.WHITE);
            sc.prefWidth(400);
            sc.prefHeight(50);
            sc.setLayoutX(80);
            sc.setLayoutY(175);
            root.getChildren().add(sc);
        }
        else{
            int i=0;
            for (Map.Entry<String,GameModel> entry : savedGames.entrySet()){
                if(i<5){
                    sc = new Label(entry.getKey()+") "+entry.getValue());
                    sc.setFont(new Font("Courier New Italic", 25));
                    sc.setTextFill(Color.WHITE);
                    sc.prefWidth(400);
                    sc.prefHeight(30);
                    sc.setLayoutX(80);
                    sc.setLayoutY(175+i*40);
                    root.getChildren().add(sc);
                    i++;
                }
                else
                    break;
            }
        }
        Button submit = new Button("Submit");
        TextField tx = new TextField();
        tx.setMinHeight(50);
        tx.setMinWidth(400);
        tx.setLayoutX(100);
        tx.setLayoutY(530);
        submit.setMinWidth(400);
        submit.setMinHeight(50);
        submit.setLayoutX(100);
        submit.setLayoutY(590);
        submit.setFont(new Font("Courier New Bold", 40));
        submit.setTextFill(Color.WHITE);
        submit.setStyle("-fx-background-color: black");
        submit.setOnAction(e -> {
            String opt = tx.getText();
            loadGameModel(opt,root);
        });
        root.getChildren().addAll(submit,tx);
        Scene scene = new Scene(root);

        stage.setTitle("Color Switch");
        stage.setScene(scene);
        stage.show();
    }
    void loadGameModel(String op,AnchorPane root){
        try{
            GameModel n = savedGames.get(op);
            n.loadGame(stage,this);
        }catch(NullPointerException e){
            Label errMessage = new Label("Invalid input! Try again");
            errMessage.setFont(new Font("Courier New",30));
            errMessage.setMinWidth(400);
            errMessage.setMinHeight(50);
            errMessage.setLayoutX(100);
            errMessage.setLayoutY(650);
            errMessage.setTextFill(Color.WHITE);
            errMessage.setStyle("-fx-background-color: black");
            root.getChildren().add(errMessage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    void saveGameModel(GameModel n){
        Count++;
        if(Count == 5){
            savedGames.put(""+5,n);
        }else{
            savedGames.put(""+Count,n);
        }
        try {
            m.saveGame(this);
            m.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void newGame(Stage stage, Main m){
        this.stage=stage;
        this.m=m;
        GameModel n = new GameModel();
        try{
            n.setUp(stage,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void end(AnchorPane root,GameModel n){
        Stars+=n.currentScore;
        try {
            m.viewLeaderBoard(stage,root, n.currentScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getStars() {
        return Stars;
    }

    public void setStars() {
        Stars -= 100;
    }
}
