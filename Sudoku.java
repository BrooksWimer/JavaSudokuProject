/**
 * Sudoku.java
 * 
 * Implementation of a class that represents a Sudoku puzzle and solves
 * it using recursive backtracking.
 *
 * Computer Science 112, Boston University
 *
 * your name: 
 *
 */

import java.io.*;   // allows us to read from a file
import java.util.*;

public class Sudoku {    
    // The current contents of the cells of the puzzle. 
    private int[][] grid;
    
    /*
     * Indicates whether the value in a given cell is fixed 
     * (i.e., part of the initial configuration).
     * valIsFixed[r][c] is true if the value in the cell 
     * at row r, column c is fixed, and false otherwise.
     */
    private boolean[][] valIsFixed;
    
    /*
     * This 3-D array allows us to determine if a given subgrid (i.e.,
     * a given 3x3 region of the puzzle) already contains a given
     * value.  We use 2 indices to identify a given subgrid:
     *
     *    (0,0)   (0,1)   (0,2)
     *
     *    (1,0)   (1,1)   (1,2)
     * 
     *    (2,0)   (2,1)   (2,2)
     * 
     * For example, subgridHasVal[0][2][5] will be true if the subgrid
     * in the upper right-hand corner already has a 5 in it, and false
     * otherwise.
     */
    private boolean[][][] subgridHasVal;
    
    /*** ADD YOUR ADDITIONAL FIELDS HERE. ***/
    
    private boolean[][] rowHasVal;

    private boolean[][] columnHasVal;

    private int row_length = 9;
    private int col_length = 9;
    /* 
     * Constructs a new Puzzle object, which initially
     * has all empty cells.
     */
    public Sudoku() {
        this.grid = new int[9][9];
        this.valIsFixed = new boolean[9][9];     
        
        /* 
         * Note that the third dimension of the following array is 10,
         * because we need to be able to use the possible values 
         * (1 through 9) as indices.
         */
        this.subgridHasVal = new boolean[3][3][10];        

        /*** INITIALIZE YOUR ADDITIONAL FIELDS HERE. ***/
        this.rowHasVal = new boolean[9][10];
        
        this.columnHasVal = new boolean[9][10];

    }
    
    /*
     * Place the specified value in the cell with the specified
     * coordinates, and update the state of the puzzle accordingly.
     */
    public void placeVal(int val, int row, int col) {
        this.grid[row][col] = val;
        this.subgridHasVal[row/3][col/3][val] = true;
        
        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
        this.rowHasVal[row][val] = true;
        this.columnHasVal[col][val] = true;
    }
        
    /*
     * remove the specified value from the cell with the specified
     * coordinates, and update the state of the puzzle accordingly.
     */
    public void removeVal(int val, int row, int col) {
        this.grid[row][col] = 0;
        this.subgridHasVal[row/3][col/3][val] = false;
        
        /*** UPDATE YOUR ADDITIONAL FIELDS HERE. ***/
        this.rowHasVal[row][val] = false;
        this.columnHasVal[col][val] = false;
    }  
        
    /*
     * read in the initial configuration of the puzzle from the specified 
     * Scanner, and use that config to initialize the state of the puzzle.  
     * The configuration should consist of one line for each row, with the
     * values in the row specified as integers separated by spaces.
     * A value of 0 should be used to indicate an empty cell.
     * 
     * You should not change this method.
     */
    public void readConfig(Scanner input) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int val = input.nextInt();
                this.placeVal(val, r, c);
                if (val != 0) {
                    this.valIsFixed[r][c] = true;
                }
            }
            input.nextLine();
        }
    }
                
    /*
     * Displays the current state of the puzzle.
     * You should not change this method.
     */        
    public void printGrid() {
        for (int r = 0; r < 9; r++) {
            this.printRowSeparator();
            for (int c = 0; c < 9; c++) {
                System.out.print("|");
                if (this.grid[r][c] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + this.grid[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        this.printRowSeparator();
    }
        
    // A private helper method used by display() 
    // to print a line separating two rows of the puzzle.
    private void printRowSeparator() {
        for (int i = 0; i < 9; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }
    
    /*** ADD ANY ADDITIONAL METHODS HERE. ***/

    public int[] findSubgrid(int row, int col){
        int[] row_col = new int[2];
        if (row < 3){
            row_col[0] = 0;
        }
        else if (row < 6){
            row_col[0] = 1;
        }
        else{
            row_col[0] = 2;
        }
        if (col < 3){
            row_col[1] = 0;
        }
        else if (col < 6){
            row_col[1] = 1;
        }
        else{
            row_col[1] = 2;
        }

        return row_col;
    }

    public boolean isSafe(int val, int row, int col){
        int[] grid_vals = findSubgrid(row, col);
        return (!(this.subgridHasVal[grid_vals[0]][grid_vals[1]][val] ||
                this.rowHasVal[row][val] || this.columnHasVal[col][val]));
    }

    public int row(int n){
        int row = -1;
        if (n/9 < 1){
            row = 0;
        }
        else if (n/9 >= 1 && n/9 < 2){
            row = 1;
        }
        else if (n/9 >= 2 && n/9 < 3){
            row = 2;
        }
        else if (n/9 >= 3 && n/9 < 4){
            row = 3;
        }
        else if (n/9 >= 4 && n/9 < 5){
            row = 4;
        }
        else if (n/9 >= 5 && n/9 < 6){
            row = 5;
        }
        else if (n/9 >= 6 && n/9 < 7){
            row = 6;
        }
        else if (n/9 >= 7 && n/9 < 8){
            row = 7;
        }
        else{
            row = 8;
        }
        return row;
    }

    public int col(int n){
        int col = -1;
        if (n/9 < 1){
            col = n;
        }
        else if (n/9 >= 1 && n/9 < 2){
            col = n-9;
        }
        else if (n/9 >= 2 && n/9 < 3){
            col = n-(9*2);
        }
        else if (n/9 >= 3 && n/9 < 4){
            col = n-(9*3);
        }
        else if (n/9 >= 4 && n/9 < 5){
            col = n-(9*4);
        }
        else if (n/9 >= 5 && n/9 < 6){
            col = n-(9*5);
        }
        else if (n/9 >= 6 && n/9 < 7){
            col = n-(9*6);
        }
        else if (n/9 >= 7 && n/9 < 8){
            col = n-(9*7);
        }
        else{
            col = n-(9*8);
        }
        return col;
    }
         
    /*
     * This is the key recursive-backtracking method.  Returns true if
     * a solution has already been found, and false otherwise.
     * 
     * Each invocation of the method is responsible for finding the
     * value of a single cell of the puzzle. The parameter n
     * is the number of the cell that a given invocation of the method
     * is responsible for. We recommend that you consider the cells
     * one row at a time, from top to bottom and left to right,
     * which means that they would be numbered as follows:
     *
     *     0  1  2  3  4  5  6  7  8
     *     9 10 11 12 13 14 15 16 17
     *    18 ...
     */
    private boolean solveRB(int n) {
                
        /* 
         * The following return statement allows the initial code to
         * compile.  Replace it with your full implementation of the
         * recursive-backtracking method.
         */
        if (n == 81){
            return true;
        }
        
        int row = this.row(n);
        int col = this.col(n);
        if (!(this.valIsFixed[row][col])){
            for (int i = 0; i < 10; i++){
                if(isSafe(i, row, col)){
                    this.placeVal(i, row, col);

                    if (this.solveRB(n+1)){
                        return true;
                    }
                    this.removeVal(i, row, col);
                    }
                }
            }
        else{
            if (this.solveRB(n+1)){
                return true;
            }
            
            }
        return false;
    } 
    
    /*
     * public "wrapper" method for solveRB().
     * Makes the initial call to solveRB, and returns whatever it returns.
     */
    public boolean solve() { 
        boolean foundSol = this.solveRB(0);
        return foundSol;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Sudoku puzzle = new Sudoku();
        
        System.out.print("Enter the name of the puzzle file: ");
        String filename = scan.nextLine();
        
        try {
            Scanner input = new Scanner(new File(filename));
            puzzle.readConfig(input);
        } catch (IOException e) {
            System.out.println("error accessing file " + filename);
            System.out.println(e);
            System.exit(1);
        }
        
        System.out.println();
        System.out.println("Here is the initial puzzle: ");
        puzzle.printGrid();
        System.out.println();
        
        if (puzzle.solve()) {
            System.out.println("Here is the solution: ");
        } else {
            System.out.println("No solution could be found.");
            System.out.println("Here is the current state of the puzzle:");
        }
        puzzle.printGrid();  
    }    
}
