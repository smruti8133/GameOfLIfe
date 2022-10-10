package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);

        int r =StdIn.readInt();
        int c = StdIn.readInt();
        grid = new boolean[r][c];

        for (int i = 0; i < r; i++ ) { 
            for (int j = 0; j < c; j++) {
                grid[i][j] = Boolean.parseBoolean(StdIn.readString());
                if(grid[i][j] == true){
                    this.totalAliveCells++;
                }
                else{
                    continue;
                }
            }
        }
    }
    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return this.totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        // WRITE YOUR CODE HERE
        if (grid[row][col] == true) 
            return true;

        return false; // update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        // WRITE YOUR CODE HERE
        int cells = this.totalAliveCells;
        if(cells >= 1 ) 
            return true;

        return false; // update this line, provided so that code compiles
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        // WRITE YOUR CODE HERE
        int neighbors = 0;
        int r = row;
        int c = col;
        int row_len = grid.length -1;
        int col_len = grid[0].length-1;

        if(r ==0|| c ==0|| r == row_len|| c == col_len){

            //Top edges
            if(r == 0 && (col >= 1 && col < col_len)){
                if(grid[r][c-1]==true){
                    neighbors++;
                }
                if(grid[r][c+1]==true){
                    neighbors++;
                }
                if(grid[r+1][c-1]==true){
                    neighbors++;
                }
                if(grid[r+1][c]==true){
                    neighbors++;
                }
                if(grid[r+1][c+1]==true){
                    neighbors++;
                }
                if(grid[row_len][c-1]==true){
                    neighbors++;
                }
                if(grid[row_len][c]==true){
                    neighbors++;
                }
                if(grid[row_len][c+1]==true){
                    neighbors++;
                } 
            }

            //left edge
            else if((r >= 1 && r < row_len) && col == 0){
                if(grid[r-1][c]==true){
                    neighbors++;
                }
                if(grid[r+1][c]==true){
                    neighbors++;
                }
                if(grid[r-1][c+1]==true){
                    neighbors++;
                }
                if(grid[r][c+1]==true){
                    neighbors++;
                }
                if(grid[r+1][c+1]==true){
                    neighbors++;
                }
                if(grid[row-1][col_len]==true){
                    neighbors++;
                }
                if(grid[row][col_len]==true){
                    neighbors++;
                }
                if(grid[row+1][col_len]==true){
                    neighbors++;
                } 
            }

            //bottom edge
            else if(r == row_len && (col >= 1 && col < col_len)){
                if(grid[r][c+1]==true){
                    neighbors++;
                }
                if(grid[r][c-1]==true){
                    neighbors++;
                }
                if(grid[r-1][c]==true){
                    neighbors++;
                }
                if(grid[r-1][c-1]==true){
                    neighbors++;
                }
                if(grid[r-1][c+1]==true){
                    neighbors++;
                }
                if(grid[0][c-1]==true){
                    neighbors++;
                }
                if(grid[0][c]==true){
                    neighbors++;
                }
                if(grid[0][c+1]==true){
                    neighbors++;
                } 
            }

            //right edge
            else if((r >= 1 && r < row_len) && c == col_len){
                if(grid[r-1][c]==true){
                    neighbors++;
                }
                if(grid[r+1][c]==true){
                    neighbors++;
                }
                if(grid[r+1][c-1]==true){
                    neighbors++;
                }
                if(grid[r][c-1]==true){
                    neighbors++;
                }
                if(grid[r-1][c-1]==true){
                    neighbors++;
                }
                if(grid[r-1][0]==true){
                    neighbors++;
                }
                if(grid[r][0]==true){
                    neighbors++;
                }
                if(grid[r+1][0]==true){
                    neighbors++;
                } 
            }
        
            //left top corner
            else if(r == 0 && c ==0){
                if(grid[r+1][c] == true){
                    neighbors++;
                }
                if(grid[r][c+1] == true){
                    neighbors++;
                }
                if(grid[r+1][c+1] == true){
                    neighbors++;
                }
                if(grid[row_len][c] == true){
                    neighbors++;
                }
                if(grid[row_len][c+1] == true){
                    neighbors++;
                }
                if(grid[row_len][col_len] == true){
                    neighbors++;
                }
                if(grid[r][col_len]== true){
                    neighbors++;
                }
                if(grid[r+1][col_len]){
                    neighbors++;
                }
            }
            //bottom left corner
            else if(r == row_len && c == 0){
                if(grid[r-1][c]==true){
                    neighbors++;
                }
                if(grid[r][c+1]==true){
                    neighbors++;
                }
                if(grid[r-1][c+1]==true){
                    neighbors++;
                }
                if(grid[0][0]==true){
                    neighbors++;
                }
                if(grid[0][c+1]==true){
                    neighbors++;
                }
                if(grid[r-1][col_len]==true){
                    neighbors++;
                }
                if(grid[r][col_len]==true){
                    neighbors++;
                }
                if(grid[0][col_len]==true){
                    neighbors++;
                }
            }
            //top right corner
            else if(r ==0&&c == col_len){
                if(grid[r][c-1]==true){
                    neighbors++;
                }
                if(grid[r+1][c-1]==true){
                    neighbors++;
                }
                if(grid[r+1][c]==true){
                    neighbors++;
                }
                if(grid[row_len][c-1]==true){
                    neighbors++;
                }
                if(grid[row_len][c]==true){
                    neighbors++;
                }
                if(grid[row_len][0]==true){
                    neighbors++;
                }
                if(grid[r][0]==true){
                    neighbors++;
                }
                if(grid[r+1][0]==true){
                    neighbors++;
                }
            }
            //bottom right corner
            else if(r == row_len && c == col_len){
                if(grid[r-1][c]==true){
                    neighbors++;
                }
                if(grid[r-1][c-1]==true){
                    neighbors++;
                }
                if(grid[r][c-1]==true){
                    neighbors++;
                }
                if(grid[0][c-1]==true){
                    neighbors++;
                }
                if(grid[0][c]==true){
                    neighbors++;
                }
                if(grid[0][0]==true){
                    neighbors++;
                }
                if(grid[r][0]==true){
                    neighbors++;
                }
                if(grid[r-1][0]==true){
                    neighbors++;
                }
            }
        }
        else {
            if(grid[r][c-1]==true){
                neighbors++;
            }
            if(grid[r][c+1]==true){
                neighbors++;
            }
            if(grid[r-1][c]==true){
                neighbors++;
            }
            if(grid[r+1][c]==true){
                neighbors++;
            }
            if(grid[r+1][c+1]==true){
                neighbors++;
            }
            if(grid[r+1][c-1]==true){
                neighbors++;
            }
            if(grid[r-1][c-1]==true){
                neighbors++;
            }
            if(grid[r-1][c+1]==true){
                neighbors++;
            }
        }
        
        return neighbors; // update this line, provided so that code compiles
    }
    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {
        boolean[][] newgrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {

            for(int j = 0; j < grid[0].length; j++) {

                int aliveNeighbors = numOfAliveNeighbors(i, j);

                if (grid[i][j] == DEAD && aliveNeighbors == 3) {
                    newgrid[i][j] = ALIVE;
                }

                if(grid[i][j] == ALIVE){
                    if(aliveNeighbors == 2 || aliveNeighbors == 3) {
                        newgrid[i][j] = ALIVE;
                     }
                    if(aliveNeighbors == 1 || aliveNeighbors == 0) {
                        newgrid[i][j] = DEAD;
                     }
                    if(aliveNeighbors >= 4) {
                        newgrid[i][j] = DEAD;
                     }
                }    
            }
        }   
        return newgrid;// update this line, provided so that code compiles
    }
    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {
        grid = computeNewGrid();
    }
    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        // WRITE YOUR CODE HERE
        for (int i = 0; i < n; i++) {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {
        // WRITE YOUR CODE HERE
    
        int row_len = grid.length;
        int col_len = grid[0].length;
    
        // instantiate object to use union
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(grid.length, grid[0].length);
     
        // find all the alive cells AND their neighbors
        // connect neighbors using union
       for (int r = 0; r < row_len; r++){
           for(int c = 0; c < col_len; c++) {
            if(grid[r][c] == ALIVE) {
                    for(int i = -1; i < 2; i++) {
                        for(int j = -1; j < 2; j++) {
                            if(grid[(r + i + row_len) % row_len][(col_len + j + c) % col_len] == ALIVE){
                                uf.union(r, c, ((r + i + row_len) % row_len), ((col_len + j + c) % col_len));
                            }
                        }
                    }
                 }
            }    
        }

        ArrayList <Integer> uniqueRoots = new ArrayList<>();
        
        // find the root using find
        for (int r = 0; r < row_len; r++) {
            for(int c = 0; c < col_len; c++) {
                if(grid[r][c] == ALIVE) {
                    int temp = uf.find(r,c);
                    // count the number of unique roots
                    if (!uniqueRoots.contains(temp)) {
                        uniqueRoots.add(uf.find(r, c));
                    }
                }
            }
        }
        return uniqueRoots.size(); // update this line, provided so that code compiles
    }
}