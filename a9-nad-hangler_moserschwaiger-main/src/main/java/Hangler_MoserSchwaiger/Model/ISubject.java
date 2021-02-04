package Hangler_MoserSchwaiger.Model;

/**
 * An Interface for attaching and detaching Receivers
 * (represents a Subject in the Observer Design-Pattern)
 */
public interface ISubject {

    /**
     * registers a new receiver (subscribe)
     * @param nr number of the receiver
     */
    void registerNewsReceiver(INewsReceiver nr);

    /**
     * removes an already existing receiver (unsubscribe)
     */
    void removeNewsReceiver(INewsReceiver nr);
}
