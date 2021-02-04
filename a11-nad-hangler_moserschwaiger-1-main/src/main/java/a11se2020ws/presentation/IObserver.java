package a11se2020ws.presentation;

/**
 * Observer Interface (Observer-Pattern)
 */
public interface IObserver {
    /**
     * this methode is called by the subject
     * ,when new data is available an the
     * observer have to update his data
     */
    void update();
}
