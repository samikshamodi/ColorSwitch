package colorswitch;
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
