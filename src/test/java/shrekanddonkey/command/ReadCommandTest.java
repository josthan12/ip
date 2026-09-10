package shrekanddonkey.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Tests executing {@link ReadCommand}.
 */
public class ReadCommandTest {

    @Test
    public void execute_emptyArguments_showsErrorMessage() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test.txt");

        new ReadCommand("").execute(tasks, ui, storage);

        assertTrue(ui.getRecordedOutput().contains("Please specify a file name after 'read'."));
    }

    @Test
    public void execute_nonExistentFile_showsFileNotFoundMessage() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test.txt");

        new ReadCommand("nonExistentFile12345.txt").execute(tasks, ui, storage);

        assertTrue(ui.getRecordedOutput().contains("File not found: ./data/nonExistentFile12345.txt"));
    }
}
