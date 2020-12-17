package colorswitch;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class Star extends GameElements {
    private String color;
    private int value;
    private boolean visible;
    private transient ImageView img;

    Star(int v, int y) {
        super(y);
        value = v;
        if (value == 1)
            color = "White";
        else
            color = "Green";
        create();
    }

    public void create() {

    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void appear(AnchorPane root) {
        img = new ImageView("/assets/star.png");
        visible = true;
        img.setFitHeight(50);
        img.setFitWidth(50);
        img.setX(275);
        img.setY(275);
        img.setPreserveRatio(true);
        img.setLayoutY(positionY);
        root.getChildren().add(img);
    }

    @Override
    public int checkCollision(Shape ball) {
        //if ball collides with star return 1 else return 0;
        if (img==null)
            return 0;
        if ((img.getBoundsInParent().intersects(ball.getBoundsInParent()))) {
            return 1;
        }
        return 0;
    }

    @Override
    public void moveDown() {
        if(img!=null)
            img.setLayoutY(img.getLayoutY() + 55); //3 is step or velocity
    }

    @Override
    public double getLayoutY() {
        if(img==null)
            return -400;
        return img.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        img.setLayoutY(dy);
    }

    @Override
    public void disappear(AnchorPane root) {
        visible = false;
        img.setLayoutY(1000);
        positionX = 275;
        positionY = -400;
        root.getChildren().remove(img);
    }

    public void save() {
        if (visible) {
            positionX = img.getLayoutX();
            positionY = img.getLayoutY();
        }
    }

    public boolean getVisible() {
        return visible;
    }
}

