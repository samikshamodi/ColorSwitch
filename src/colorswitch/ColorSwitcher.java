package colorswitch;

import javafx.scene.layout.AnchorPane;

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
    public void disappear() {

    }
}
