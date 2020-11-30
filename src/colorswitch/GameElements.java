package colorswitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public abstract class GameElements {
    int position[];
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
}
