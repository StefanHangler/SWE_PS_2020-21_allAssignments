package Hangler_MoserSchwaiger;

/**
 * This class represents the structure of an transaction
 */
public class TransactionDetails {
    private String receiver, sender, description;
    private int amount;
    private long timestamp;

    /**
     * constructs an {@link TransactionDetails} object
     * @param receiver name of the receiver who gets the money
     * @param sender name of the sender who sends the money
     * @param amount money which is transferred
     * @param timestamp time of the transfer
     * @param description transfer purpose
     */
    public TransactionDetails(String receiver, String sender, int amount, long timestamp, String description) {
        this.receiver = receiver;
        this.sender = sender;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    /** get and set methodes **/

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public int getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }
}
