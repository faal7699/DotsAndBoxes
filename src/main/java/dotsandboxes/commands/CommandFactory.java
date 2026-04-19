package dotsandboxes.commands;

public class CommandFactory {
    public ICommand createNewGameCommand(Runnable newGameAction){
        return new NewGameCommand(newGameAction);
    }

    public ICommand createQuitGameCommand(Runnable quitGameAction){
        return new QuitGameCommand(quitGameAction);
    }
}
