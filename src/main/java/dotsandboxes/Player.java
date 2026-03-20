package dotsandboxes;

public class Player {
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


}
