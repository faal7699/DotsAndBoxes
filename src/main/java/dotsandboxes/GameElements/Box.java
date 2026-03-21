package dotsandboxes.GameElements;

public class Box {
    private final Edge top;
    private final Edge bottom;
    private final Edge left;
    private final Edge right;

    private boolean boxIsCompleted;
    public Box(Edge top, Edge bottom, Edge left, Edge right) { //Dependency injection where edge objects are passed in the constructor, so that the box method recognizes all sides connected
        this.top=top;
        this.bottom=bottom;
        this.left=left;
        this.right=right;
    }

    public boolean boxIsCompleted() {
            return top.edgeClaimed() && bottom.edgeClaimed() && left.edgeClaimed() && right.edgeClaimed();
        }
    }
//This is a foundational class because it determines if a box is claimed or not based on the four edges connecting to each other once it is completed, it has a meaningful contribution towards the game's state
