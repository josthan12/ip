package shrekanddonkey.command;
import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that displays all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
        ui.showDivider();
    }
}

