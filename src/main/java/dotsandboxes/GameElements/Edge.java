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
    }

    public Dot getEdgeStart() {
        return edgeStart;
    }

    public Dot getEdgeEnd() {
        return edgeEnd;
    }
}
