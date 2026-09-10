package shrekanddonkey.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import shrekanddonkey.task.Task;
import shrekanddonkey.task.Todo;

/**
 * Tests saving and loading tasks in {@link Storage}.
 */
public class StorageTest {

    @Test
    public void saveAndLoad_taskList_savedAndLoadedCorrectly(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("testFile.txt");
        Storage storage = new Storage(filePath.toString());

        Task task1 = new Todo("read book");
        Task task2 = new Todo("buy groceries");
        storage.save(List.of(task1, task2));

        List<String> loadedLines = storage.loadLines();
        assertEquals(2, loadedLines.size());
        assertEquals("[T][ ] read book", loadedLines.get(0));
        assertEquals("[T][ ] buy groceries", loadedLines.get(1));
    }
}
