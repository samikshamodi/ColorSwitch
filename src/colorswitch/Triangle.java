package colorswitch;
public class Triangle extends Obstacle{
    double side;
    Triangle(String ty,int x,int y,double s){
        super(ty,x,y);
        side=s;
    }
}
