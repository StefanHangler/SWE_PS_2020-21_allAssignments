package Hangler_MoserSchwaiger;

import assignment3_int.GenericStack;

/**
 * Stack implementation with a linked list structure
 * @param <E> Generics Parameter
 */
public class StackLinkedList<E> implements GenericStack<E>{
    Node head;

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
            return o;
        }
        throw new RuntimeException("Stack is Empty! No element to pop."); //oder EmptyStackException()
    }

    @Override
    public E peek() {
        if(!this.isEmpty())
            return this.head.data;

        throw new RuntimeException("Stack is Empty! No element to peek."); //oder EmptyStackException()
    }

    @Override
    public void push(E element) {
        Node p = new Node(element);
        p.next = this.head;
        this.head = p;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

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
