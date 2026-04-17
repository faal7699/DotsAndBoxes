package dotsandboxes;
import dotsandboxes.GameElements.Edge;
public interface IDotsAndBoxes {
    boolean claimEdge(Edge edge);
    Player getWinnerPlayer();
    boolean isGameOver();
    Grid getGrid();
    void reset();
}
