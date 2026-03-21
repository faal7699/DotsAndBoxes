import dotsandboxes.GameElements.Dot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DotTest {
    @Test
    void testGetRowAndColumn(){
        Dot dot = new Dot(1,4);
        assertEquals(1,dot.getRow());
        assertEquals(4,dot.getColumn());
    }
}
