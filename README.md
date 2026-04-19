Design patterns: 
1. Factory Pattern: Used to create game objects like players and grids
   Used in PlayerFactory and GridFactory classes. In PlayerFactory we create players and in GridFactory we create grids with multiple sizes.
   With these factories we can avoid creating objects with new and scatter that in the code. 
2. Observer Pattern: Will be Used for Game class(subject) and a GameObserver interface that will be used by our Swing UI. 
   Game notifies observers when events happen(move/score change) and Swing UI will observe and respond such as displaying the update.
3. Facade Pattern: Used to hide complex operations being a simple interface. 
   Used in Game class, wrapping Player, Grid and score logic. The UI will only need to call methods in Game like takeTurn() without needing to know about all separate classes. 










Reference for creating grid:
- https://www.math.ucla.edu/~tom/Games/dots&boxes_hints.html

Ui References:
- https://github.com/SidDays/dots-swing.git
- https://github.com/tanzirbd/Dots-Boxes-.git

