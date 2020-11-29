package colorswitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class Triangle extends Obstacle{
    double side;
    Triangle(String ty, int x, int y, double s){
        super(ty,x,y);
        side=s;
    }

    @Override
    public void disappear() {

    }

    @Override
    public void appear(AnchorPane root) {

    }

    @Override
    public int checkCollision(Shape ball) {
        return 0;
    }
}
