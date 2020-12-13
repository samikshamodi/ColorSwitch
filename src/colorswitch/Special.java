package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class Special extends Obstacle{
    Plus left, right;
    Group g, g1, g2;
    RotateTransition rotater, rotaterLeft, rotaterRight;
    Special(String ty,int x,int y){
        super(ty,x,y);

        left = new Plus("ty", 1, 1, 1);
        right = new Plus("ty", 1, 1, 1);

        //Exchanging cyan and yellow rectangle in the right plus to maintain sync
        right.setCyan(Color.YELLOW);
        right.setYellow(Color.CYAN);

        //Getting the group for left and right Circle

        g1 = left.getGroup();
        g2 = right.getGroup();

        //Setting position of the right Plus
        g1.setLayoutX(-10);
        g2.setLayoutX(205);

        rotaterLeft = new RotateTransition(Duration.seconds(5), g1);
        rotaterRight = new RotateTransition(Duration.seconds(5), g2);
        rotaterLeft.setCycleCount(1500);
        rotaterRight.setCycleCount(1500);

        g = new Group();
        g.getChildren().addAll(g1, g2);

        super.setGroup(g);
    }

    @Override
    public void appear(AnchorPane root) {
        rotaterLeft.setByAngle(360);
        rotaterLeft.play();

        rotaterRight.setByAngle(-360);
        rotaterRight.play();

        g.setLayoutY(-400);
        root.getChildren().add(g);
    }

    @Override
    public int checkCollision(Shape ball) {
        int c1 = left.checkCollision(ball);
        if(c1==1)
            return 1;

        int c2 = left.checkCollision(ball);
        if(c2==1)
            return 1;

        return 0;
    }

}
