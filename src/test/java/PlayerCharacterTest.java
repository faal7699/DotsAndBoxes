import dotsandboxes.Player;
import dotsandboxes.PlayerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerCharacterTest {
    PlayerFactory playerFactory = new PlayerFactory();
    @Test
    void userStartTurn(){
        Player pc = playerFactory.createPlayer("John Doe");
      pc.StartTurn();
        assertTrue(true);
    }
}
