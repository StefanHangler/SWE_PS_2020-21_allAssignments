package Hangler_MoserSchwaiger;

import assignment3_int.GenericStack;
import org.junit.jupiter.api.function.Executable;

import java.util.EmptyStackException;

/**
 * Stack implementation with an array structure
 * @param <E> Generics Parameter
 */
public class StackArray<E> implements GenericStack<E>{

    private E[] stack;
    // index of the last pushed element
    private int top = -1;
    private int size = 0;

    /**
     * Constructs a {@link StackArray} object and creates a new array with the given size and class
     * @param size of the array
     */
    public StackArray(int size) {
        this.stack = (E[]) new Object[size];
    }

    @Override
    public E pop() {
        if(!this.isEmpty()){
            E o = this.stack[top];
            this.stack[top] = null;
            this.top--;
            return o;
        }
        //throw new RuntimeException("Stack is empty! No element to pop."); //oder EmptyStackException() werfen
        throw new EmptyStackException();
    }

    @Override
    public E peek(){
        if(!this.isEmpty())
            return this.stack[top];

        //throw new RuntimeException("Stack is empty! No element to peek."); // oder EmptyStackException
        throw new EmptyStackException();
    }

    @Override
    public void push(E element) {
        if(this.top < this.stack.length - 1)
            this.stack[++this.top] = element;

        else
            //throw new RuntimeException("Stack is full! No element could be added. The maximum number of elements is " + this.stack.length);
            throw new StackOverflowError();
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public int getSize() { return this.top+1; }
}
