import dotsandboxes.Player;
import dotsandboxes.PlayerCharacter;
import org.junit.jupiter.api.Test;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {
    @Test
    void testCreatePlayer(){
        Player player = new PlayerCharacter("Player1", Color.BLACK);
        assertEquals("Player1", player.getName());
        assertEquals(0, player.getScore());
        player.addScore();
        assertEquals(1, player.getScore());
    }
    @Test
    void testResetPlayerScore(){
        Player player = new PlayerCharacter("Player1", Color.BLACK);
        player.addScore();
        assertEquals(1, player.getScore());
        player.resetScore();
        assertEquals(0, player.getScore());
    }
    @Test
    void testGetPlayerColor(){
        Player player = new PlayerCharacter("Player1", Color.BLACK);
        assertEquals(Color.BLACK, player.getColor());
    }
}
