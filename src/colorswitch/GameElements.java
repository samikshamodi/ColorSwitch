package colorswitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.io.Serializable;

public abstract class GameElements implements Serializable {
    protected double positionY;
    protected double positionX;
    GameElements(double y){
        positionY=y;
    }


    public double getPosition() {
        return positionY;
    }
    public abstract void disappear(AnchorPane root);
    public abstract void appear(AnchorPane root);

    public abstract int checkCollision(Shape ball);

    public abstract void moveDown();

    public abstract double getLayoutY();

    public abstract void setLayoutY(double dy);
}
