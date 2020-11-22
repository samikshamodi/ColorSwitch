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
    void appear(AnchorPane root) {

    }

    @Override
    void disappear() {

    }
}
