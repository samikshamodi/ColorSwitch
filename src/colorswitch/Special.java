package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class Special extends Obstacle{
    private Plus left, right;
    private transient Group g1, g2;
    private transient RotateTransition rotaterRight;
    Special(int y,double a){
        super(y,a);
        create();
    }
    public void create(){
        g=new Group();
        shapeList=new ArrayList<>();
        left = new Plus(-400,1);
        right = new Plus( -400,-1);

        //Exchanging cyan and yellow rectangle in the right plus to maintain sync
        right.setCyan(Color.YELLOW);
        right.setYellow(Color.CYAN);

        //Getting the group for left and right Circle

        g1 = left.getGroup();
        g2 = right.getGroup();

        //Setting position of the right Plus
        g1.setLayoutX(-10);
        g2.setLayoutX(205);

        rotater1 = new RotateTransition(Duration.seconds(speed), g1);
        rotaterRight = new RotateTransition(Duration.seconds(speed), g2);
        rotater1.setCycleCount(1500);
        rotaterRight.setCycleCount(1500);

        g.getChildren().addAll(g1, g2);
    }
    @Override
    public void appear(AnchorPane root) {
        rotater1.setByAngle(left.getAngle());
        rotater1.play();

        rotaterRight.setByAngle(right.getAngle());
        rotaterRight.play();

        g.setLayoutY(positionY);
        root.getChildren().add(g);
        visible=true;
    }

    @Override
    public int checkCollision(Shape ball) {
        if(left.checkCollision(ball)==1)
            return 1;

        if(left.checkCollision(ball)==1)
            return 1;

        return 0;
    }

}
