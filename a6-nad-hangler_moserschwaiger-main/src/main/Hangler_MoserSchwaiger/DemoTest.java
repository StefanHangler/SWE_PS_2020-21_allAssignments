package Hangler_MoserSchwaiger;

import assignment6_int.Cell;
import assignment6_int.FieldGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DemoTest {
    public static void main(String[] args) throws IOException {
        FieldGenerator fieldGenerator = new FieldGeneratorA6();
        MineFieldA6 mField = (MineFieldA6) fieldGenerator.loadFieldFromFile(new File("Test_MineField.txt"));
        mField.displayMatrix();

        /* //random MineField
        FieldGenerator fieldGeneratorRandom = new FieldGeneratorA6();
        MineFieldA6 mFieldRandom = (MineFieldA6) fieldGeneratorRandom.randomMineField(4,5);
        */
        Cell c00 = mField.getCell(0,0);
        Cell c14 = mField.getCell(1,4);
        Cell c36 = mField.getCell(3,6);

        System.out.println("Cell c15 should be a mine: expected: true -> actual: " + mField.getCell(1,5).hasMine());
        System.out.println("Cell c15 should be not a mine: expected: false -> actual: " + c14.hasMine());

        int distance1 = mField.getShortestDistance(c00, c14); // should return 4
        int distance2 = mField.getShortestDistance(c00, c36); // should return 8

        System.out.println("Distance1: expected: 4 -> actual: " + distance1);
        System.out.println("Distance2: expected: 8 -> actual: " + distance2);


        List<Cell> path;

        //TODO: instruct mField that we are interested in 'min' paths.
        mField.setStrategy(new MinShortestPath());

        path = mField.getShortestPath(c00, c14);
        // (1,0)->(1,1)->(1,2)->(1,3) has one mine
        // any shortest path with one mine on it is ok
        System.out.println("PathMin: \n(1,0)->(1,1)->(1,2)->(1,3) has one mine");
        mField.displayPath(path);

        path = mField.getShortestPath(c14, c36);
        // (2,4)->(3,4)->(3,5) has no mine. Others have also no mine.

        System.out.println("\n PathMin: \n(2,4)->(3,4)->(3,5) has no mine");
        mField.displayPath(path);

        //TODO: instruct mField that we are interested in 'max' paths.
        mField.setStrategy(new MaxShortestPath());

        path = mField.getShortestPath(c00, c14);
        // (0,1)->(0,2)->(0,3)->(0,4) is one of the shortest paths with two mines

        System.out.println("\n PathMax: \n(0,1)->(0,2)->(0,3)->(0,4) is one of the shortest paths with two mines");
        mField.displayPath(path);

        path = mField.getShortestPath(c00, c36);
        // there are several paths with 3 mines, one of them is
        // (0,1)->(0,2)->(0,3)->(0,4)->(0,5)->(1,5)->(1,6)->(2,6)

        System.out.println("\n PathMax: \n(0,1)->(0,2)->(0,3)->(0,4)->(0,5)->(1,5)->(1,6)->(2,6) - have 3 mines");
        mField.displayPath(path);

        // for the bonus part:

        //TODO: instruct mField that we are interested in 'safest' paths.

        mField.setStrategy(new SafestShortestPath());

        path = mField.getShortestPath(c00, c36);
        // only one path is two steps away from any mine:
        // (1,0)->(2,0)->(3,0)->(3,1)->(3,2)->(3,3)->(3,4)->(3,5)
        // any other shortest path has a cell that is at most 1 step from a mine

        System.out.println("\n PathSafest: \n(1,0)->(2,0)->(3,0)->(3,1)->(3,2)->(3,3)->(3,4)->(3,5) - has a cell that is at most 1 step from a mine");
        mField.displayPath(path);
    }
}
