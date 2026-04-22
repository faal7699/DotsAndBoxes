package dotsandboxes;

import dotsandboxes.GameElements.Box;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.GameElements.Grid;
import dotsandboxes.commands.CommandFactory;
import dotsandboxes.commands.ICommand;
import dotsandboxes.observers.EventType;
import dotsandboxes.observers.IDotsAndBoxesObserver;
import dotsandboxes.strategy.DefaultTurnStrategy;
import dotsandboxes.strategy.TurnStrategy;
import dotsandboxes.strategy.TwoTurnStrategy;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class UIGame implements IDotsAndBoxesObserver {
    static Runnable uiStarter = () -> new UIGame().showGame();

    private static final String SIZE_3 = "3x3";
    private static final String SIZE_4 = "4x4";
    private static final String SIZE_5 = "5x5";
    private static final String DEFAULT_RULES = "Default Rules";
    private static final String TWO_TURN_RULES = "Two Turn Rules";

    private static final int DOT_SIZE = 10;
    private static final int CELL_SIZE = 64;
    private static final int BOARD_PADDING = 34;
    private static final int EDGE_CLICK_TOLERANCE = 12;

    private static final Color BACKGROUND = new Color(245, 241, 230);
    private static final Color PANEL_BACKGROUND = new Color(255, 252, 244);
    private static final Color TEXT = new Color(42, 48, 39);
    private static final Color MUTED_TEXT = new Color(92, 99, 88);
    private static final Color DOT_COLOR = new Color(31, 36, 33);
    private static final Color UNCLAIMED_EDGE = new Color(218, 209, 190);
    private static final Color PLAYER_ONE_COLOR = new Color(255, 0, 0);
    private static final Color PLAYER_TWO_COLOR = new Color(0, 88, 255);

    private JFrame frame;
    private final JPanel boardPanel;
    private final JLabel turnLabel;
    private final JLabel scoreLabel;
    private final JComboBox<String> sizeSelector;
    private final JComboBox<String> strategySelector;
    private final CommandFactory commandFactory;
    private final Map<Edge, Color> claimedEdgeColors;

    private DotsAndBoxes game;

    public UIGame() {
        boardPanel = new BoardPanel();
        turnLabel = new JLabel();
        scoreLabel = new JLabel();
        sizeSelector = new JComboBox<>(new String[]{SIZE_3, SIZE_4, SIZE_5});
        strategySelector = new JComboBox<>(new String[]{DEFAULT_RULES, TWO_TURN_RULES});
        commandFactory = new CommandFactory();
        claimedEdgeColors = new IdentityHashMap<>();
    }

    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            uiStarter.run();
        });
    }

    private void showGame() {
        if (GraphicsEnvironment.isHeadless() || Boolean.getBoolean("dotsandboxes.ui.testMode")) {
            startNewGame(SIZE_3);
            return;
        }

        frame = new JFrame("Dots and Boxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(BACKGROUND);
        frame.setLayout(new BorderLayout(16, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        frame.add(createHeaderPanel(), BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);

        boardPanel.setBackground(PANEL_BACKGROUND);
        boardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 217, 198), 1),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                onBoardClick(event.getX(), event.getY());
            }
        });
        EventBus.getInstance().attach(this);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                EventBus.getInstance().detach(UIGame.this);
            }

            @Override
            public void windowClosed(WindowEvent event) {
                EventBus.getInstance().detach(UIGame.this);
            }
        });

        startNewGame(SIZE_3);
        frame.setMinimumSize(new Dimension(520, 520));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(16, 0));
        headerPanel.setOpaque(false);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Dots and Boxes");
        titleLabel.setForeground(TEXT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        turnLabel.setForeground(TEXT);
        turnLabel.setFont(turnLabel.getFont().deriveFont(Font.BOLD, 15f));
        scoreLabel.setForeground(MUTED_TEXT);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(14f));

        JPanel textStack = new JPanel(new BorderLayout(0, 3));
        textStack.setOpaque(false);
        textStack.add(titleLabel, BorderLayout.NORTH);
        textStack.add(turnLabel, BorderLayout.CENTER);
        textStack.add(scoreLabel, BorderLayout.SOUTH);
        statusPanel.add(textStack);

        JPanel controlsPanel = new JPanel(new BorderLayout(12, 0));
        controlsPanel.setOpaque(false);

        JPanel leftControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftControls.setOpaque(false);
        JLabel boardLabel = new JLabel("Board:");
        boardLabel.setForeground(MUTED_TEXT);
        JLabel rulesLabel = new JLabel("Rules:");
        rulesLabel.setForeground(MUTED_TEXT);
        JButton applyButton = new JButton("Apply Grid");
        styleButton(applyButton);
        applyButton.addActionListener(event -> createNewGameCommand().execute());
        leftControls.add(boardLabel);
        leftControls.add(sizeSelector);
        leftControls.add(rulesLabel);
        leftControls.add(strategySelector);
        leftControls.add(applyButton);

        JPanel rightControls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightControls.setOpaque(false);
        JButton resetButton = new JButton("Reset Game");
        styleButton(resetButton);
        resetButton.addActionListener(event -> createResetGameCommand().execute());
        rightControls.add(resetButton);
        JButton quitButton = new JButton("Quit Game");
        styleButton(quitButton);
        quitButton.addActionListener(event -> createQuitGameCommand().execute());
        rightControls.add(quitButton);

        controlsPanel.add(leftControls, BorderLayout.WEST);
        controlsPanel.add(rightControls, BorderLayout.EAST);

        headerPanel.add(statusPanel, BorderLayout.WEST);
        headerPanel.add(controlsPanel, BorderLayout.EAST);
        return headerPanel;
    }

    private ICommand createNewGameCommand() {
        return commandFactory.createNewGameCommand(() -> startNewGame((String) sizeSelector.getSelectedItem()));
    }

    private ICommand createResetGameCommand() {
        return commandFactory.createNewGameCommand(this::resetCurrentGame);
    }

    private ICommand createQuitGameCommand() {
        return commandFactory.createQuitGameCommand(this::quitGame);
    }

    private void quitGame() {
        EventBus.getInstance().detach(this);
        if (frame != null) {
            frame.dispose();
        }
    }

    private void startNewGame(String size) {
        Grid grid = createGrid(size);
        List<Player> players = Arrays.asList(
                new PlayerCharacter("Player 1",PLAYER_ONE_COLOR),
                new PlayerCharacter("Player 2",PLAYER_TWO_COLOR)
        );
        game = new DotsAndBoxes(grid, createTurnStrategy(), players);
        claimedEdgeColors.clear();
        refreshBoard();
    }

    private TurnStrategy createTurnStrategy() {
        if (TWO_TURN_RULES.equals(strategySelector.getSelectedItem())) {
            return new TwoTurnStrategy();
        }
        return new DefaultTurnStrategy();
    }

    private Grid createGrid(String size) {
        if (SIZE_5.equals(size)) {
            return Grid.getNewBuilder().setRows(5).setColumns(5).build();
        }
        if (SIZE_4.equals(size)) {
            return Grid.getNewBuilder().setRows(4).setColumns(4).build();
        }
        return Grid.getNewBuilder().setRows(3).setColumns(3).build();
    }

    private void resetCurrentGame() {
        if (game == null) {
            startNewGame((String) sizeSelector.getSelectedItem());
            return;
        }
        game.reset();
        claimedEdgeColors.clear();
        refreshBoard();
    }

    private void onBoardClick(int x, int y) {
        if (game == null || game.isGameOver()) {
            return;
        }

        Grid grid = game.getGrid();
        Edge selectedEdge = findHorizontalEdge(grid, x, y);
        if (selectedEdge == null) {
            selectedEdge = findVerticalEdge(grid, x, y);
        }
        if (selectedEdge == null || selectedEdge.edgeClaimed()) {
            return;
        }

        Player claimingPlayer = game.getCurrentPlayer();
        boolean claimed = game.claimEdge(selectedEdge);
        if (!claimed) {
            return;
        }

        claimedEdgeColors.put(selectedEdge, getPlayerColor(claimingPlayer));
        refreshBoard();
    }

    @Override
    public void update(EventType eventType, Object eventObject) {
        if (game == null) {
            return;
        }

        switch (eventType) {
            case CLAIMED_EDGE:
            case CAPTURED_BOX:
            case CHARACTER_PLAYED_TURN:
                runOnEventDispatch(this::refreshBoard);
                break;
            case GAME_DONE:
                runOnEventDispatch(() -> {
                    refreshBoard();
                    Player winner = (eventObject instanceof Player) ? (Player) eventObject : null;
                    showGameOverMessage(winner);
                });
                break;
            default:
                break;
        }
    }

    private void runOnEventDispatch(Runnable action) {
        if (SwingUtilities.isEventDispatchThread()) {
            action.run();
            return;
        }
        SwingUtilities.invokeLater(action);
    }

    private void showGameOverMessage(Player winner) {
        if (frame == null) {
            return;
        }
        String message = winner == null
                ? "Game over! It's a tie."
                : "Game over! " + winner.getName() + " wins.";
        JOptionPane.showMessageDialog(frame, message, "Dots and Boxes", JOptionPane.INFORMATION_MESSAGE);
    }

    private Edge findHorizontalEdge(Grid grid, int x, int y) {
        Edge[][] horizontalEdges = grid.getHorizontalEdges();
        for (int row = 0; row < horizontalEdges.length; row++) {
            for (int column = 0; column < horizontalEdges[row].length; column++) {
                int startX = toPixel(column);
                int endX = toPixel(column + 1);
                int edgeY = toPixel(row);
                if (Math.abs(y - edgeY) <= EDGE_CLICK_TOLERANCE && x >= startX && x <= endX) {
                    return horizontalEdges[row][column];
                }
            }
        }
        return null;
    }

    private Edge findVerticalEdge(Grid grid, int x, int y) {
        Edge[][] verticalEdges = grid.getVerticalEdges();
        for (int row = 0; row < verticalEdges.length; row++) {
            for (int column = 0; column < verticalEdges[row].length; column++) {
                int edgeX = toPixel(column);
                int startY = toPixel(row);
                int endY = toPixel(row + 1);
                if (Math.abs(x - edgeX) <= EDGE_CLICK_TOLERANCE && y >= startY && y <= endY) {
                    return verticalEdges[row][column];
                }
            }
        }
        return null;
    }

    private void refreshBoard() {
        boardPanel.setPreferredSize(createBoardSize());
        refreshStatus();
        boardPanel.revalidate();
        boardPanel.repaint();
        if (frame != null) {
            frame.pack();
        }
    }

    private Dimension createBoardSize() {
        Grid grid = game.getGrid();
        int width = (grid.getColumns() * CELL_SIZE) + (BOARD_PADDING * 2) + DOT_SIZE;
        int height = (grid.getRows() * CELL_SIZE) + (BOARD_PADDING * 2) + DOT_SIZE;
        return new Dimension(width, height);
    }

    private void refreshStatus() {
        List<Player> players = game.getPlayers();
        Player firstPlayer = players.get(0);
        Player secondPlayer = players.get(1);

        if (game.isGameOver()) {
            Player winner = game.getWinnerPlayer();
            turnLabel.setText(winner == null ? "Game finished: tie game" : "Game finished: " + winner.getName() + " wins");
        } else {
            turnLabel.setText(game.getCurrentPlayer().getName() + "'s turn");
        }

        scoreLabel.setText(firstPlayer.getName() + ": " + firstPlayer.getScore()
                + "   |   "
                + secondPlayer.getName() + ": " + secondPlayer.getScore());
    }

    private void drawBoard(Graphics graphics) {
        if (game == null) {
            return;
        }

        Grid grid = game.getGrid();
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawCapturedBoxes(graphics2D, grid);
        drawEdges(graphics2D, grid.getHorizontalEdges(), true);
        drawEdges(graphics2D, grid.getVerticalEdges(), false);
        drawDots(graphics2D, grid);

        graphics2D.dispose();
    }

    private void drawCapturedBoxes(Graphics2D graphics, Grid grid) {
        Box[][] boxes = grid.getBoxes();
        for (int row = 0; row < boxes.length; row++) {
            for (int column = 0; column < boxes[row].length; column++) {
                Box box = boxes[row][column];
                if (!box.boxIsOwned()) {
                    continue;
                }

                graphics.setColor(withAlpha(getPlayerColor(box.getBoxOwner()), 105));
                graphics.fillRoundRect(
                        toPixel(column) + 7,
                        toPixel(row) + 7,
                        CELL_SIZE - 14,
                        CELL_SIZE - 14,
                        14,
                        14
                );
            }
        }
    }

    private void drawEdges(Graphics2D graphics, Edge[][] edges, boolean horizontal) {
        graphics.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int row = 0; row < edges.length; row++) {
            for (int column = 0; column < edges[row].length; column++) {
                Edge edge = edges[row][column];
                graphics.setColor(edge.edgeClaimed() ? claimedEdgeColors.getOrDefault(edge, TEXT) : UNCLAIMED_EDGE);

                int x1 = toPixel(column);
                int y1 = toPixel(row);
                int x2 = horizontal ? toPixel(column + 1) : x1;
                int y2 = horizontal ? y1 : toPixel(row + 1);
                graphics.drawLine(x1, y1, x2, y2);
            }
        }
    }

    private void drawDots(Graphics2D graphics, Grid grid) {
        graphics.setColor(DOT_COLOR);
        for (int row = 0; row <= grid.getRows(); row++) {
            for (int column = 0; column <= grid.getColumns(); column++) {
                int x = toPixel(column);
                int y = toPixel(row);
                graphics.fillOval(x - (DOT_SIZE / 2), y - (DOT_SIZE / 2), DOT_SIZE, DOT_SIZE);
            }
        }
    }

    private int toPixel(int gridCoordinate) {
        return BOARD_PADDING + (gridCoordinate * CELL_SIZE);
    }

    private void styleButton(JButton button) {
        button.setFocusable(false);
        button.setBackground(TEXT);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createEmptyBorder(7, 14, 7, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private Color getPlayerColor(Player player) {
        return player.getColor();
    }

    private Color withAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    private class BoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            drawBoard(graphics);
        }
    }
}
