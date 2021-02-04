package Hangler_MoserSchwaiger;

import java.util.PriorityQueue;

/**
 * set the strategy, the shortest path will be found
 * with the maximum distance to all mines around the path
 */
public class SafestShortestPath extends ShortestPath {

    @Override
    public int compare(CellA6 o1, CellA6 o2) {
        if(o1.distanceFromStart == o2.distanceFromStart)
            return Double.compare(o2.distanceToNextMine,o1.distanceToNextMine);

        return Double.compare(o1.distanceFromStart,o2.distanceFromStart);
    }

    @Override
    public void relaxMines(CellA6 c, CellA6 n, PriorityQueue<CellA6> priorityQueue, MineFieldA6 mf) {
        if (n.distanceFromStart > (c.distanceFromStart + 1)) {
            n.distanceFromStart = c.distanceFromStart + 1;
            n.prevCell = c;
            n.numbersOfMines = c.numbersOfMines;
            n.setDistanceToNextMine(mf);

            if (n.hasMine())
                n.numbersOfMines++;

            priorityQueue.add(n);
        }

        if(this.compare(c,n) > 0){
            priorityQueue.add(n);
        }
    }
}
