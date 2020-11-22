package colorswitch;

public class Player {
    String name;
    int currentScore,highestScore,totalStars;
    Ball ball;

    Player(String n){
        name=n;
        currentScore=0;
        highestScore=0;
        ball = new Ball(0,0);
        totalStars=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCScore() {
        return currentScore;
    }

    public void setCScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getHScore() {
        return highestScore;
    }

    public void setHScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }
    void saveGame(){

    }
    void loadGame(){

    }
    void newGame(){

    }
    void printSavedGames(){

    }

    int menu(Main main){
        return 0;
    }
}
