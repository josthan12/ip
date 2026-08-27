package shrekanddonkey;

import shrekanddonkey.command.Command;
import shrekanddonkey.command.DeadlineCommand;
import shrekanddonkey.command.DeleteCommand;
import shrekanddonkey.command.EventCommand;
import shrekanddonkey.command.ExitCommand;
import shrekanddonkey.command.FindCommand;
import shrekanddonkey.command.ListCommand;
import shrekanddonkey.command.MarkCommand;
import shrekanddonkey.command.ReadCommand;
import shrekanddonkey.command.TodoCommand;
import shrekanddonkey.command.UnmarkCommand;
import shrekanddonkey.command.WriteCommand;
import shrekanddonkey.parser.Parser;
import shrekanddonkey.storage.Storage;
import shrekanddonkey.task.TaskList;
import shrekanddonkey.ui.Ui;

/**
 * Runs the ShrekAndDonkey chatbot and manages the user's task list.
 */
public class ShrekAndDonkey {
    /**
     * Creates the chatbot application.
     */
    public ShrekAndDonkey() {
    }

    /**
     * Starts the chatbot and processes commands until the user exits.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/happyFile.txt");

        String input = ui.readCommand();

        while (true) {
            Parser.CommandType commandType = Parser.parseCommandType(input);
            if (commandType == Parser.CommandType.EXIT) {
                Command command = new ExitCommand();
                command.execute(taskList, ui, storage);
                if (command.isExit()) {
                    break;
                }
            }
            ui.showDivider();

            if (commandType == Parser.CommandType.LIST) {
                new ListCommand().execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.MARK) {
                new MarkCommand(Parser.getArguments(input, "mark"))
                        .execute(taskList, ui, storage);

            } else if (commandType == Parser.CommandType.UNMARK) {
                new UnmarkCommand(Parser.getArguments(input, "unmark"))
                        .execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.TODO) {
                new TodoCommand(Parser.getArguments(input, "todo"))
                        .execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.DEADLINE) {
                new DeadlineCommand(Parser.getArguments(input, "deadline"))
                        .execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.EVENT) {
                new EventCommand(Parser.getArguments(input, "event"))
                        .execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.DELETE) {
                new DeleteCommand(Parser.getArguments(input, "delete"))
                        .execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.FIND) {
                new FindCommand(Parser.getArguments(input, "find"))
                        .execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.WRITE) {
                new WriteCommand().execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.READ) {
                new ReadCommand(Parser.getArguments(input, "read"))
                        .execute(taskList, ui, storage);
            } else {

                ui.showMessage("NO VALID INPUT GIVEN,PWEASE TRY AGAIN");
                ui.showDivider();
            }
            input = ui.readCommand();
        }

    }
}
