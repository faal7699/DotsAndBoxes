import dotsandboxes.GameElements.Box;
import dotsandboxes.GameElements.Dot;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.Player;
import dotsandboxes.PlayerCharacter;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoxTest {
    @Test
    void boxIsComplete() {
        Edge top = new Edge(new Dot(0,0),new Dot(0,1));
        Edge bottom = new Edge(new Dot(1,0),new Dot(1,1));
        Edge left = new Edge(new Dot(0,0),new Dot(1,0));
        Edge right = new Edge(new Dot(0,1),new Dot(1,1));
        top.claim();
        bottom.claim();
        left.claim();
        right.claim();
        Box box = new Box(top,bottom,left,right);
        assertTrue(box.boxIsCompleted());
    }
    @Test
    void testCaptureBox(){
        Edge top = new Edge(new Dot(0,0),new Dot(0,1));
        Edge bottom = new Edge(new Dot(1,0),new Dot(1,1));
        Edge left = new Edge(new Dot(0,0),new Dot(1,0));
        Edge right = new Edge(new Dot(0,1),new Dot(1,1));
        top.claim();
        bottom.claim();
        left.claim();
        right.claim();
        Box box = new Box(top,bottom,left,right);
        Player player = new PlayerCharacter("Player 1", Color.BLACK);
        box.captureBox(player);
        assertEquals(player,box.getBoxOwner());
    }
}
