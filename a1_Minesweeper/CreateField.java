import java.io.File;
import java.util.Scanner;

/**
 * Create the output field with the calculation of the mines
 *  like in the description
 */
public class CreateField implements Assignment1{

    @Override
    public long minesweep(File inputfile) throws Exception{
        Scanner myReader = new Scanner(inputfile);
        long counter=0;
        int fieldCnt=0;

        while(myReader.hasNext()) {
            int rows = myReader.nextInt();
            int column = myReader.nextInt();

            //end of file
            if (rows == 0 && column == 0)
                break;

            char[][] inputField = new char[rows][column];
            //System.out.println("Rows: " + rows + "\nColumns: " + column);

            //store the field in a 2d-array
            for (int i = 0; i < rows; i++) {
                String line = myReader.next();
                for (int j = 0; j < column; j++) {
                    inputField[i][j] = line.charAt(j);
                }
            }

            //displayField(inputField);
            fieldCnt++;
            counter += createField(inputField, fieldCnt);
        }

        return counter;
    }

    /**
     * Create the output-field like in de description with the calculation
     * of the individual fields
     * @param field is the input-field from the .txt file stored in a 2-dim array
     * @param fieldCnt counts the number of fields
     * @return sum of all integers of one field
     */
    private long createField(char[][] field,int fieldCnt) {
        char[][] OutField = zeroFillField(field);
        int rightBorder = field[0].length;
        int bottomBorder = field.length;

        long counter=0;

        for(int i=0; i< field.length; i++){
            for(int j=0; j < field[i].length; j++){

                //if the current position is a '*' (means: mine)
                if(field[i][j] == '*')
                    OutField[i][j] = '*';

                //if-queries if there is a '*' in the radius --> current position +1
                //top left
                if((i-1)>=0 && (j-1)>=0 && field[i-1][j-1] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }

                //top middle
                if((i-1)>=0 && field[i-1][j] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }

                //top right
                if((i-1)>=0 && (j+1)<rightBorder && field[i-1][j+1] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }

                //left
                if((j-1)>=0 && field[i][j-1] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }

                //right
                if((j+1)<rightBorder && field[i][j+1] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }

                //bottom left
                if((i+1)<bottomBorder && (j-1)>=0 && field[i+1][j-1] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }

                //top middle
                if((i+1)<bottomBorder && field[i+1][j] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }

                //top right
                if((i+1)<bottomBorder && (j+1)<rightBorder && field[i+1][j+1] == '*') {
                    OutField[i][j] = (char) ((int) OutField[i][j] + 1);
                    counter++;
                }
            }
        }

        System.out.println("Field: " + fieldCnt);
        displayField(OutField);
        return counter;
    }

    /**
     * Initialize (fill) the output-array with zero
     * @param field input-field from the file (only used here for the size)
     * @return initialized output-array
     */
    private char[][] zeroFillField(char[][] field) {
        char[][] OutField = new char[field.length][field[0].length];

        for(int i=0; i< field.length; i++){
            for(int j=0; j < field[i].length; j++){
                OutField[i][j] = '0';
            }
        }
        //displayField(OutField);
        return OutField;
    }

    /**
     * Displays the field
     * @param field which should be displayed
     */
    private void displayField(char[][] field){
        for (char[] chars : field) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }
}
