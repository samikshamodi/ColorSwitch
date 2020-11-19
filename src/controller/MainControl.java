package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainControl {
    public void newPlayerS(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/gui/NewPlayerScene.fxml"));
        Scene scene=new Scene(root);

        //This line gets stage information
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void exPlayerS(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/gui/ExistingPlayerScene.fxml"));
        Scene scene=new Scene(root);

        //This line gets stage information
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void viewLeaderboardS(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/gui/viewLeaderboardScene.fxml"));
        Scene scene=new Scene(root);

        //This line gets stage information
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void quitS(ActionEvent actionEvent) {
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.close();
    }

    public void gameMenuS(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/gui/GameMenu.fxml"));
        Scene scene=new Scene(root);

        //This line gets stage information
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void mainMenuS(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/gui/MainMenu.fxml"));
        Scene scene=new Scene(root);

        //This line gets stage information
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void startGameS(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/gui/StartGameScene.fxml"));
        Scene scene=new Scene(root);

        //This line gets stage information
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void selectGameS(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/gui/SelectGameScene.fxml"));
        Scene scene=new Scene(root);

        //This line gets stage information
        Stage window=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
