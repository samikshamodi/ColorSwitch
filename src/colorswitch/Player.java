package colorswitch;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Player extends Application {
    public static Scene scene1,sceneG,sceneS;
    public static Canvas canvas;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Switch");

        Button newGameButton = new Button("NEW GAME");
        newGameButton.setMinSize(300, 50);
        newGameButton.setOnAction(e->primaryStage.setScene(sceneS));
        newGameButton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
        newGameButton.setFont(Font.font("Denver", 20));

        Button resumeGameButton = new Button("RESUME GAME");
        resumeGameButton.setMinSize(300, 50);
        resumeGameButton.setOnAction(e->primaryStage.setScene(sceneG));
        resumeGameButton.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
        resumeGameButton.setFont(Font.font("Denver", 20));

        Button quitGameButton = new Button("EXIT TO MAIN PAGE");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        quitGameButton.setFont(Font.font("Denver", 20));

        Canvas canvas = new Canvas(300, 315);
        VBox root = new VBox(20, canvas, newGameButton, resumeGameButton, quitGameButton);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        scene1=new Scene(root,600,800);

        /*Image logo = new Image("assets/logo.png");
        Image logo2 = new Image("assets/logo2.png");
        gc.drawImage(logo, 10, 10);
        gc.drawImage(logo2, 75, 160);*/

        //sceneG select Game scene
        sceneG = selectGameScene(primaryStage);

        //SceneS Start Game scene
        sceneS= startGameScene(primaryStage);


        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static Scene selectGameScene(Stage window) {
        TextField b = new TextField();

        Text lN = new Text();
        lN.setText("Choose a Game\nDisplay list of available options");
        lN.setFont(Font.font("Denver", 20));
        lN.setFill(Color.WHITESMOKE);

        Button confirmButton = new Button("CONFIRM");
        confirmButton.setMinSize(300, 50);
        confirmButton.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
        confirmButton.setFont(Font.font("Denver", 20));

        Button goBackButton = new Button("Go Back");
        goBackButton.setMinSize(300, 50);
        goBackButton.setOnAction(e -> window.setScene(scene1));
        goBackButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        goBackButton.setFont(Font.font("Denver", 20));

        VBox rootN = new VBox(20);
        rootN.getChildren().setAll(lN, b, confirmButton, goBackButton);
        rootN.setAlignment(Pos.BASELINE_CENTER);
        rootN.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return new Scene(rootN, 600, 800);
    }

    public static Scene startGameScene(Stage window) {
        Text lN = new Text();
        lN.setText("START");
        lN.setFont(Font.font("Denver", 20));
        lN.setFill(Color.WHITESMOKE);

        canvas = new Canvas(300, 315);
        VBox rootN = new VBox(50);
        rootN.getChildren().setAll(lN,canvas);
        rootN.setAlignment(Pos.BASELINE_CENTER);
        rootN.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //TODO change play button
        Image logo = new Image("assets/playButton.jpg");
        gc.drawImage(logo, 10, 10);
        return new Scene(rootN, 600, 800);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
