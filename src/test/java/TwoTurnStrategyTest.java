import dotsandboxes.strategy.TwoTurnStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwoTurnStrategyTest {
    @Test
    void testGetsAnotherTurn(){
        TwoTurnStrategy strategy = new TwoTurnStrategy();
        assertTrue(strategy.getsAnotherTurn(0));
        assertFalse(strategy.getsAnotherTurn(0));
    }
}
