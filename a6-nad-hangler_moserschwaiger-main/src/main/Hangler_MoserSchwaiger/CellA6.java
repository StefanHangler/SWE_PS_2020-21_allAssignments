package Hangler_MoserSchwaiger;

import assignment6_int.Cell;

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing a cell in a mine field
 */
public class CellA6 implements Cell, Comparable<CellA6>{

    private final boolean hasMine;
    private final int line;
    private final int column;

    //saves previous cell
    protected CellA6 prevCell;
    //saves distance from start
    protected double distanceFromStart;
    //number of mines in the current path
    protected double numbersOfMines;
    //distance to next mine around the cell
    protected double distanceToNextMine;

    /**
     * constructs a {@link CellA6} object
     * @param hasMine   true if the given cell has a mine, false otherwise
     * @param line      line number of the cell
     * @param column    column number of the cell
     */
    public CellA6(boolean hasMine, int line, int column) {
        this.hasMine = hasMine;
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean hasMine() {
        return this.hasMine;
    }

    /**
     * creates a List of this cell's neighbours (only vertical and horizontal neighbours)
     * @param mineField MineField of the cell
     * @return List of neighbours
     */
    protected List<CellA6> getNeighbourCells(MineFieldA6 mineField){
        List<CellA6> neighbourCells = new LinkedList<>();

        if(this.getColumn()-1 >= 0)
            neighbourCells.add(mineField.getCellA6(this.line,this.column-1));

        if(this.getLine()-1 >= 0)
            neighbourCells.add(mineField.getCellA6(this.line-1,this.column));

        if(this.getColumn()+1 < mineField.getNrOfColumns())
            neighbourCells.add(mineField.getCellA6(this.line,this.column+1));

        if(this.getLine()+1 < mineField.getNrOfLines())
            neighbourCells.add(mineField.getCellA6(this.line+1,this.column));

        return neighbourCells;
    }

    /**
     * determines the distance between this cell and the nearest mine and sets it in variable distanceToNextMine (also diagonal)
     * @param mineField MineField of the cell
     */
    public void setDistanceToNextMine(MineFieldA6 mineField){
        int dis = 1;

        while(true){
            for(int x=-(dis); x<=dis; x++){
                for(int y=-(dis); y<=dis; y++){
                    if(!outOfBounds(mineField,x,y) &&
                            (mineField.getCell(this.line + x, this.column + y).hasMine())){

                        this.distanceToNextMine = dis;
                        return;
                    }
                }
            }
            dis++;
        }
    }

    /**
     * checks whether the cell is out of the MineField's boundaries
     * @param mineField MineField of the cell
     * @param lines     actual line number
     * @param columns   actual column number
     * @return true if the cell is out of the MineField's boundaries, false otherwise
     */
    private boolean outOfBounds(MineFieldA6 mineField, int lines, int columns){
        return (this.column + columns) < 0 || (this.column + columns) >= mineField.getNrOfColumns()
                || (this.line + lines) < 0 || (this.line + lines) >= mineField.getNrOfLines();

    }

    @Override
    public int compareTo(CellA6 o) {
        return Double.compare(this.distanceFromStart,o.distanceFromStart);
    }
}
