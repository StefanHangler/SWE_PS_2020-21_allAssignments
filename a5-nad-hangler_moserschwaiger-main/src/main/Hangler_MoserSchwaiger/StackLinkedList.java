package Hangler_MoserSchwaiger;

import assignment3_int.GenericStack;
import org.junit.jupiter.api.function.Executable;

import java.util.EmptyStackException;

/**
 * Stack implementation with a linked list structure
 * @param <E> Generics Parameter
 */
public class StackLinkedList<E> implements GenericStack<E>{
    Node head;
    private int size = 0;

    /**
     * Constructs a new {@link StackLinkedList} object and creates a ned linked list
     */
    public StackLinkedList() {
        this.head=null;
    }

    @Override
    public E pop() {
        if(!this.isEmpty()){
            E o = this.head.data;
            this.head = this.head.next;
            this.size--;
            return o;
        }
        //throw new RuntimeException("Stack is Empty! No element to pop."); //oder EmptyStackException()
        throw new EmptyStackException();

    }

    @Override
    public E peek(){
        if(!this.isEmpty())
            return this.head.data;

        //throw new RuntimeException("Stack is Empty! No element to peek."); //oder EmptyStackException()
        throw new EmptyStackException();

    }

    @Override
    public void push(E element) {
        Node p = new Node(element);
        p.next = this.head;
        this.head = p;
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public int getSize() { return this.size; }

    /**
     * Innerclass to create a Node for the linked list
     */
    private class Node{
        Node next;
        E data;

        public Node(E data) {
            this.next = null;
            this.data = data;
        }
    }
}
