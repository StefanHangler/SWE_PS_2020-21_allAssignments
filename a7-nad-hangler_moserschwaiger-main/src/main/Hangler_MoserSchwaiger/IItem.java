package Hangler_MoserSchwaiger;

/**
 * Interface for Items (book, cd or list) using the Composite Pattern
 */
public interface IItem {

    /** returns the name of an item (book, cd or list)*/
    String getName();

    /** returns the price of an item (book, cd or list)*/
    double getPrice();

    /**
     * Search for the item in the given list
     * @param item item we are searching for
     * @return item or null if item is not an element in the list
     */
    IItem getItem(String item);
}
