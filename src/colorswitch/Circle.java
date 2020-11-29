package colorswitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class Circle extends Obstacle{
    double radius;
    public Circle(String ty,int x,int y,double r){
        super(ty,x,y);
        radius=r;
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
