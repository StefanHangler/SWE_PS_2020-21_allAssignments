package Hangler_MoserSchwaiger;

import assignment6_int.FieldGenerator;
import assignment6_int.MineField;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Creates a field and stores it in a 2d-matrix
 */
public class FieldGeneratorA6 implements FieldGenerator {

    private double chanceCellIsMine = 0.12;

    /**
     * Loads from file with same format as in Assignment 1 (first field only).
     * stores field in a 2d-matrix
     * @param inputFile file to reformat
     */
    @Override
    public MineField loadFieldFromFile(File inputFile) throws IOException {
        Scanner myReader = new Scanner(inputFile);

        int lines = myReader.nextInt();
        int column = myReader.nextInt();

        MineFieldA6 mineField = new MineFieldA6(lines, column);

        //store the field in a 2d-array
        for (int i = 0; i < lines; i++) {
            String line = myReader.next();
            for (int j = 0; j < column; j++) {
                    mineField.matrix[i][j] =
                            new CellA6(line.charAt(j) == '*',i,j);
            }
        }

        return mineField;
    }

    /**
     * Field with random mines
     * Returns a field with mines randomly placed
     * Returns null if field could not be created
     * @param nrLines   number of the random MineField's lines
     * @param nrColumns number of the random MineField's columns
     * @return a field with mines randomly placed, or null if there were no mines placed
     */
    @Override
    public MineField randomMineField(int nrLines, int nrColumns) {
        MineFieldA6 randomMineField = new MineFieldA6(nrLines, nrColumns);


        if(nrLines == 0 || nrColumns == 0)
            return null;

        for(int i = 0; i < nrLines; i++){
            for(int j = 0; j < nrColumns; j++){
                double randDouble = Math.random();
                if(randDouble < chanceCellIsMine)
                    randomMineField.matrix[i][j] = new CellA6(true,i,j);

                else
                    randomMineField.matrix[i][j] = new CellA6(false,i,j);

            }
        }
        return randomMineField;
    }

    /**
     * @param chanceCellIsMine the chance that a cell is a mine
     */
    public void setChanceCellIsMine(double chanceCellIsMine) {
        this.chanceCellIsMine = chanceCellIsMine;
    }
}
