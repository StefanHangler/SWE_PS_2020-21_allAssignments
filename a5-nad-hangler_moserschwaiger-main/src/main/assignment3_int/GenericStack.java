package assignment3_int;

import org.junit.jupiter.api.function.Executable;

/** Interface for a generic stack */
public interface GenericStack<E> {
   public E pop();
   public E peek();
   public void push(E element);
   public boolean isEmpty();
   public int getSize();
}