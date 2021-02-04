package assignment3_int;

/** Interface for a calculator */
public interface Calculator {
   /** returns the result or throws an (Runtime-) Exception if anything goes wrong (e.g. illegal input) */
   public double calc(String[] input, GenericStack<Double> stack);

   /** returns an array-based stack */
   public GenericStack<Double> getArrayStack();

   /** returns a list-based stack */
   public GenericStack<Double> getListStack();
}