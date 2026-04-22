import dotsandboxes.commands.CommandFactory;
import dotsandboxes.commands.ICommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    @Test
    void testNewGameCommand() {
        CommandFactory factory = new CommandFactory();
        boolean[] ran = {false};
        ICommand command = factory.createNewGameCommand(() -> ran[0] = true);
        assertTrue(command.execute());
        assertTrue(ran[0]);
    }

    @Test
    void testQuitGameCommand() {
        CommandFactory factory = new CommandFactory();
        boolean[] ran = {false};
        ICommand command = factory.createQuitGameCommand(() -> ran[0] = true);
        assertTrue(command.execute());
        assertTrue(ran[0]);
    }
}
