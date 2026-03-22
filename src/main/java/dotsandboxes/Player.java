package dotsandboxes;

abstract public class Player {
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
    public abstract void StartTurn();

}
