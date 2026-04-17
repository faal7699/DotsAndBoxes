import dotsandboxes.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GridTest {
    @Test
    void testCreate3x3Grid(){
        GridFactory gridFactory = new GridFactory();
        Grid grid = gridFactory.create3x3Grid();
        assertEquals(3,grid.getColumns());
        assertEquals(3, grid.getRows());

    }
    @Test
    void testCreate5x5Grid(){
        GridFactory gridFactory = new GridFactory();
        Grid grid = gridFactory.create5x5Grid();
        assertEquals(5,grid.getColumns());
        assertEquals(5, grid.getRows());

    }

}
