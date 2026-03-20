package dotsandboxes.GameElements;

public class Box {
    private boolean boxIsCompleted;
    public Box(Edge top, Edge bottom, Edge left, Edge right) {
    }

    public boolean boxIsCompleted() {
        return boxIsCompleted;
    }
    public void setBoxIsCompleted(boolean trueOrFalse){
        this.boxIsCompleted = trueOrFalse;
    }
}
