# A8_WS2020

Assignment 8: Reflection
----------------------------------------------

Write a Java application that reconstructs from a `.class` file (or a class) the source skeleton using **Java Reflection** [1]!

You will not be able to reconstruct the implementation of methods, 
but you should at least be able to reconstruct the following parts: 
member fields, method and constructor signatures (also handle private and static methods and fields!)

See an example for a reconstructed class below.

Your application must implement the interface provided below.

There is no need to handle 
* generics, 
* inner classes, anonymous classes, enums, native methods, abstract methods, annotations, varargs, ...
* naming conflicts (java.util.List and java.awt.List in the same class)

### Task 1:
Handle basic constructs:
* use proper indentation
* basic class signature (classname)
* fields (also private and static fields)
* modifiers (public, static, ...)
* methods with return type and parameters (also private and static methods)

E.g.,
```java
public class MyClass1 {
  public static int C ;
  private String s;
  
  MyClass1(String s){
  }
  
  public int m(int arg0, String arg1){
  }
}
```

### Task 2
In addition to Task 1, handle:
* packages
* imports (no fully qualified names everywhere and 'java.lang' is handled properly)
* arrays
* inheritance and interface implementations ("extends", "implements")
* declared exceptions ("throws IOException")

E.g.,
```java
package myPackage;

import java.util.List;
import java.io.Serializable;
import java.io.IOException;
import someOtherPackage.SomeOtherClass;

public class MyClass2 extends SomeOtherClass implements Serializable {
  public static int C ;
  private String s;
  private int[] a;
  
  MyClass2(String s){
  }
  
  public int m(int arg0, String arg1, final int[] arg3) throws IOException {
  }
}
```
### Task 3
In addition to Task 2: 
* your code is compilable (e.g., dummy return values are introduced)


E.g.,
```java
package myPackage;

import java.util.List;
import java.io.Serializable;
import java.io.IOException;
import someOtherPackage.SomeOtherClass;

public class MyClass3 extends SomeOtherClass implements Serializable {
  public static final int C = 0;
  private String s;
  private int[] a;
  
  MyClass3(String s){
  }
  
  public int m(int arg0, String arg1, final int[] arg3) throws IOException {
    return 0;
  }
}
```

## Interface
Your reconstructor class must have a default constructor and must implement the following interface:

```java
package assignment8_int;

public interface Reconstructor {
  /** returns the reconstructed source skeleton of the provided class c; returns null if c is null */
  public String reconstructFromClass(Class c);
  
  /** returns the reconstructed source skeleton of the class represented by the provided (fully qualified) class name; returns null if className is null */
  public String reconstructFromClassName(String className) throws ClassNotFoundException;
}
```

Test your implementation with older assignments (do not overwrite them!) and with classes from the Java library (e.g. `ArrayList`).

[1] http://docs.oracle.com/javase/tutorial/reflect/TOC.html



## Example for `ArrayList`:

```java
package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ArrayListDummy extends AbstractList implements List, RandomAccess, Cloneable, Serializable {

  private static final long serialVersionUID = 0;
  private transient Object[] elementData = null;
  private int size = 0;
  private static final int MAX_ARRAY_SIZE = 0;

  public ArrayListDummy(Collection param0) {
    System.out.println("constructor");
  }

  public ArrayListDummy() {
    System.out.println("constructor");
  }

  public ArrayListDummy(int param0) {
    System.out.println("constructor");
  }


  public void add(int param0, Object param1) {
    System.out.println("add");
  }

  public boolean add(Object param0) {
    System.out.println("add");
    return false;
  }

  public Object get(int param0) {
    System.out.println("get");
    return null;
  }

  public Object clone() {
    System.out.println("clone");
    return null;
  }

  public int indexOf(Object param0) {
    System.out.println("indexOf");
    return 0;
  }

  public void clear() {
    System.out.println("clear");
  }

  static int access$100(ArrayList param0) {
    System.out.println("access$100");
    return 0;
  }

  public boolean addAll(Collection param0) {
    System.out.println("addAll");
    return false;
  }

  public boolean addAll(int param0, Collection param1) {
    System.out.println("addAll");
    return false;
  }

  public Iterator iterator() {
    System.out.println("iterator");
    return null;
  }

  static Object[] access$200(ArrayList param0) {
    System.out.println("access$200");
    return null;
  }

  public boolean remove(Object param0) {
    System.out.println("remove");
    return false;
  }

  public Object remove(int param0) {
    System.out.println("remove");
    return null;
  }

  private void readObject(ObjectInputStream param0) throws IOException, ClassNotFoundException{
    System.out.println("readObject");
  }

  private void writeObject(ObjectOutputStream param0) throws IOException{
    System.out.println("writeObject");
  }

  public Object set(int param0, Object param1) {
    System.out.println("set");
    return null;
  }

  public void ensureCapacity(int param0) {
    System.out.println("ensureCapacity");
  }

  private void ensureCapacityInternal(int param0) {
    System.out.println("ensureCapacityInternal");
  }

  public void trimToSize() {
    System.out.println("trimToSize");
  }

  private String outOfBoundsMsg(int param0) {
    System.out.println("outOfBoundsMsg");
    return null;
  }

  private void rangeCheckForAdd(int param0) {
    System.out.println("rangeCheckForAdd");
  }

   Object elementData(int param0) {
    System.out.println("elementData");
    return null;
  }

  private void grow(int param0) {
    System.out.println("grow");
  }

  private static int hugeCapacity(int param0) {
    System.out.println("hugeCapacity");
    return 0;
  }

  public ListIterator listIterator() {
    System.out.println("listIterator");
    return null;
  }

  public ListIterator listIterator(int param0) {
    System.out.println("listIterator");
    return null;
  }

  public boolean removeAll(Collection param0) {
    System.out.println("removeAll");
    return false;
  }

  protected void removeRange(int param0, int param1) {
    System.out.println("removeRange");
  }

  public boolean retainAll(Collection param0) {
    System.out.println("retainAll");
    return false;
  }

  private boolean batchRemove(Collection param0, boolean param1) {
    System.out.println("batchRemove");
    return false;
  }

  private void fastRemove(int param0) {
    System.out.println("fastRemove");
  }

  private void rangeCheck(int param0) {
    System.out.println("rangeCheck");
  }

  public boolean contains(Object param0) {
    System.out.println("contains");
    return false;
  }

  public boolean isEmpty() {
    System.out.println("isEmpty");
    return false;
  }

  public int lastIndexOf(Object param0) {
    System.out.println("lastIndexOf");
    return 0;
  }

  public int size() {
    System.out.println("size");
    return 0;
  }

  public List subList(int param0, int param1) {
    System.out.println("subList");
    return null;
  }

  public Object[] toArray(Object[] param0) {
    System.out.println("toArray");
    return null;
  }

  public Object[] toArray() {
    System.out.println("toArray");
    return null;
  }

  static void subListRangeCheck(int param0, int param1, int param2) {
    System.out.println("subListRangeCheck");
  }

}
```


