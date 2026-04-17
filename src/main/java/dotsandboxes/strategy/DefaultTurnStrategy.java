package dotsandboxes.strategy;

public class DefaultTurnStrategy implements TurnStrategy {
    @Override
    public boolean getsAnotherTurn(int capturedBoxes) {
        return capturedBoxes>0;
    }
}
