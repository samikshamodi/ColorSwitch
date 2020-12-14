package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class SpecialCircle extends Obstacle {
    private double angle;
    private Circle left, right;
    private transient Group g1, g2;
    private transient RotateTransition rotaterRight;

    public SpecialCircle(int y) {
        super(y);
        angle = 360;
        left = new Circle(1);
        right = new Circle(1);

        //Exchanging pink and purple arcs in the riht circle to maintain sync
        right.setPink(Color.PURPLE);
        right.setPurple(Color.DEEPPINK);

        //Getting the group for left and right Circle

        g1 = left.getGroup();
        g2 = right.getGroup();

        //Setting position of the 2 groups
        g1.setLayoutX(-110);
        g2.setLayoutX(119);

        rotater1 = new RotateTransition(Duration.seconds(10), g1);
        rotaterRight = new RotateTransition(Duration.seconds(10), g2);
        rotater1.setCycleCount(1500);
        rotaterRight.setCycleCount(1500);

        g.getChildren().addAll(g1, g2);

    }

    @Override
    public void appear(AnchorPane root) {

        rotater1.setByAngle(360);
        rotater1.play();

        rotaterRight.setByAngle(-360);
        rotaterRight.play();

        g.setLayoutY(-400);
        root.getChildren().add(g);
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
