package colorswitch;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Scene sceneN,sceneL,sceneE,scene1;
    public static Canvas canvas;
    public static Player pl;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainPage.fxml"));
        AnchorPane root = loader.load();
        scene1 = new Scene(root);
        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    public static Scene newPlayerscene(Stage window){
        TextField b = new TextField();

        Text lN=new Text();
        lN.setText("Choose a UserName");
        lN.setFont(Font.font("Denver",20));
        lN.setFill(Color.WHITESMOKE);

        Button newPl = new Button("Confirm");
        newPl.setMinSize(300, 50);
        newPl.setOnAction(e->pl.main(null));
        newPl.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
        newPl.setFont(Font.font("Denver", 20));

        Button quitGameButton = new Button("Main Page");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setOnAction(e->window.setScene(scene1));
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        quitGameButton.setFont(Font.font("Denver", 20));

        VBox rootN = new VBox(20);
        rootN.getChildren().setAll(lN,b,newPl,quitGameButton);
        rootN.setAlignment(Pos.BASELINE_CENTER);
        rootN.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return new Scene(rootN,600,800);
    }

    public static Scene exPlayerScene(Stage window){
        TextField b = new TextField();

        Text lN=new Text();
        lN.setText("Select User\nDisplay list of available options");
        lN.setFont(Font.font("Denver",20));
        lN.setFill(Color.WHITESMOKE);

        Button newPl = new Button("Confirm");
        newPl.setMinSize(300, 50);
        newPl.setOnAction(e -> pl.launch(Player.class));
        newPl.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
        newPl.setFont(Font.font("Denver", 20));

        Button quitGameButton = new Button("Main Page");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setOnAction(e->window.setScene(scene1));
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        quitGameButton.setFont(Font.font("Denver", 20));

        VBox rootN = new VBox(20);
        rootN.getChildren().setAll(lN,b,newPl,quitGameButton);
        rootN.setAlignment(Pos.BASELINE_CENTER);
        rootN.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return new Scene(rootN,600,800);
    }

    public static Scene leader(Stage window){

        Text lN=new Text();
        lN.setText("Display Leaderboard");
        lN.setFont(Font.font("Denver",20));
        lN.setFill(Color.WHITESMOKE);

        Button quitGameButton = new Button("Main Page");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setOnAction(e->window.setScene(scene1));
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        quitGameButton.setFont(Font.font("Denver", 20));

        VBox rootN = new VBox(20);
        rootN.getChildren().setAll(lN,quitGameButton);
        rootN.setAlignment(Pos.BASELINE_CENTER);
        rootN.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return new Scene(rootN,600,800);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
