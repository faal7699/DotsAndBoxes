package dotsandboxes;
import dotsandboxes.GameElements.Dot;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.GameElements.Box;



public class Grid {
    private final int rows;
    private final int columns;
    private final Edge[][] horizontalEdges;
    private final Edge[][] verticalEdges;
    private final Dot[][] dots;
    private final Box[][] boxes;




    Grid(int rows,int columns){
        this.rows = rows;
        this.columns = columns;
        this.horizontalEdges = new Edge[rows + 1][columns];
        this.verticalEdges = new Edge[rows][columns+1];
        this.dots = new Dot[rows+1][columns+1];
        this.boxes = new Box[rows][columns];
        // creating dots
        for(int i = 0; i < rows+1; i++){
            for(int j = 0; j < columns+1; j++){
                dots[i][j] = new Dot(i,j);
            }
        }
        // creating horizontal edges
        for(int i = 0; i < rows+1; i++){
            for(int j = 0; j < columns; j++){
                horizontalEdges[i][j] = new Edge(dots[i][j],dots[i][j+1]);
            }
        }
        // vertical edges
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns+1; j++){
                verticalEdges[i][j] = new Edge(dots[i][j],dots[i+1][j]);
            }
        }
        // creating boxes
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                boxes[i][j] = new Box(horizontalEdges[i][j],horizontalEdges[i+1][j], verticalEdges[i][j], verticalEdges[i][j+1]
                );
            }
        }

    }
    public int getRows(){
        return rows;
    }
    public int getColumns() {
        return columns;
    }





}
