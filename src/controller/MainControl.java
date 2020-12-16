package controller;

import colorswitch.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainControl {
    Main m = new Main();
    private void show(ActionEvent actionEvent, String s) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(s));
        Scene scene = new Scene(root);

        //This line gets stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    /*  public void viewLeaderboardS(ActionEvent actionEvent) throws IOException {
          String s = "/gui/viewLeaderboardScene.fxml";
          show(actionEvent, s);
      }
  */
    public void quitS(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.close();
    }

    public void mainMenuS(ActionEvent actionEvent) throws IOException {
        //This line gets stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        m.start(window);
    }

    public void selectGameS(ActionEvent actionEvent) throws IOException {
        //String s = "/gui/SelectGameScene.fxml";
        //show(actionEvent, s);
    }

    public void instructionS(ActionEvent actionEvent) throws IOException {
        String s = "/gui/InstructionScene.fxml";
        show(actionEvent, s);
    }
}
