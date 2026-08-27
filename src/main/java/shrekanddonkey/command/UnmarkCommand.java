package shrekanddonkey.command;
import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents the command that marks a task as not completed.
 */
public class UnmarkCommand extends Command {
    private final String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                ui.showMessage(" Please enter a task number from 1 to " + tasks.size() + ".");
            } else {
                tasks.get(taskIndex).markAsNotDone();
                ui.showMessage(" OK, I've marked this task as not done yet:");
                ui.showMessage("   " + tasks.get(taskIndex));
            }
        } catch (NumberFormatException e) {
            ui.showMessage(" Please enter a valid task number after 'unmark'.");
        }
        ui.showDivider();
    }
}

