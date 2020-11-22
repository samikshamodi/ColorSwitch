package controller;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainControl {
    private void show(ActionEvent actionEvent, String s) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(s));
        Scene scene = new Scene(root);

        //This line gets stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void viewLeaderboardS(ActionEvent actionEvent) throws IOException {
        String s = "/gui/viewLeaderboardScene.fxml";
        show(actionEvent, s);
    }

    public void quitS(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.close();
    }

    public void mainMenuS(ActionEvent actionEvent) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/MainMenu.fxml"));
        ImageView img1 = new ImageView("/assets/c1.png");
        img1.setFitHeight(150);
        img1.setFitWidth(150);
        img1.setX(225.5);
        img1.setY(240);
        img1.setPreserveRatio(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4),img1);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1500);
        rotateTransition.play();

        ImageView img2 = new ImageView("/assets/c1.png");
        img2.setFitHeight(190);
        img2.setFitWidth(190);
        img2.setX(205);
        img2.setY(220);
        img2.setPreserveRatio(true);
        RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(4),img2);
        rotateTransition2.setByAngle(-360);
        rotateTransition2.setCycleCount(1500);
        rotateTransition2.play();

        ImageView img3 = new ImageView("/assets/c1.png");
        img3.setFitHeight(240);
        img3.setFitWidth(240);
        img3.setX(180);
        img3.setY(195);
        img3.setPreserveRatio(true);
        RotateTransition rotateTransition3 = new RotateTransition(Duration.seconds(4),img3);
        rotateTransition3.setByAngle(360);
        rotateTransition3.setCycleCount(1500);
        rotateTransition3.play();

        root.getChildren().addAll(img1,img2,img3);
        Scene scene = new Scene(root);

        //This line gets stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void startGameS(ActionEvent actionEvent) throws IOException {
        String s = "/gui/StartGameScene.fxml";
        show(actionEvent, s);
    }

    public void selectGameS(ActionEvent actionEvent) throws IOException {
        String s = "/gui/SelectGameScene.fxml";
        show(actionEvent, s);
    }
}
