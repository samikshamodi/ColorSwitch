package colorswitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class Special extends Obstacle{
    Special(String ty,int x,int y){
        super(ty,x,y);
    }

    @Override
    public void disappear(AnchorPane root) {

    }

    @Override
    public void appear(AnchorPane root) {

    }

    @Override
    public int checkCollision(Shape ball) {
        return 0;
    }

    @Override
    public void moveDown() {

    }

    @Override
    public double getLayoutY() {
        return 0;
    }

    @Override
    public void setLayoutY(double dy) {

    }
}
