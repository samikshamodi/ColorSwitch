package colorswitch;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class Star extends GameElements{
    String color;
    int value;
    ImageView img;
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
    public void appear(AnchorPane root) {
        //ImageView img = new ImageView("/assets/star.png");
        img = new ImageView("/assets/star.png");
        img.setFitHeight(50);
        img.setFitWidth(50);
        img.setX(275);
        img.setY(275);
        img.setPreserveRatio(true);
        root.getChildren().add(img);
    }

    @Override
    public int checkCollision(Shape ball) {

        return 0;
    }

    @Override
    public void disappear() {

    }

}
