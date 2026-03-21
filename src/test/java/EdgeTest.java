import dotsandboxes.GameElements.Dot;
import dotsandboxes.GameElements.Edge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EdgeTest {
    @Test
    void testClaimedEdge(){
        Edge edge = new Edge(new Dot(2,3),new Dot(2,4));
                edge.claim();
        assertTrue(edge.edgeClaimed());
    }
    @Test
    void testStartAndEndOfEdge(){
        Edge edge = new Edge(new Dot(2,3),new Dot(2,4));
        assertEquals(2,edge.getEdgeStart().getRow());
        assertEquals(2,edge.getEdgeEnd().getRow());
        assertEquals(3,edge.getEdgeStart().getColumn());
        assertEquals(4,edge.getEdgeEnd().getColumn());
    }

}
