package dotsandboxes.GameElements;

public class Edge {
    private final Dot edgeStart;
    private final Dot edgeEnd;
    private boolean edgeClaimed;

    public Edge(Dot edgeStart, Dot edgeEnd){
        this.edgeStart = edgeStart;
        this.edgeEnd = edgeEnd;
        this.edgeClaimed = false;
    }
    public void claim(){
        edgeClaimed=true;
    }
    public boolean edgeClaimed(){
        return edgeClaimed;
    } //Encapsulation keeps the claimed state private, protecting the internal state and using methods to access it

    public Dot getEdgeStart() {
        return edgeStart;
    }

    public Dot getEdgeEnd() {
        return edgeEnd;
    }
}
//Foundational state to track if edges during the game has been claimed by a player, so that the other player can not use it