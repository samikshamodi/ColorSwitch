package colorswitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

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

    @Override
    public void appear(AnchorPane root) {

    }

    @Override
    public int checkCollision(Shape ball) {
        return 0;
    }

    @Override
    public void disappear() {

    }
}
