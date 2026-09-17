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
import shrekanddonkey.command.SortCommand;
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
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Creates the chatbot application with default storage location.
     */
    public ShrekAndDonkey() {
        this("./data/happyFile.txt");
    }

    /**
     * Creates the chatbot application with the specified storage file path.
     *
     * @param filePath path to the file used for task persistence
     */
    public ShrekAndDonkey(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        assert this.ui != null : "Ui must be initialized";
        assert this.storage != null : "Storage must be initialized";
        assert this.taskList != null : "TaskList must be initialized";
    }

    /**
     * Returns the chatbot welcome message for display.
     *
     * @return the welcome message string
     */
    public String getWelcomeMessage() {
        return "Grrr GET OUT OF MY SWAMP! Ohh, I didn't see you there stranger! I'm ShrekAndDonkey.\n"
                + "What can I do for you?";
    }

    /**
     * Generates a response for the user's chat message by parsing and executing the command.
     *
     * @param input message entered by the user
     * @return response string from the chatbot
     */
    public String getResponse(String input) {
        assert input != null : "User input cannot be null";
        ui.clearOutput();
        Parser.CommandType commandType = Parser.parseCommandType(input);
        if (commandType == Parser.CommandType.EXIT) {
            new ExitCommand().execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.LIST) {
            new ListCommand().execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.MARK) {
            new MarkCommand(Parser.getArguments(input, "mark")).execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.UNMARK) {
            new UnmarkCommand(Parser.getArguments(input, "unmark")).execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.TODO) {
            new TodoCommand(Parser.getArguments(input, "todo")).execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.DEADLINE) {
            new DeadlineCommand(Parser.getArguments(input, "deadline")).execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.EVENT) {
            new EventCommand(Parser.getArguments(input, "event")).execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.DELETE) {
            new DeleteCommand(Parser.getArguments(input, "delete")).execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.FIND) {
            new FindCommand(Parser.getArguments(input, "find")).execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.SORT) {
            new SortCommand().execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.WRITE) {
            new WriteCommand().execute(taskList, ui, storage);
        } else if (commandType == Parser.CommandType.READ) {
            new ReadCommand(Parser.getArguments(input, "read")).execute(taskList, ui, storage);
        } else {
            ui.showError("What are ye doing in my swamp?! Try: list, todo, deadline,"
                    + " event, mark, unmark, delete, find, sort, bye");
        }
        String response = ui.getRecordedOutput();
        assert response != null : "Chatbot response cannot be null";
        return response;
    }

    /**
     * Returns whether the last processed command produced an error.
     *
     * @return {@code true} if the last response is an error
     */
    public boolean isLastResponseError() {
        return ui.isError();
    }

    /**
     * Returns whether the given input is an exit command.
     *
     * @param input message entered by the user
     * @return {@code true} if the input is a bye/exit command
     */
    public boolean isExit(String input) {
        return Parser.parseCommandType(input) == Parser.CommandType.EXIT;
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
            } else if (commandType == Parser.CommandType.SORT) {
                new SortCommand().execute(taskList, ui, storage);
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
