package dotsandboxes.commands;
public class QuitGameCommand implements ICommand{
    private final Runnable quitGameAction;

    public QuitGameCommand(Runnable quitGameAction) {
        this.quitGameAction = quitGameAction;
    }

    @Override
    public boolean execute() {
        if (quitGameAction == null) {
            return false;
        }
        quitGameAction.run();
        return true;
    }
}
