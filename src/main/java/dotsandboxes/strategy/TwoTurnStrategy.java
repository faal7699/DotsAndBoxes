package dotsandboxes.strategy;

public class TwoTurnStrategy implements TurnStrategy{
    private static final int TURN_AMOUNT = 2;
    private int turnCounter = 0;
    @Override
    public boolean getsAnotherTurn(int capturedBoxes) {
        turnCounter+=1;
        if(turnCounter <TURN_AMOUNT){
            return true;
        }
        turnCounter =0;
        return false;
    }
}
