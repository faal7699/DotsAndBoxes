
# Dots and Boxes And Java Swing UI- Fahad And Roberto

Team members' names:

    Names: <Fahad Alshadoukhi, Roberto Sierra>
    Java Version: <25.0.2>

Design patterns:
1. Observer Pattern: We use the subject EventBus class similar to previous assignments, where UIGame attaches to it, and receives updates/events 
where it then updates the board, these events are ones that include a box being captured, player playing a turn, game is over
and claiming an edge. Our interface is IDotsAndBoxesObserver for observers.
2. Command Pattern: We implement a ICommand interface that both NewGameCommand and QuitGameCommand implement it, these commands are used
by the UIGame for creating a new game or quitting current game.
3. Builder Pattern: In our Grid class we use a builder, similar to the one we use for a maze in Polymorphia homework, Grid builder builds
the grid with it's builder methods such as setRows and setColumns and then we return that instance of build to use it for our needed grid.
4. Strategy Pattern: We have two strategies, DefaultTurnStrategy which is the standard way of playing dots and boxes, where you get another
turn if you capture a box and we also have TwoTurnStrategy where each player gets two turns in general. TurnStrategy interface gives the methods
used by the strategies and we inject it to DotsAndBoxes, ensuring we use dependency injection
5. Although we only need four patterns, we started with implementing a CommandFactory and we did not remove it as we thought it would be helpful 
for decoupling, as it creates a NewGameCommand and a QuitGameCommand, and then UI uses the factory
to create those commands.










Reference for creating grid:
- https://www.math.ucla.edu/~tom/Games/dots&boxes_hints.html

Ui References:
- https://github.com/SidDays/dots-swing.git
- https://github.com/tanzirbd/Dots-Boxes-.git

