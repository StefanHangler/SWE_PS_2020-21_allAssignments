package Hangler_MoserSchwaiger.Model;

/**
 * An abstract class for constructing new Receivers
 * (represents an Observer in the Observer Design-Pattern)
 */
public abstract class INewsReceiver {
    private final String name;
    private final String[] hashtag;
    private final String LINE = System.lineSeparator();

    /**
     * constructs a {@link INewsReceiver} object
     * @param name      name of the receiver
     * @param hashtag   the hashtags for which the receiver wants to get updates
     */
    public INewsReceiver(String name, String hashtag) {
            this.name = name;
            this.hashtag = hashtag.split("#");
        }

    /**
     * method to update the receiver with a certain Event
     * @param event the event to be updated with
     */
    public void update(Event event) {
        for (String s : this.hashtag) {
            //true if news contains no hashtag or receiver set no hashtag (length < 2) or receivers hashtag contains in news
            if (!event.getNews().contains("#") || this.hashtag.length < 2 || (!s.isEmpty() && event.getNews().contains("#" + s))) {
                System.out.println(LINE + this.getClass().getSimpleName() + ": " + this.name + " notified by " + event.getSource()
                        + LINE + "Time: " + event.getDateTime()
                        + LINE + "News: " + event.getNews());

                return;
            }
        }
    }
}

