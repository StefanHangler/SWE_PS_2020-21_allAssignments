# A3_WS2020

Starter repo for Assignment3

----------------------------------------
Assignment 3: Stack-based RPN Calculator
----------------------------------------

Write two different implementations for the given interface of a stack (LIFO):

```java
package assignment3_int;

/** Interface for a generic stack */
public interface GenericStack<E> {
   public E pop();
   public E peek();
   public void push(E element);
   public boolean isEmpty();
}
``` 
One implementation shall be based on an array with constant size; the other shall be based on a linked list. 
Both classes shall implement the interface above.

The stack classes must not rely on the Java Collections Framework. 
Implement them yourself. Go for a simple solution, but include some basic error handling
 (e.g. pop-operation on an empty stack).
 
Use your stacks to implement an RPN (reverse-polish-notation) calculator.
The calculator shall work with both types of stacks (and any other implementation of the stack interface) 
without any modification (see "dependency injection").
Your calculator class must have a default constructor and must implement the following interface:

```java
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
``` 

The program takes as input an expression in RPN and outputs the calculated result. 
The program shall support the 4 basic arithmetical operations `+ - * /`. 

```
Example:
input: {"1.5", "2", "+", "3", "4", "-", "*"}
output: -3.5
```

You may write as many classes as you need. The class that implements the Calculator interface must be named **Assignment3** and must have only one constructor, namely the empty default constructor, as follows: 

```
package <your_team_name>;

/*
* Implements assignment interface
* ...
*
*/
public class Assignment3 ... {

  // empty default constructor, do not change
  public Assignment3(){
  
  }

// Your implementation here, no other constructors allowed   
   
}
```
