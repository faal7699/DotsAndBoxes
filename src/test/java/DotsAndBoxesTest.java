import dotsandboxes.DotsAndBoxes;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.GameElements.Grid;
import dotsandboxes.Player;
import dotsandboxes.PlayerCharacter;
import dotsandboxes.strategy.DefaultTurnStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DotsAndBoxesTest {
    private DotsAndBoxes game;
    private List<Player> players;
    @BeforeEach
    void setGame(){
        Grid grid = Grid.getNewBuilder().setRows(5).setColumns(5).build();
         players = Arrays.asList(
                new PlayerCharacter("Player 1", Color.BLACK),
                new PlayerCharacter("Player 2",Color.BLUE)
        );
        game = new DotsAndBoxes(grid,new DefaultTurnStrategy(), players);
    }
    @Test
    void testGetPlayers(){
        assertEquals(2,game.getPlayers().size());
    }
    @Test
    void testGameIsOver(){
        assertFalse(game.isGameOver());
    }
    @Test
    void testClaimEdge(){
        Edge edge = game.getGrid().getVerticalEdges()[0][0];
        assertTrue(game.claimEdge(edge));
    }
    @Test
    void testReset(){
        players.getFirst().addScore();
        game.reset();
        assertEquals(0,players.getFirst().getScore());

    }
    @Test
    void testGetWinnerPlayer(){
        assertNull(game.getWinnerPlayer());
    }
    @Test
    void testGetCurrentPlayer(){
        assertEquals("Player 1", game.getCurrentPlayer().getName());
    }
    @Test
    void testGetGrid(){
        assertEquals(5, game.getGrid().getColumns());
        assertEquals(5,game.getGrid().getRows());
    }
}


