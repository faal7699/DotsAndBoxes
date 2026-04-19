package dotsandboxes;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    void launcherTest() {
        AtomicBoolean launched = new AtomicBoolean(false);
        Runnable originalLauncher = Main.gameLauncher;
        try {
            Main.gameLauncher = () -> launched.set(true);
            Main.main(new String[0]);
        } finally {
            Main.gameLauncher = originalLauncher;
        }
        assertTrue(launched.get());
    }
}
