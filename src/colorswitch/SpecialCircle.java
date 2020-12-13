package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class SpecialCircle extends Obstacle {
    private double radius;
    private Circle left, right;
    private transient Group g, g1, g2;
    private transient RotateTransition rotaterLeft, rotaterRight;

    public SpecialCircle(String ty, int x, int y, double r) {
        super(ty, x, y);
        radius = r;

        left = new Circle("ty", 1, 1, 1);
        right = new Circle("ty", 1, 1, 1);

        //Exchanging pink and purple arcs in the riht circle to maintain sync
        right.setPink(Color.PURPLE);
        right.setPurple(Color.DEEPPINK);

        //Getting the group for left and right Circle

        g1 = left.getGroup();
        g2 = right.getGroup();

        //Setting position of the 2 groups
        g1.setLayoutX(-110);
        g2.setLayoutX(119);

        rotaterLeft = new RotateTransition(Duration.seconds(10), g1);
        rotaterRight = new RotateTransition(Duration.seconds(10), g2);
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
        if(left.checkCollision(ball)==1)
            return 1;

        if(left.checkCollision(ball)==1)
            return 1;

        return 0;
    }

}
