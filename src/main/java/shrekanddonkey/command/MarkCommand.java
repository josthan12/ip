package shrekanddonkey.command;

import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that marks a task as completed.
 */
public class MarkCommand extends Command {
    private final String arguments;

    /**
     * Creates a mark command with its raw task-number argument.
     *
     * @param arguments text following the {@code mark} command
     */
    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Marks the selected task as done, or displays an error for invalid input.
     *
     * @param tasks application's task list
     * @param ui application's user interface
     * @param storage application's file storage, which is not used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList collaborator cannot be null";
        assert ui != null : "Ui collaborator cannot be null";
        assert storage != null : "Storage collaborator cannot be null";
        assert arguments != null : "Command arguments cannot be null";
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showError(" Please enter a task number from 1 to " + tasks.size() + ".");
            } else {
                tasks.get(taskIndex).markAsDone();
                ui.showMessage(" Nice! I've marked this task as done:");
                ui.showMessage("   " + tasks.get(taskIndex));
            }
        } catch (NumberFormatException e) {
            ui.showError(" Please enter a valid task number after 'mark'.");
        }
        ui.showDivider();
    }
}

