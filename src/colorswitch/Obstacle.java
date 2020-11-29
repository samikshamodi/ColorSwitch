package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public abstract class Obstacle extends GameElements{
    double speed;
    int direction;
    String type;
    Obstacle(String ty,int x, int y){
        super(x,y);
        speed=0;
        direction=1;
        type=ty;
    }

    public String getType() {
        return type;
    }

    public int getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }
    int rotate(){
        return 0;
    }

}
