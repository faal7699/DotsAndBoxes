package dotsandboxes.GameElements;

public class Dot {
    private final int row;
    private final int column;
    public Dot(int row, int column){
        this.row=row;
        this.column=column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
//Another foundational class that represents a coordinate to contribute towards creating an edge for the game