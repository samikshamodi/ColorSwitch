package colorswitch;

public abstract class GameElements {
    int position[];
    GameElements(int x, int y){
        position=new int[2];
        position[0]=x;
        position[1]=y;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
    void disappear(){

    }
    void appear(){

    }
}
