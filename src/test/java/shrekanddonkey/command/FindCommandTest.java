package shrekanddonkey.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import shrekanddonkey.task.Task;
import shrekanddonkey.task.TaskList;

/**
 * Tests executing {@link FindCommand}.
 */
public class FindCommandTest {
    @Test
    public void execute_matchingKeyword_populatesMatchingTasks() {
        TaskList taskList = new TaskList();
        Task matchTask = new Task("read book");
        Task otherTask = new Task("sleep");
        taskList.add(matchTask);
        taskList.add(otherTask);

        FindCommand command = new FindCommand("book");
        assertEquals(1, taskList.findTasks("book").size());
        assertEquals(matchTask, taskList.findTasks("book").get(0));
    }
}