package dotsandboxes;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.GameElements.Grid;

public interface IDotsAndBoxes {
    boolean claimEdge(Edge edge);
    Player getWinnerPlayer();
    boolean isGameOver();
    Grid getGrid();
    void reset();
}
