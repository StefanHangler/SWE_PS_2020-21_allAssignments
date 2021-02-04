package Hangler_MoserSchwaiger;

import assignment6_int.Cell;
import assignment6_int.MineField;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A MineField is regarded as a matrix of cells
 * (Design Pattern: Strategy Pattern is implemented)
 */
public class MineFieldA6 implements MineField{

    private final int lines;
    private final int columns;
    protected CellA6[][] matrix;
    private ShortestPath shortestPathStrategy;

    /**
     * constructs a {@link MineFieldA6} objects
     * @param lines     number of the MineField's lines
     * @param columns   number of the MineField's columns
     */
    public MineFieldA6(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;
        this.matrix = new CellA6[lines][columns];
    }


    /**
     * Returns the minimum number of cells that must be visited on the
     * shortest path between start and end cells (excluding both from the count).
     * Paths have only horizontal and vertical moves (no diagonals).
     * Returns -1 in case of error.
     *
     * @param start start cell
     * @param end   end cell
     * @return minimum number of cells that must be visited on path between start and end cell
     */
    @Override
    public int getShortestDistance(Cell start, Cell end) {
        int shortestDistanceCounter=-1;
        CellA6 startA6 = new CellA6(start.hasMine(),start.getLine(),start.getColumn());

        initializeSingleSource(this,startA6);

        PriorityQueue<CellA6> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(startA6);

        while(priorityQueue.size() > 0){
            CellA6 c = priorityQueue.poll();

            for(CellA6 n : c.getNeighbourCells(this))
                relax(c,n,priorityQueue);
        }

        CellA6 endA6 = this.getCellA6(end.getLine(),end.getColumn());

        while(endA6.prevCell != null){
            endA6 = endA6.prevCell;

            if((endA6.getColumn() == startA6.getColumn()) && (endA6.getLine() == startA6.getLine()))
                return ++shortestDistanceCounter;

            shortestDistanceCounter++;
        }

        return -1;
    }

    /**
     * Returns the cells of the shortest path between start and end according to
     * the current setting of the path type for the field.
     * The number of elements in the list must be equal to the shortest distance
     * as defined above. The cells start and end must not be in the list.
     * Returns null if no path could be found (error, path does not exist, etc.)
     *
     * Important: The implementation of this method must not depend on the type of path.
     * For example, no change must be done here when adding a new path type to the application.
     *
     * @param start start cell
     * @param end   end cell
     * @return list of cells from the shortest path
     */
    @Override
    public List<Cell> getShortestPath(Cell start, Cell end) {
        List<Cell> cellList = new ArrayList<>();

        CellA6 startA6 = new CellA6(start.hasMine(),start.getLine(),start.getColumn());

        initializeSingleSource(this,startA6);

        PriorityQueue<CellA6> priorityQueue = new PriorityQueue<>(shortestPathStrategy);
        priorityQueue.add(startA6);

        while(priorityQueue.size() > 0){
            CellA6 c = priorityQueue.poll();

            for(CellA6 n : c.getNeighbourCells(this))
                shortestPathStrategy.relaxMines(c,n,priorityQueue,this);
        }

        CellA6 endA6 = this.getCellA6(end.getLine(),end.getColumn());

        //create cell list backwards from end cell to start cell
        while(endA6.prevCell != null){

            endA6 = endA6.prevCell;
            if((endA6.getColumn() == startA6.getColumn()) && (endA6.getLine() == startA6.getLine()))
                return cellList;

            cellList.add(0,endA6);

        }
        System.out.println("Error, path does not exists");
        return null;
    }

    /**
     * Sets the strategy of the shortest Path (strategy design pattern)
     * @param strategy the strategy to set
     */
    public void setStrategy (ShortestPath strategy){
        shortestPathStrategy = strategy;
    }

    /**
     * initializes variables for Dijkstra (single source)
     * @param mineField MineField whose cells are to initialize
     * @param s start cell
     */
    private static void initializeSingleSource(MineFieldA6 mineField, CellA6 s) {
        for(int i=0; i< mineField.getNrOfLines(); i++){
            for(CellA6 c : mineField.matrix[i]) {
                c.distanceFromStart = Double.POSITIVE_INFINITY;
                c.prevCell = null;
                c.numbersOfMines=0;
                c.distanceToNextMine = Double.POSITIVE_INFINITY;
            }
        }
        s.distanceFromStart = 0;
    }

    /**
     * Sets the distance of n new, if the new distance is lower than the current
     * then n will be added to the PriorityQueue
     * @param c CellA6 from the PriorityQueue
     * @param n neighbour cell
     * @param priorityQueue the PriorityQueue
     */
    public void relax(CellA6 c, CellA6 n, PriorityQueue<CellA6> priorityQueue) {
        if (n.distanceFromStart > (c.distanceFromStart + 1)) {
            n.distanceFromStart = c.distanceFromStart + 1;
            n.prevCell = c;

            priorityQueue.add(n);
        }
    }

    @Override
    public Cell getCell(int line, int column) {
        return this.matrix[line][column];
    }

    /**
     * Returns the Cell of type CellA6 at the given line and column
     * @param line      line number of the cell
     * @param column    column number of the cell
     * @return CellA6 at given line and column
     * */
    public CellA6 getCellA6(int line, int column) {
        return this.matrix[line][column];
    }

    @Override
    public int getNrOfLines() {
        return this.lines;
    }

    @Override
    public int getNrOfColumns() {
        return this.columns;
    }

    /**
     * Method for displaying the MineField
     * mines are represented by '*', other cells are represented as '.'
     */
    public void displayMatrix(){
        if(this.matrix != null){
            for(int i=0; i<this.getNrOfLines(); i++){
                for(int j=0; j<this.getNrOfColumns(); j++){
                    System.out.print(this.matrix[i][j].hasMine() ? "*" : ".");
                }
                System.out.println();
            }
        }
        else
            throw new NullPointerException("Minefield or Minefield.matrix is null, therefore so something went wrong with the creation of a minefield");
    }

    /**
     * Method for displaying the path between to cells as (lineCell1,columnCell2)->(lineCell2,columnCell2)-->...
     * @param path  list of cells from the path
     */
    public void displayPath(List<Cell> path){
        if(this.matrix != null) {
            System.out.print("(" + path.get(0).getLine() + "," + path.get(0).getColumn() + ")");
            for (int i = 1; i < path.size(); i++)
                System.out.print("->(" + path.get(i).getLine() + "," + path.get(i).getColumn() + ")");
        }else
            throw new NullPointerException("minefield or Minefield.matrix is null, therefore so something went wrong with the creation of a minefield");
    }
}
