package dotsandboxes;

public class GridFactory {
    public Grid create3x3Grid(){
        return new Grid(3,3);
    }
    public Grid create5x5Grid(){
        return new Grid(5,5);
    }
}
