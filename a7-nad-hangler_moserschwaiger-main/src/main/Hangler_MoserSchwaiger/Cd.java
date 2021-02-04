package Hangler_MoserSchwaiger;

/**
 * Leaf Class of the Composite Pattern
 * creates a CD object
 */
public class Cd implements IItem {
    private final String name;
    private final double price;

    /**
     * constructs a {@link Cd} object
     * @param name  name of the cd object
     * @param price price of the cd object
     */
    public Cd(String name, double price) {
        this.name = name;
        this.price = price;
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
}
