package a8se2020ws.assignment8_int;

public interface Reconstructor {
  /** returns the reconstructed source skeleton of the provided class c; returns null if c is null */
  public String reconstructFromClass(Class c);
  
  /** returns the reconstructed source skeleton of the class represented by the provided (fully qualified) class name; returns null if className is null */
  public String reconstructFromClassName(String className) throws ClassNotFoundException;
}
