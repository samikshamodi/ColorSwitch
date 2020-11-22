package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    void appear(AnchorPane root) {
        ImageView img = new ImageView("/assets/c1.png");
        img.setFitHeight(150);
        img.setFitWidth(150);
        img.setX(225.5);
        img.setY(200);
        img.setPreserveRatio(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4),img);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1500);
        rotateTransition.play();
        root.getChildren().add(img);
    }

    @Override
    void disappear() {

    }
}
