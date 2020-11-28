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

    @Override
    public void appear(AnchorPane root) {
        Rectangle r1 = new Rectangle(200, 15, Color.YELLOW);
        Rectangle r2 = new Rectangle(15,200, Color.DEEPPINK);
        Rectangle r3 = new Rectangle(200,15, Color.CYAN);
        Rectangle r4 = new Rectangle(15,200, Color.PURPLE);
        r1.relocate(200, 200);
        r2.relocate(200, 215);
        r3.relocate(200, 400);
        r4.relocate(385, 200);


        Group g=new Group();
        g.getChildren().addAll(r1,r2,r3,r4);
        RotateTransition rotater1 = new RotateTransition(Duration.seconds(4),g);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(1500);
        rotater1.play();
        root.getChildren().add(g);
    }

    @Override
    public void disappear() {

    }
}
