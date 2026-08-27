package shrekanddonkey.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the completion-status icon returned by {@link Task#getStatusIcon()}.
 */
public class TaskTest {
    @Test
    public void getStatusIcon_newTask_spaceReturned() {
        Task task = new Task("read book");

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_completedTask_xReturned() {
        Task task = new Task("read book");
        task.markAsDone();

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_completedThenResetTask_spaceReturned() {
        Task task = new Task("read book");
        task.markAsDone();
        task.markAsNotDone();

        assertEquals(" ", task.getStatusIcon());
    }
}
