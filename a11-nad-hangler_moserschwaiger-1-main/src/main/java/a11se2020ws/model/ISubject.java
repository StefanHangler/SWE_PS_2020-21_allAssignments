package a11se2020ws.model;

import a11se2020ws.presentation.IObserver;

/**
 * An Interface for attaching and detaching views/applications
 * (represents a Subject in the Observer Design-Pattern)
 */
public interface ISubject {

    /**
     * registers a new view/observer (subscribe)
     * @param app view/observer object
     */
    void registerView(IObserver app);

    /**
     * removes an already existing view/observer (unsubscribe)
     */
    void removeView(IObserver app);
}
