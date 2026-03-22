package dotsandboxes;

public class PlayerFactory {
    public Player createPlayer(String name){
        return new PlayerCharacter(name);
    }
}
