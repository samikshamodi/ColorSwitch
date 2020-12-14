package colorswitch;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Obstacle extends GameElements{
    private double speed;
    private final int direction;
    protected transient Group g;
    protected transient ArrayList<Shape> shapeList;
    protected transient RotateTransition rotater1;
    Obstacle(int y){
        super(y);
        speed=0;
        direction=1;
        g =new Group();
        shapeList=new ArrayList<>();
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

    public void setGroup(Group g)
    {
        this.g=g;
    }

    public void setShapeList(ArrayList<Shape> list)
    {
        this.shapeList=list;
    }


   @Override
    public void moveDown()
    {
        /*AnimationTimer animationTimer = new AnimationTimer() {
            int x=0;
            @Override
            public void handle(long l) {
                if(x>=55)
                {
                    stop();
                }
                group.setLayoutY(group.getLayoutY()+5);
                x+=5;
            }
        };
        animationTimer.start();*/
        g.setLayoutY(g.getLayoutY()+55);
    }

    @Override
    public double getLayoutY()
    {
        return g.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        g.setLayoutY(dy);

    }

    public void disappear(AnchorPane root) {
        root.getChildren().remove(g);
    }

    @Override
    public int checkCollision(Shape ball) {
        for(Shape s:shapeList)
        {
            Shape shapeIntersect=Shape.intersect(ball,s);
            //Collision happened
            if (shapeIntersect.getBoundsInLocal().getWidth() != -1) {
                if (s.getFill().equals(ball.getFill())) {
                    return 0;
                }
                else
                {
                    System.out.println("Collision detected "+s.getFill());
                    return 1;
                }
            }

        }
        return 0;
    }

}
