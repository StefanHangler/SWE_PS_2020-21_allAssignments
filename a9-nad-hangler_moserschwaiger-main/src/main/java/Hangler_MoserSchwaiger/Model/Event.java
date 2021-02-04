package Hangler_MoserSchwaiger.Model;

/**
 * Class representing an Event (source, news, date)
 */
public class Event {
    private final String source;
    private final String news;
    String dateTime;

    /**
     * constructs a {@link Event} object
     * @param source    the source of the Event
     * @param news      the news/the content of the Event
     * @param dateTime  the exact date and time of the Event
     */
    public Event(String source, String news, String dateTime) {
        this.source = source;
        this.news = news;
        this.dateTime = dateTime;
    }

    /**
     * @return the news of the Event
     */
    public String getNews() {
        return news;
    }

    /**
     * @return the source of the Event
     */
    public String getSource() {
        return source;
    }

    /**
     * @return the date and time of the Event
     */
    public String getDateTime() {
        return dateTime;
    }
}
