package shrekanddonkey.command;

import java.util.List;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.Task;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that searches for tasks containing a keyword in their description.
 */
public class FindCommand extends Command {
    private final String arguments;

    /**
     * Creates a find command with the search keyword argument.
     *
     * @param arguments text following the {@code find} command
     */
    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Finds and displays tasks whose description contains the search keyword.
     *
     * @param tasks application's task list
     * @param ui application's user interface
     * @param storage application's file storage, which is not used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.isEmpty()) {
            ui.showMessage(" Please enter a keyword after 'find'.");
            ui.showDivider();
            return;
        }

        List<Task> matchingTasks = tasks.findTasks(arguments);
        ui.showMatchingTasks(matchingTasks);
        ui.showDivider();
    }
}
