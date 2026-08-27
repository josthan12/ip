package shrekanddonkey.command;
import shrekanddonkey.exception.ShrekAndDonkeyException;
import shrekanddonkey.parser.Parser;
import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.Deadline;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents the command that adds a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int byMarkerIndex = arguments.indexOf("/by");
        try {
            if (byMarkerIndex < 0) {
                ui.showMessage(" Please specify a deadline using /by.");
            } else {
                String description = arguments.substring(0, byMarkerIndex).trim();
                if (description.isEmpty()) {
                    throw new ShrekAndDonkeyException("deadline");
                }
                String deadlineText = arguments.substring(byMarkerIndex + "/by".length()).trim();
                LocalDateTime deadline = Parser.parseDateTime(deadlineText);
                tasks.add(new Deadline(description, deadline));
                ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
            }
        } catch (ShrekAndDonkeyException e) {
            ui.showMessage("OOPS!!UWU description of a " + e.getMessage() + " cannot be empty UwU");
        } catch (DateTimeParseException e) {
            ui.showMessage(" Please enter a valid deadline date (yyyy-MM-dd, yyyy-MM-dd HH:mm, "
                    + "or d/M/yyyy HHmm).");
        }
        ui.showDivider();
    }
}

