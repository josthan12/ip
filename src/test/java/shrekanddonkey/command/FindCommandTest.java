package shrekanddonkey.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.Task;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

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

    @Test
    public void execute_emptyKeyword_marksResponseAsError() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test.txt");

        new FindCommand("").execute(tasks, ui, storage);

        assertTrue(ui.isError());
        assertTrue(ui.getRecordedOutput().contains("Please enter a keyword after 'find'."));
    }
}
