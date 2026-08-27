package shrekanddonkey.command;
import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Represents one executable chatbot command.
 */
public abstract class Command {
    /**
     * Executes this command using the application's collaborators.
     *
     * @param tasks application's task list
     * @param ui application's user interface
     * @param storage application's file storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether this command ends the chatbot session.
     *
     * @return {@code true} only for the exit command
     */
    public boolean isExit() {
        return false;
    }
}

