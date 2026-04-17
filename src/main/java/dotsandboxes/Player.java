package dotsandboxes;
import java.io.Serializable;
abstract public class Player implements Serializable{
    private final String name;
    private int score;
    public Player(String name){
        this.name = name;
        this.score = 0;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public void addScore(){
        this.score += 1;
    }
    public void resetScore(){
        this.score = 0;
    }
    public abstract void startTurn();

}
