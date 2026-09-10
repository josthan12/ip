package shrekanddonkey.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.task.Todo;
import shrekanddonkey.ui.Ui;

/**
 * Tests executing {@link WriteCommand}.
 */
public class WriteCommandTest {

    @Test
    public void execute_validStorage_writesTasksToFileSuccessfully(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath.toString());
        TaskList tasks = new TaskList();
        tasks.add(new Todo("write tests"));
        Ui ui = new Ui();

        new WriteCommand().execute(tasks, ui, storage);

        assertTrue(ui.getRecordedOutput().contains("Successfully written"));
        assertEquals(1, storage.loadLines().size());
    }
}
