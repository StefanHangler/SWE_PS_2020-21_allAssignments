package Hangler_MoserSchwaiger;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Strategy-abstract class to implement different types of shortest Path algorithm
 * which implements the relax methode for dijkstra
 */
public abstract class ShortestPath implements Comparator<CellA6> {

    @Override
    public abstract int compare(CellA6 o1, CellA6 o2);

    /**
     * sorts a cell into a priority queue
     * @param c             current cell
     * @param n             neighbour cell
     * @param priorityQueue priorityQueue to sort into
     * @param mf            MineField of the cells
     */
    public void relaxMines(CellA6 c, CellA6 n, PriorityQueue<CellA6> priorityQueue, MineFieldA6 mf) {
        if (n.distanceFromStart > (c.distanceFromStart + 1)) {
            n.distanceFromStart = c.distanceFromStart + 1;
            n.prevCell = c;
            n.numbersOfMines = c.numbersOfMines;

            if (n.hasMine())
                n.numbersOfMines++;

            priorityQueue.add(n);
        }

        if(this.compare(c,n) > 0){
            priorityQueue.add(n);
        }
    }
}
