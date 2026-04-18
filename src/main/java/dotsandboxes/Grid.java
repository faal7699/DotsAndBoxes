package dotsandboxes;
import dotsandboxes.GameElements.Dot;
import dotsandboxes.GameElements.Edge;
import dotsandboxes.GameElements.Box;
import java.io.Serializable;
import java.util.logging.Logger;

public class Grid implements Serializable{
    private final int rows;
    private final int columns;
    private final Edge[][] horizontalEdges;
    private final Edge[][] verticalEdges;
    private final Dot[][] dots;
    private final Box[][] boxes;




    Grid(int rows, int columns){
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
    public Box[][] getBoxes(){return boxes;}
    public Edge[][] getHorizontalEdges() {return horizontalEdges;}
    public Edge[][] getVerticalEdges() {return verticalEdges;}
    public static Builder getNewBuilder(){return new Builder();}
    public static class Builder {

        private final static int MIN_ROWS =3;
        private final static int MIN_COLUMNS =3;
        private static final Logger logger = Logger.getLogger(Builder.class.getName());
        private int rows = MIN_ROWS;
        private int columns = MIN_COLUMNS;
        private Builder(){}
        public Builder setColumns(int columns){this.columns= columns; return this;}
        public Builder setRows(int rows){
            this.rows= rows;
            return this;
        }
        public Grid build(){
            if(rows<MIN_ROWS || columns<MIN_COLUMNS){
                logger.warning("Size is too small, set up 3x3 grid instead.");
                rows = MIN_ROWS;
                columns = MIN_COLUMNS;
            }
            return new Grid(rows,columns);
        }




    }






}
