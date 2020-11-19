package colorswitch;

import java.util.HashMap;

public class Game {
    HashMap<Integer,Obstacle> obstacles;
    HashMap<Integer,ColorSwitcher> colorswitchers;
    HashMap<Integer,Star> stars;
    Player player;
    Game(Player pl){
        player=pl;
    }
    void addObstacles(){

    }
    Obstacle removeObstacles(){
        return new Circle("Solid",0,0,10.5);
    }
    void addColorSwitcher(){

    }
    ColorSwitcher removeColorSwitcher(){
        return new ColorSwitcher(0,0);
    }
    void addStar(){

    }
    Star addStars(){
       return new Star(1,0,0);
    }
    int start(Player player){
        return 0;
    }
    int pause(){
        return -1;
    }
    int hitObstacle(){
        return 3;
    }
    int collectStars(){
        return -1;
    }
    String collectColorSwitcher(){
        return " ";
    }
    int fall(){
        return 2;
    }
}
