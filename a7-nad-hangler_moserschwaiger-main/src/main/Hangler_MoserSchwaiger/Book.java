package Hangler_MoserSchwaiger;

/**
 * Leaf Class of the Composite Pattern
 * creates a Book object
 */
public class Book implements IItem {
    private final String name;
    private final double price;
    private final long isbn;

    /**
     * constructs a {@link Book} object
     * @param name  the name of the book object
     * @param price the price of the book object
     * @param isbn  the ISBN number of the book
     */
    public Book(String name, double price, long isbn) {
        this.name = name;
        this.price = price;
        this.isbn = isbn;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public IItem getItem(String item) {
        return this.name.equals(item) ? this : null;
    }

    /**
     * returns the ISBN number of this book object
     * @return the ISBN number
     */
    public long getISBN(){
        return this.isbn;
    }
}
