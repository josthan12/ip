package shrekanddonkey.command;
import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

import java.io.IOException;

/**
 * Represents the command that saves the current task list.
 */
public class WriteCommand extends Command {
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

