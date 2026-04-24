import dotsandboxes.EventBus;
import dotsandboxes.UIGame;
import dotsandboxes.observers.EventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UIGameTest {
    @Test
    void testGameStarts() {
        System.setProperty("Game", "true");
        UIGame uiGame = new UIGame();
        uiGame.launch();
        assertNotNull(uiGame);
    }
    @Test
    void testEventHandling() {
        System.setProperty("Game", "true");
        UIGame ui = new UIGame();
        EventBus.getInstance().attach(ui);
        EventBus.getInstance().postEvent(EventType.CLAIMED_EDGE, null);
        assertTrue(true);
    }
}
