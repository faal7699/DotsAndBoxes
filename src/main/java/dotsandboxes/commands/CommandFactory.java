package dotsandboxes.commands;

public class CommandFactory {
    public ICommand createNewGameCommand(){
        return new NewGameCommand();
    }
    public ICommand createQuitGameCommand(){
        return new QuitGameCommand();
    }
}
