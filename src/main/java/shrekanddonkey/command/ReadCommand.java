package shrekanddonkey.command;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that displays a file's contents.
 */
public class ReadCommand extends Command {
    private static final Path DATA_DIRECTORY = Path.of("data").toAbsolutePath().normalize();

    private final String arguments;

    /**
     * Creates a read command for a file in the data directory.
     *
     * @param arguments file name following the {@code read} command.
     */
    public ReadCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Displays the contents of the requested file, or an error if it cannot be read.
     *
     * @param tasks application's task list, which is not used.
     * @param ui application's user interface.
     * @param storage application's file storage, which is not used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList collaborator cannot be null";
        assert ui != null : "Ui collaborator cannot be null";
        assert storage != null : "Storage collaborator cannot be null";
        assert arguments != null : "Command arguments cannot be null";
        if (arguments.isEmpty()) {
            ui.showError("Please specify a file name after 'read'.");
            return;
        }

        Path requestedPath;
        try {
            requestedPath = resolveDataFilePath(arguments);
        } catch (InvalidPathException e) {
            ui.showError("Please choose a valid file inside the data directory.");
            return;
        }

        if (requestedPath == null) {
            ui.showError("Please choose a file inside the data directory.");
            return;
        }

        try {
            for (String line : new Storage(requestedPath.toString()).loadLines()) {
                ui.showMessage(line);
            }
            ui.showDivider();
        } catch (IOException e) {
            ui.showError("File not found: " + arguments);
        }
    }

    /**
     * Returns a normalized path within the data directory for a relative file name.
     *
     * @param fileName file name supplied to the {@code read} command.
     * @return normalized data-file path, or {@code null} if the path escapes the data directory.
     * @throws InvalidPathException if {@code fileName} is not a valid file path.
     */
    private Path resolveDataFilePath(String fileName) {
        Path fileNamePath = Path.of(fileName);
        if (fileNamePath.isAbsolute()) {
            return null;
        }

        Path dataFilePath = DATA_DIRECTORY.resolve(fileNamePath).normalize();
        return dataFilePath.startsWith(DATA_DIRECTORY) ? dataFilePath : null;
    }
}

