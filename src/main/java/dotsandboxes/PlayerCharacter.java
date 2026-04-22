package dotsandboxes;

import java.awt.*;
import java.util.logging.Logger;
public class PlayerCharacter extends Player {
    private static final Logger logger = Logger.getLogger(PlayerCharacter.class.getName());
    public PlayerCharacter(String name, Color color) {
        super(name,color);
    }

    @Override
    public void startTurn() {
        logger.info(getName() + ", it is your turn!");
    }
}
