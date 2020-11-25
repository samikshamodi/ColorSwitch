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
        /*Rectangle r1 = new Rectangle(200, 15, Color.YELLOW);
        Rectangle r2 = new Rectangle(15,200, Color.DEEPPINK);
        Rectangle r3 = new Rectangle(200,15, Color.CYAN);
        Rectangle r4 = new Rectangle(15,200, Color.PURPLE);
        r1.relocate(200, 200);
        r2.relocate(200, 215);
        r3.relocate(200, 400);
        r4.relocate(385, 200);*/


        Rectangle r1 = new Rectangle(200,200);
        Rectangle r2 = new Rectangle(200,200);
        Rectangle r3 = new Rectangle(200,200);
        Rectangle r4 = new Rectangle(200,200);
        Rectangle r5 = new Rectangle(220,220,Color.BLACK);
        r1.relocate(185, 200);
        r2.relocate(200, 215);
        r3.relocate(215, 200);
        r4.relocate(200, 185);
        r5.relocate(190,190);
        r1.setStroke(Color.YELLOW);
        r2.setStroke(Color.DEEPPINK);
        r3.setStroke(Color.CYAN);
        r4.setStroke(Color.PURPLE);
        r1.setStrokeWidth(15);
        r2.setStrokeWidth(15);
        r3.setStrokeWidth(15);
        r4.setStrokeWidth(15);

        Group g=new Group();
        g.getChildren().addAll(r1,r2,r3,r4,r5);
        RotateTransition rotater1 = new RotateTransition(Duration.seconds(4),g);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(1500);
        rotater1.play();
        root.getChildren().add(g);

        //Circle, Shape, 3 rectangle subtract kiya, so that i can get 90 degree arc. did this 4 times.add them in one group. shape. subtract. shape. intersect

        /*RotateTransition rotater1 = new RotateTransition(Duration.seconds(4),r1);
        rotater1.setByAngle(360);
        rotater1.setCycleCount(1500);
        rotater1.play();
        RotateTransition rotater2 = new RotateTransition(Duration.seconds(4),r2);
        rotater2.setByAngle(360);
        rotater2.setCycleCount(1500);
        rotater2.play();
        RotateTransition rotater3 = new RotateTransition(Duration.seconds(4),r3);
        rotater3.setByAngle(360);
        rotater3.setCycleCount(1500);
        rotater3.play();
        RotateTransition rotater4 = new RotateTransition(Duration.seconds(4),r4);
        rotater4.setByAngle(360);
        rotater4.setCycleCount(1500);
        rotater4.play();
        RotateTransition rotater5 = new RotateTransition(Duration.seconds(4),r5);
        rotater5.setByAngle(360);
        rotater5.setCycleCount(1500);
        rotater5.play();
        root.getChildren().addAll(r1,r2,r3,r4,r5);*/


       /* ImageView img = new ImageView("/assets/c1.png");
        img.setFitHeight(200);
        img.setFitWidth(200);
        img.setX(200);
        img.setY(200);
        img.setPreserveRatio(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4),img);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1500);
        rotateTransition.play();
        root.getChildren().add(img);*/
    }

    @Override
    public void disappear() {

    }
}
