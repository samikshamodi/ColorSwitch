package colorswitch;

import javafx.scene.layout.AnchorPane;

public class Star extends GameElements{
    String color;
    int value;
    Star(int v,int x, int y){
        super(x,y);
        value = v;
        if(value == 1)
            color="White";
        else
            color="Green";
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    @Override
    void appear(AnchorPane root) {

    }

    @Override
    void disappear() {

    }

}
