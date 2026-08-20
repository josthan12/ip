//Implemented by Chatgpt
/**
 * Represents a task that takes place between a start and an end date or time.
 */
//Stores a from and to variable for date/time
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an event that is initially not done.
     *
     * @param description description of the task
     * @param from start date/time stored as text
     * @param to end date/time stored as text
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns this task with its event type marker and timing text.
     *
     * @return formatted event text
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
