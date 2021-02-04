package Hangler_MoserSchwaiger;

public class Assignment3_Demo {

    public static void main(String[] args) {
        Assignment3 a3 = new Assignment3();
        String[] input = {"5.5", "2", "+", "3", "4", "-", "*"};

        System.out.print("Input: ");

        for(String s : input)
            System.out.print(s + ", ");

        System.out.println("\n\nOutput: \nArray: " + a3.calc(input, a3.getArrayStack()));
        System.out.println("LinkedList: " + a3.calc(input, a3.getListStack()));
    }
}
