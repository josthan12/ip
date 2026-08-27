package shrekanddonkey.command;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.Task;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that removes a task by its one-based number.
 */
public class DeleteCommand extends Command {
    private final String arguments;

    /**
     * Creates a delete command with its raw task-number argument.
     *
     * @param arguments text following the {@code delete} command
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Removes the selected task, or displays an error when the task number is invalid.
     *
     * @param tasks application's task list
     * @param ui application's user interface
     * @param storage application's file storage, which is not used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showMessage(" Please enter a task number from 1 to " + tasks.size() + ".");
            } else {
                Task removedTask = tasks.remove(taskIndex);
                ui.showMessage(" Noted. I've removed this task:");
                ui.showMessage("   " + removedTask);
                ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            ui.showMessage(" Please enter a valid task number after 'delete'.");
        }
        ui.showDivider();
    }
}

