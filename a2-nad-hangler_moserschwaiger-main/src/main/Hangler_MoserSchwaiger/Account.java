package Hangler_MoserSchwaiger;

/**
 * This class represents the structure of an account
 */
public class Account {
    private String name;
    private int balance;

    /**
     * constructs an {@link Account} object
     * @param name of the accountholder
     * @param balance amount of money
     */
    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    /** get and set methodes **/

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
