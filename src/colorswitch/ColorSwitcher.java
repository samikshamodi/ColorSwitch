package colorswitch;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ColorSwitcher extends GameElements {
    private Random rand;
    private transient ImageView img;
    private transient ArrayList<Color> list;


    ColorSwitcher(int y) {
        super(y);
        rand = new Random();

        list=new ArrayList<>();
        list.add(Color.YELLOW);
        list.add(Color.DEEPPINK);
        list.add(Color.CYAN);
        list.add(Color.PURPLE);
    }

    public Color generateColor(Color ballColor)
    {
        list.remove(ballColor);
        Collections.shuffle(list);
        Color newColor=list.get(0);
        list.add(ballColor);
        return newColor;
    }

    @Override
    public void appear(AnchorPane root) {
        img = new ImageView("/assets/colorSwitcher.png");
        img.setFitHeight(30);
        img.setFitWidth(30);
        img.setX(290);
        img.setY(50);
        img.setPreserveRatio(true);
        img.setLayoutY(-350);
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
        return img.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        img.setLayoutY(dy);
    }

    @Override
    public void disappear(AnchorPane root) {
        //root.getChildren().remove(img);
        //img.setImage(null);
        img.setLayoutY(1000);
        root.getChildren().remove(img);
    }
}
