package dotsandboxes.GameElements;

public class Box {
    private Edge top;
    private Edge bottom;
    private Edge left;
    private Edge right;

    private boolean boxIsCompleted;
    public Box(Edge top, Edge bottom, Edge left, Edge right) {
        this.top=top;
        this.bottom=bottom;
        this.left=left;
        this.right=right;
    }

    public boolean boxIsCompleted() {
            return top.edgeClaimed() && bottom.edgeClaimed() && left.edgeClaimed() && right.edgeClaimed();
        }
    }

