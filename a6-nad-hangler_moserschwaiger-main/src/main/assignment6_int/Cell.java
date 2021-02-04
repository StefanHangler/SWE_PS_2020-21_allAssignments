package assignment6_int;

/**
 * Interface representing a cell in a mine field
 *  
 */

public interface Cell {
    
    /**
     * returns the line number of this cell (starting at 0, increases top-down)
     */
    int getLine();
    
    /**
     * returns the column number of the cell (starting at 0, increases from left to right);
     */
    int getColumn();
    
    /**
     * returns true if this cell has a mine, false otherwise
     */
    boolean hasMine();
}
