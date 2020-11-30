package colorswitch;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.Random;

public class ColorSwitcher extends GameElements {
    Random rand;
    ImageView img;

    ColorSwitcher(int x, int y) {
        super(x, y);
        rand = new Random();
    }

    String generateColor() {
        return " ";
    }

    @Override
    public void appear(AnchorPane root) {
        img = new ImageView("/assets/colorSwitcher.png");
        img.setFitHeight(30);
        img.setFitWidth(30);
        img.setX(290);
        img.setY(50);
        img.setPreserveRatio(true);
        root.getChildren().add(img);
    }

    @Override
    public int checkCollision(Shape ball) {
        //if ball collides with color switcher return 1 else return 0;
        if ((img.getBoundsInParent().intersects(ball.getBoundsInParent()))) {
            return 1;
        }
        return 0;
    }

    @Override
    public void moveDown() {
        img.setLayoutY(img.getLayoutY() + 55); //3 is step or velocity
    }

    @Override
    public double getLayoutY() {
        return 0;
    }

    @Override
    public void setLayoutY(double dy) {

    }

    @Override
    public void disappear(AnchorPane root) {
        root.getChildren().remove(img);
    }
}
