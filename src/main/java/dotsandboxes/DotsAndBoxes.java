package dotsandboxes;
import dotsandboxes.GameElements.Box;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.observers.EventType;
import dotsandboxes.strategy.TurnStrategy;

import java.util.List;
import java.util.logging.Logger;
import java.io.Serializable;
public class DotsAndBoxes implements Serializable, IDotsAndBoxes{
    private static final Logger logger = Logger.getLogger(DotsAndBoxes.class.getName());
    private Grid grid;
    private int rows;
    private int columns;
    private boolean isOver = false;
    private List<Player> players;
    private TurnStrategy turnStrategy;
    private Player currentPlayer;
    private  Player firstPlayer;
    private Player secondPlayer;

    public DotsAndBoxes(Grid grid, TurnStrategy turnStrategy, List<Player> players){
        this.grid = grid;
        this.rows = grid.getRows();
        this.columns = grid.getColumns();
        this.turnStrategy = turnStrategy;
        this.players = players;
        this.firstPlayer = players.getFirst();
        this.secondPlayer = players.getLast();
        this.currentPlayer = firstPlayer;


    }


    private void passTurn(){
        if(currentPlayer == firstPlayer){
            currentPlayer = secondPlayer;
        }
        else{
            currentPlayer = firstPlayer;
        }
    }
    @Override
    public boolean claimEdge(Edge edge) {
        if(isGameOver()){
            return false;
        }
        if(edge.edgeClaimed()){
            return false;
        }
        int capturedBoxes =0;
        edge.claim();
        logger.info(currentPlayer.getName()+ " claimed edge");
        EventBus.getInstance().postEvent(EventType.CLAIMED_EDGE,edge);
        Box[][] boxes = grid.getBoxes();
        for(int i = 0; i<boxes.length; i++){
            for(int j = 0; j<boxes[i].length; j++){
                Box box = boxes[i][j];
                if(!box.boxIsOwned()&& box.boxIsCompleted()){
                    box.captureBox(currentPlayer);
                    capturedBoxes +=1;
                    EventBus.getInstance().postEvent(EventType.CAPTURED_BOX,box);
                    currentPlayer.addScore();
                }
            }
        }
        if(!turnStrategy.getsAnotherTurn(capturedBoxes)){
            passTurn();
            EventBus.getInstance().postEvent(EventType.CHARACTER_PLAYED_TURN,currentPlayer);
        }
        isOver = isAllEdgesClaimed();
        if(isOver){
            EventBus.getInstance().postEvent(EventType.GAME_DONE,currentPlayer);
        }
        return true;


    }
    private boolean isAllEdgesClaimed(){
        Edge[][] verticalEdges = grid.getVerticalEdges();
        Edge[][] horizontalEdges = grid.getHorizontalEdges();
        for(int i = 0; i<horizontalEdges.length; i++){
            for(int j = 0; j<horizontalEdges[i].length; j++){
                if(!horizontalEdges[i][j].edgeClaimed()){
                    return false;
                }
            }
        }
        for(int i = 0; i<verticalEdges.length; i++){
            for(int j = 0; j<verticalEdges[i].length; j++){
                if(!verticalEdges[i][j].edgeClaimed()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Player getWinnerPlayer() {
        if(!isGameOver()){
            return null;
        }
        if(firstPlayer.getScore()>secondPlayer.getScore()){
            return firstPlayer;
        }
        if(secondPlayer.getScore() > firstPlayer.getScore()){
            return secondPlayer;
        }
        logger.info("It's a tie");
        return null;
    }

    @Override
    public boolean isGameOver() {
        return isOver = isAllEdgesClaimed();
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void reset() {
        this.grid = Grid.getNewBuilder().setRows(rows).setColumns(columns).build();
        this.currentPlayer = firstPlayer;
        this.isOver = false;
        for(Player player: players){
            player.resetScore();
        }
        logger.info("Reset game");

    }
}
