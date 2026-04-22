import dotsandboxes.GameElements.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GridTest {
    @Test
    void testCreate3x3Grid(){
        Grid grid = Grid.getNewBuilder().setRows(3).setColumns(3).build();

        assertEquals(3,grid.getColumns());
        assertEquals(3, grid.getRows());

    }
    @Test
    void testCreate5x5Grid(){
        Grid grid = Grid.getNewBuilder().setRows(5).setColumns(5).build();
        assertEquals(5,grid.getColumns());
        assertEquals(5, grid.getRows());

    }
    @Test
    void testGetBoxes(){
        Grid grid = Grid.getNewBuilder().setRows(5).setColumns(5).build();
        assertEquals(5,grid.getBoxes().length);
    }
    @Test
    void testGetEdges(){
        Grid grid = Grid.getNewBuilder().setRows(5).setColumns(5).build();
        assertEquals(6,grid.getHorizontalEdges().length);
        assertEquals(5, grid.getVerticalEdges().length);
    }

}
