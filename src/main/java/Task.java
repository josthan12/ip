//Made using Chatgpt
/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task that is initially not done.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Returns the shared status and description portion of a task.
     *
     * @return task text in the format shown by the chatbot
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
