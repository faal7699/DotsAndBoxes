package dotsandboxes;
import java.awt.*;
abstract public class Player {
    private final String name;
    private final Color color;
    private int score;
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        this.score = 0;
    }
    public String getName(){
        return name;
    }
    public Color getColor(){return color;}
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
