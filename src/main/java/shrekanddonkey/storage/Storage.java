package shrekanddonkey.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import shrekanddonkey.task.Task;

/**
 * Handles saving and loading the chatbot's task file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates storage backed by the given file path.
     *
     * @param filePath path of the task file
     */
    public Storage(String filePath) {
        assert filePath != null : "Storage file path cannot be null";
        this.filePath = Path.of(filePath);
    }

    /**
     * Saves each task on its own line, creating the parent directory if needed.
     *
     * @param tasks tasks to save
     * @throws IOException if the directory or file cannot be written
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Task list to save cannot be null";
        assert filePath != null : "Storage file path cannot be null";
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> taskLines = tasks.stream()
                .map(Task::toString)
                .toList();
        Files.write(filePath, taskLines, StandardCharsets.UTF_8);
    }

    /**
     * Loads the file as display lines for the current read command.
     *
     * @return lines contained in the task file
     * @throws IOException if the file cannot be read
     */
    public List<String> loadLines() throws IOException {
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }
}

