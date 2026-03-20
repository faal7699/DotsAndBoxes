import dotsandboxes.Player;
import dotsandboxes.PlayerFactory;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {
    @Test
    void testCreatePlayer(){
        PlayerFactory playerFactory = new PlayerFactory();
        Player player = playerFactory.createPlayer("Player1");
        assertEquals("Player1", player.getName());
        assertEquals(0, player.getScore());
        player.addScore();
        assertEquals(1, player.getScore());
    }
}
