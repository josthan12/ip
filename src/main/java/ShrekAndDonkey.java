/**
 * Runs the ShrekAndDonkey chatbot and manages the user's task list.
 */
public class ShrekAndDonkey {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        // Each Task object keeps its description and done status together.

        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/happyFile.txt");

        String input = ui.readCommand();

        //Scans till bye is input
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

            //If list is input 
            if (commandType == Parser.CommandType.LIST) {
                new ListCommand().execute(taskList, ui, storage);
            } else if (commandType == Parser.CommandType.MARK) {
                new MarkCommand(Parser.getArguments(input, "mark"))
                        .execute(taskList, ui, storage);

                //Updated by Chatgpt
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
            }
            // Writing to one specific file only
            else if (commandType == Parser.CommandType.WRITE) {
                new WriteCommand().execute(taskList, ui, storage);
            }
            else if (commandType == Parser.CommandType.READ) {
                new ReadCommand(Parser.getArguments(input, "read"))
                        .execute(taskList, ui, storage);
            }



            else {

                ui.showMessage("NO VALID INPUT GIVEN,PWEASE TRY AGAIN");
                ui.showDivider();
            }
            input = ui.readCommand();
        }

    }
}
