package Hangler_MoserSchwaiger;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Class of the Composite Pattern
 * creates an ItemList
 */
public class ItemList implements IItem {
    private final List<IItem> items;
    private final String name;

    /**
     * constructs a {@link ItemList} object
     * @param name  name of the ItemList
     */
    public ItemList(String name) {
        this.items = new ArrayList<>();
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        double sumPrice = 0;

        for(IItem i : this.items)
            sumPrice += i.getPrice();

        return sumPrice;
    }

    /**
     * adds an Item to this itemList
     * @param item the item to add
     */
    public void addItem(IItem item){
        this.items.add(item);
    }

    /**
     * returns an item of this list
     * @param item  name of the item
     * @return the item
     */
    public IItem getItem (String item){
        if(this.name.equals(item))
            return this;

        else{
            for(IItem i : this.items){
                IItem iItem = i.getItem(item);

                if(iItem != null)
                    return iItem;
            }
        }

        //return null if item was not found
        return null;
    }

    /**
     * displays this itemList
     */
    public void displayItemList(){
        for(IItem i : this.items) {
            if (i.getClass().getSimpleName().equals("ItemList")) {
                ItemList j = (ItemList) i;
                j.displayItemList();
            }

            System.out.print(i.getName() + ", ");
        }
    }
}
