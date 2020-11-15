package colorswitch;


import javafx.application.Application;
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

public class Main extends Application {

    public static Scene sceneN,sceneL,sceneE,scene1;
    public static Canvas canvas;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Switch");

        //Main Page
        Button newPlayerButton = new Button("NEW PLAYER");
        newPlayerButton.setMinSize(300, 50);
        newPlayerButton.setOnAction(e->primaryStage.setScene(sceneN));
        newPlayerButton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
        newPlayerButton.setFont(Font.font("Denver", 20));

        Button existingPlayerButton = new Button("EXISTING PLAYER");
        existingPlayerButton.setMinSize(300, 50);
        existingPlayerButton.setOnAction(e->primaryStage.setScene(sceneE));
        existingPlayerButton.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
        existingPlayerButton.setFont(Font.font("Denver", 20));

        Button viewLeaderboardButton = new Button("VIEW LEADERBOARD");
        viewLeaderboardButton.setMinSize(300, 50);
        viewLeaderboardButton.setOnAction(e->primaryStage.setScene(sceneL));
        viewLeaderboardButton.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        viewLeaderboardButton.setFont(Font.font("Denver", 20));

        Button quitGameButton = new Button("QUIT GAME");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setOnAction(e->primaryStage.close());
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        quitGameButton.setFont(Font.font("Denver", 20));

        canvas = new Canvas(300, 315);
        VBox root = new VBox(20, canvas, newPlayerButton, existingPlayerButton, viewLeaderboardButton, quitGameButton);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image logo = new Image("assets/logo.png");
        Image logo2 = new Image("assets/logo2.png");
        gc.drawImage(logo, 10, 10);
        gc.drawImage(logo2, 75, 160);
        scene1= new Scene(root, 600, 800);

        //sceneN New player scene
        sceneN = newPlayerscene(primaryStage);

        //sceneE Existing Player scene
        sceneE = exPlayerScene(primaryStage);

        //sceneL Leaderboard scene
        sceneL = leader(primaryStage);
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
