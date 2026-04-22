import dotsandboxes.Player;
import dotsandboxes.PlayerCharacter;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerCharacterTest {

    @Test
    void userStartTurn(){
        Player pc = new PlayerCharacter("John Doe", Color.BLACK);
      pc.startTurn();
        assertTrue(true);
    }
}
