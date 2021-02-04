package assignment7_int;

import java.io.File;
import java.util.Optional;

public interface XMLData {
   /** loads the xml file or throws an Exception if anything goes wrong */
   public void loadXml(File input) throws Exception;
   
   /** returns the price of an item (cd, Book, or list) */
   public Optional<Double> getPrice(String item);
}