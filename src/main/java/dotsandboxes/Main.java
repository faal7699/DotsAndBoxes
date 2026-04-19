package dotsandboxes;

public class Main {
    static Runnable gameLauncher = UIGame::launch;
    public static void main(String[] args) {
        launch(gameLauncher);
    }
    static void launch(Runnable uiLauncher) {
        uiLauncher.run();
    }
}
