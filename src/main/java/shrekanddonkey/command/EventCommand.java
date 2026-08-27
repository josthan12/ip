package shrekanddonkey.command;
import shrekanddonkey.exception.ShrekAndDonkeyException;
import shrekanddonkey.parser.Parser;
import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.Event;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents the command that adds an event task.
 */
public class EventCommand extends Command {
    private final String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int fromMarkerIndex = arguments.indexOf("/from");
        int toMarkerIndex = arguments.indexOf("/to", fromMarkerIndex + "/from".length());
        try {
            if (fromMarkerIndex < 0 || toMarkerIndex < 0) {
                ui.showMessage(" Please specify an event using /from and /to.");
            } else {
                String description = arguments.substring(0, fromMarkerIndex).trim();
                if (description.isEmpty()) {
                    throw new ShrekAndDonkeyException("event");
                }
                String startText = arguments.substring(
                        fromMarkerIndex + "/from".length(), toMarkerIndex).trim();
                String endText = arguments.substring(toMarkerIndex + "/to".length()).trim();
                LocalDateTime start = Parser.parseDateTime(startText);
                LocalDateTime end = Parser.parseDateTime(endText);
                if (end.isBefore(start)) {
                    ui.showMessage(" Please ensure the event end is not before its start.");
                } else {
                    tasks.add(new Event(description, start, end));
                    ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
                }
            }
        } catch (ShrekAndDonkeyException e) {
            ui.showMessage("OOPS!!UWU description of a " + e.getMessage() + " cannot be empty UwU");
        } catch (DateTimeParseException e) {
            ui.showMessage(" Please enter valid event dates (yyyy-MM-dd, yyyy-MM-dd HH:mm, "
                    + "or d/M/yyyy HHmm).");
        }
        ui.showDivider();
    }
}

