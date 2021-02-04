package Hangler_MoserSchwaiger;

import java.util.PriorityQueue;

/**
 * set the strategy, the shortest path will be found
 * with the maximum number of mines
 */
public class MaxShortestPath extends ShortestPath {

    @Override
    public int compare(CellA6 o1, CellA6 o2) {
        if(o1.distanceFromStart == o2.distanceFromStart)
            return Double.compare(o2.numbersOfMines,o1.numbersOfMines);

        return Double.compare(o1.distanceFromStart,o2.distanceFromStart);
    }

    @Override
    public void relaxMines(CellA6 c, CellA6 n, PriorityQueue<CellA6> priorityQueue, MineFieldA6 mf) {
        super.relaxMines(c,n,priorityQueue,mf);
    }
}
