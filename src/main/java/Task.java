//Made using Chatgpt
/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String dateTime;
    protected String endDateTime;

    /**
     * Creates a task that is initially not done.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this(description, "todo");
    }

    /**
     * Creates a task of the specified type without date/time details.
     *
     * @param description description of the task
     * @param taskType type of task: todo, deadline, or event
     */
    public Task(String description, String taskType) {
        this(description, taskType, "", "");
    }

    /**
     * Creates a deadline task with its deadline stored as text.
     *
     * @param description description of the task
     * @param taskType type of task
     * @param dateTime deadline text
     */
    public Task(String description, String taskType, String dateTime) {
        this(description, taskType, dateTime, "");
    }

    /**
     * Creates an event task with its start and end stored as text.
     *
     * @param description description of the task
     * @param taskType type of task
     * @param dateTime start date/time text
     * @param endDateTime end date/time text
     */
    public Task(String description, String taskType, String dateTime, String endDateTime) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.dateTime = dateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the character used to display the task's completion status.
     *
     * @return {@code X} when done, or a space when not done
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the letter used to identify this task's type.
     *
     * @return {@code T}, {@code D}, or {@code E}
     */
    public String getTaskIcon() {
        if (this.taskType.equals("todo")) {
            return "T";
        } else if (this.taskType.equals("deadline")) {
            return "D";
        } else if (this.taskType.equals("event")) {
            return "E";
        } else {
            return "?";
        }
    }

    /**
     * Returns the task with its type, status, description, and any timing details.
     *
     * @return task text in the format shown by the chatbot
     */
    @Override
    public String toString() {
        String dateTimeDetails = "";
        if (taskType.equals("deadline")) {
            dateTimeDetails = " (by: " + dateTime + ")";
        } else if (taskType.equals("event")) {
            dateTimeDetails = " (from: " + dateTime + " to: " + endDateTime + ")";
        }
        return "[" + getTaskIcon() + "][" + getStatusIcon() + "] "
                + description + dateTimeDetails;
    }
}
