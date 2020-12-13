package colorswitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.io.Serializable;

public abstract class GameElements implements Serializable {
    private int[] position;
    GameElements(int x, int y){
        position=new int[2];
        position[0]=x;
        position[1]=y;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
    public abstract void disappear(AnchorPane root);
    public abstract void appear(AnchorPane root);

    public abstract int checkCollision(Shape ball);

    public abstract void moveDown();

    public abstract double getLayoutY();

    public abstract void setLayoutY(double dy);
}
