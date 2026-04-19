package dotsandboxes.commands;

public class NewGameCommand implements ICommand{
    private final Runnable newGameAction;

    public NewGameCommand(Runnable newGameAction) {
        this.newGameAction = newGameAction;
    }

    @Override
    public boolean execute() {
        if (newGameAction == null) {
            return false;
        }
        newGameAction.run();
        return true;
    }
}
