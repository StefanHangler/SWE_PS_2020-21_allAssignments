import java.io.File;

public class Minesweeper {
    public static void main(String[] args) throws Exception {
        CreateField cf = new CreateField();

        //name of the input-field stored in a File variable
        File file = new File("Assignment1-test.txt");
        if(file.exists())
            System.out.println("Numbers of mines:    " + cf.minesweep(file));
        else
            System.out.println("File does not exists");
    }
}
