package colorswitch;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public abstract class Obstacle extends GameElements{
    double speed;
    int direction;
    String type;
    private Group group;
    Obstacle(String ty,int x, int y){
        super(x,y);
        speed=0;
        direction=1;
        type=ty;
        group =new Group();
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

    public void setGroup(Group g)
    {
        this.group=g;
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
        group.setLayoutY(group.getLayoutY()+55);
    }

    @Override
    public double getLayoutY()
    {
        return group.getLayoutY();
    }

    @Override
    public void setLayoutY(double dy) {
        group.setLayoutY(dy);

    }

}
