package assignment6_int;

import java.io.File;
import java.io.IOException;

/**
 * Creates a field.
 * 
 */
public interface FieldGenerator {
    
    /**
     * Loads from file with same format as in Assignment 1 (first field only).
     */
    public MineField loadFieldFromFile(File inputFile) throws IOException;
    
    /**
     * Field with random mines 
     * 
     * Returns a field with mines randomly placed
     * Returns null if field could not be created
     */
    public MineField randomMineField(int nrLines, int nrColumns);
}
