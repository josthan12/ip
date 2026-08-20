//Implemented by Chatgpt
/**
 * Represents a task that must be completed by a specified date or time.
 */
//Just extends from Task but has a by variable
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a deadline that is initially not done.
     *
     * @param description description of the task
     * @param by deadline stored as text
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns this task with its deadline type marker and deadline text.
     *
     * @return formatted deadline text
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
