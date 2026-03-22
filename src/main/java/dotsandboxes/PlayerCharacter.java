package dotsandboxes;

import java.util.logging.Logger;

public class PlayerCharacter extends Player {
    private static final Logger logger = Logger.getLogger(PlayerCharacter.class.getName());
    public PlayerCharacter(String name) {
        super(name);
    }

    @Override
    public void StartTurn() {
        logger.info(getName() + ", it is your turn!");
    }
}
