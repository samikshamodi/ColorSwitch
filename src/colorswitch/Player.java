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
public class Player extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Switch");

        Button newGameButton = new Button("NEW GAME");
        newGameButton.setMinSize(300, 50);
        newGameButton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
        newGameButton.setFont(Font.font("Denver", 20));

        Button resumeGameButton = new Button("RESUME GAME");
        resumeGameButton.setMinSize(300, 50);
        resumeGameButton.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
        resumeGameButton.setFont(Font.font("Denver", 20));

        Button quitGameButton = new Button("Exit to Main Page");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
        quitGameButton.setFont(Font.font("Denver", 20));

        Canvas canvas = new Canvas(300, 315);
        VBox root = new VBox(20, canvas, newGameButton, resumeGameButton, quitGameButton);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        //GraphicsContext gc = canvas.getGraphicsContext2D();

        /*Image logo = new Image("assets/logo.png");
        Image logo2 = new Image("assets/logo2.png");
        gc.drawImage(logo, 10, 10);
        gc.drawImage(logo2, 75, 160);*/

        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
