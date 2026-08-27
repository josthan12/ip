import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//Implemented by Chatgpt
/**
 * Represents a task that takes place between a start and an end date or time.
 */
//Stores a from and to variable for date/time
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a", Locale.ENGLISH);
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an event that is initially not done.
     *
     * @param description description of the task
     * @param from start date and time
     * @param to end date and time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
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
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER)
                + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}
