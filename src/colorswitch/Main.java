package colorswitch;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Switch");

        Button newPlayerButton = new Button("NEW PLAYER");
        newPlayerButton.setMinSize(300, 50);
        newPlayerButton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
        newPlayerButton.setFont(Font.font("Denver", 20));

        Button existingPlayerButton = new Button("EXISTING PLAYER");
        existingPlayerButton.setMinSize(300, 50);
        existingPlayerButton.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
        existingPlayerButton.setFont(Font.font("Denver", 20));

        Button viewLeaderboardButton = new Button("VIEW LEADERBOARD");
        viewLeaderboardButton.setMinSize(300, 50);
        viewLeaderboardButton.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        viewLeaderboardButton.setFont(Font.font("Denver", 20));

        Button quitGameButton = new Button("QUIT GAME");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        quitGameButton.setFont(Font.font("Denver", 20));

        Canvas canvas = new Canvas(300, 315);
        VBox root = new VBox(20, canvas, newPlayerButton, existingPlayerButton, viewLeaderboardButton, quitGameButton);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image logo = new Image("assets/logo.png");
        Image logo2 = new Image("assets/logo2.png");
        gc.drawImage(logo, 10, 10);
        gc.drawImage(logo2, 75, 160);

        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
