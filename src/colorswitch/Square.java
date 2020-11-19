package colorswitch;
public class Square extends Obstacle{
    double side;
    Square(String ty,int x,int y,double s){
        super(ty,x,y);
        side=s;
    }
}
