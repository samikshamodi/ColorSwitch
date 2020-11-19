package colorswitch;

import java.util.Random;

public class ColorSwitcher extends GameElements{
    Random rand;
    ColorSwitcher(int x, int y){
        super(x,y);
        rand = new Random();
    }
    String generateColor(){
        return " ";
    }

}
