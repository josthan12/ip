package shrekanddonkey.command;

import java.io.IOException;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that saves the current task list.
 */
public class WriteCommand extends Command {
    /**
     * Creates a write command.
     */
    public WriteCommand() {
    }

    /**
     * Saves the current task list to the configured storage file.
     *
     * @param tasks application's task list
     * @param ui application's user interface
     * @param storage application's file storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.getTasks());
            ui.showMessage("Successfully written");
        } catch (IOException e) {
            ui.showMessage("Unable to write to ./data/happyFile.txt");
        }
        ui.showDivider();
    }
}

