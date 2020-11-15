package colorswitch;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Switch");

        Button newPlayerButton =new Button("NEW PLAYER");
        newPlayerButton.setMinSize(300, 50);
        newPlayerButton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,null,null)));
        newPlayerButton.setFont(Font.font("Denver",20));

        Button existingPlayerButton =new Button("EXISTING PLAYER");
        existingPlayerButton.setMinSize(300, 50);
        existingPlayerButton.setBackground(new Background(new BackgroundFill(Color.CADETBLUE,null,null)));
        existingPlayerButton.setFont(Font.font("Denver",20));

        Button viewLeaderboardButton =new Button("VIEW LEADERBOARD");
        viewLeaderboardButton.setMinSize(300, 50);
        viewLeaderboardButton.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE,null,null)));
        viewLeaderboardButton.setFont(Font.font("Denver",20));

        Button quitGameButton =new Button("QUIT GAME");
        quitGameButton.setMinSize(300, 50);
        quitGameButton.setBackground(new Background(new BackgroundFill(Color.CORAL,null,null)));
        quitGameButton.setFont(Font.font("Denver",20));


        VBox vbox=new VBox(20,newPlayerButton,existingPlayerButton,viewLeaderboardButton,quitGameButton);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        //vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        primaryStage.setScene(new Scene(vbox, 600,600 ));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
