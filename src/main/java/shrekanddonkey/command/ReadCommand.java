package shrekanddonkey.command;

import java.io.IOException;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that displays a file's contents.
 */
public class ReadCommand extends Command {
    private final String arguments;

    /**
     * Creates a read command for a file in the data directory.
     *
     * @param arguments file name following the {@code read} command
     */
    public ReadCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Displays the contents of the requested file, or an error if it cannot be read.
     *
     * @param tasks application's task list, which is not used
     * @param ui application's user interface
     * @param storage application's file storage, which is not used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.isEmpty()) {
            ui.showMessage("Please specify a file name after 'read'.");
            return;
        }

        String relativePath = "./data/" + arguments;
        try {
            for (String line : new Storage(relativePath).loadLines()) {
                ui.showMessage(line);
            }
            ui.showDivider();
        } catch (IOException e) {
            ui.showMessage("File not found: " + relativePath);
        }
    }
}

