import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


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
        while (!input.equals("bye")) {
            Parser.CommandType commandType = Parser.parseCommandType(input);
            ui.showDivider();

            //If list is input 
            if (commandType == Parser.CommandType.LIST) {
                ui.showTaskList(taskList.getTasks());
                ui.showDivider();
            } else if (commandType == Parser.CommandType.MARK) {
                String taskNumberText = Parser.getArguments(input, "mark");

                //Exception handling
                try {
                    int taskIndex = Integer.parseInt(taskNumberText) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        ui.showMessage(" Please enter a task number from 1 to " + taskList.size() + ".");
                    } else {
                        //using task as state
                        taskList.get(taskIndex).markAsDone();
                        ui.showMessage(" Nice! I've marked this task as done:");
                        ui.showMessage("   " + taskList.get(taskIndex));
                    }
                } catch (NumberFormatException e) {
                    ui.showMessage(" Please enter a valid task number after 'mark'.");
                }
                ui.showDivider();

                //Updated by Chatgpt
            } else if (commandType == Parser.CommandType.UNMARK) {
                String taskNumberText = Parser.getArguments(input, "unmark");

                //Exception handling for unmarked
                try {
                    int taskIndex = Integer.parseInt(taskNumberText) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        ui.showMessage(" Please enter a task number from 1 to " + taskList.size()
                                + ".");
                    } else {
                        //Using task state itself
                        taskList.get(taskIndex).markAsNotDone();
                        ui.showMessage(" OK, I've marked this task as not done yet:");
                        ui.showMessage("   " + taskList.get(taskIndex));
                    }
                } catch (NumberFormatException e) {
                    ui.showMessage(" Please enter a valid task number after 'unmark'.");
                }
                ui.showDivider();
            } else if (commandType == Parser.CommandType.TODO) {
                String description = Parser.getArguments(input, "todo");
                try {
                    if (description.isEmpty()) {
                        throw new ShrekAndDonkeyException("todo");

                    } else {
                        taskList.add(new Todo(description));
                        ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
                        ui.showDivider();
                    }

                } catch (ShrekAndDonkeyException e) {
                    ui.showMessage("OOPS!!UWU description of a " + e.getMessage() + " cannot be empty UwU");
                    ui.showDivider();
                }
            } else if (commandType == Parser.CommandType.DEADLINE) {
                String taskDetails = Parser.getArguments(input, "deadline");
                int byMarkerIndex = taskDetails.indexOf("/by");

                //Exception only checks for missing description and date for deadline(copied format from todo)
                try {
                    if (byMarkerIndex < 0) {
                        ui.showMessage(" Please specify a deadline using /by.");
                    } else {
                        String description = taskDetails.substring(0, byMarkerIndex).trim();
                        if (description.isEmpty()) {
                            throw new ShrekAndDonkeyException("deadline");
                        }
                        String deadlineText = taskDetails.substring(byMarkerIndex + "/by".length()).trim();
                        LocalDateTime deadline = Parser.parseDateTime(deadlineText);
                        taskList.add(new Deadline(description, deadline));
                        ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
                    }
                } catch (ShrekAndDonkeyException e) {
                    ui.showMessage("OOPS!!UWU description of a "
                            + e.getMessage() + " cannot be empty UwU");
                } catch (DateTimeParseException e) {
                    ui.showMessage(" Please enter a valid deadline date (yyyy-MM-dd, yyyy-MM-dd HH:mm, "
                            + "or d/M/yyyy HHmm).");
                }
                ui.showDivider();
            } else if (commandType == Parser.CommandType.EVENT) {
                String taskDetails = Parser.getArguments(input, "event");
                int fromMarkerIndex = taskDetails.indexOf("/from");
                int toMarkerIndex = taskDetails.indexOf("/to", fromMarkerIndex + "/from".length());

                //Exception only checks for missing deadline and description in Events(copied format from todo)
                try {
                    if (fromMarkerIndex < 0 || toMarkerIndex < 0) {
                        ui.showMessage(" Please specify an event using /from and /to.");
                    } else {
                        String description = taskDetails.substring(0, fromMarkerIndex).trim();
                        if (description.isEmpty()) {
                            throw new ShrekAndDonkeyException("event");
                        }
                        String startText = taskDetails.substring(
                                fromMarkerIndex + "/from".length(), toMarkerIndex).trim();
                        String endText = taskDetails.substring(toMarkerIndex + "/to".length()).trim();
                        LocalDateTime start = Parser.parseDateTime(startText);
                        LocalDateTime end = Parser.parseDateTime(endText);
                        if (end.isBefore(start)) {
                            ui.showMessage(" Please ensure the event end is not before its start.");
                        } else {
                            taskList.add(new Event(description, start, end));
                            ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
                        }
                    }
                } catch (ShrekAndDonkeyException e) {
                    ui.showMessage("OOPS!!UWU description of a "
                            + e.getMessage() + " cannot be empty UwU");
                } catch (DateTimeParseException e) {
                    ui.showMessage(" Please enter valid event dates (yyyy-MM-dd, yyyy-MM-dd HH:mm, "
                            + "or d/M/yyyy HHmm).");
                }
                ui.showDivider();
            } else if (commandType == Parser.CommandType.DELETE) {
                String deleteDetails = Parser.getArguments(input, "delete");

                try {
                    int deleteIndex = Integer.parseInt(deleteDetails) - 1;
                    if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
                        ui.showMessage(" Please enter a task number from 1 to " + taskList.size()
                                + ".");
                    } else {

                        Task removedTask = taskList.remove(deleteIndex);
                        ui.showMessage(" Noted. I've removed this task:");
                        ui.showMessage("   " + removedTask);
                        ui.showMessage(" Now you have " + taskList.size() + " tasks in the list.");
                    }
                } catch (NumberFormatException e) {
                    ui.showMessage(" Please enter a valid task number after 'delete'.");
                }
                ui.showDivider();
            }
            // Writing to one specific file only
            else if (commandType == Parser.CommandType.WRITE) {
                try {
                    storage.save(taskList.getTasks());
                    ui.showMessage("Successfully written");
                } catch (IOException e) {
                    ui.showMessage("Unable to write to ./data/happyFile.txt");
                }
                ui.showDivider();
            }
            else if (commandType == Parser.CommandType.READ) {
                String requestedFileName = Parser.getArguments(input, "read");

                if (requestedFileName.isEmpty()) {
                    ui.showMessage("Please specify a file name after 'read'.");
                } else {
                    Storage requestedStorage = new Storage("./data/" + requestedFileName);

                    try {
                        for (String line : requestedStorage.loadLines()) {
                            ui.showMessage(line);
                        }
                        ui.showDivider();
                    } catch (IOException e) {
                        ui.showMessage("File not found: ./data/" + requestedFileName);
                    }
                }
            }



            else {

                ui.showMessage("NO VALID INPUT GIVEN,PWEASE TRY AGAIN");
                ui.showDivider();
            }
            input = ui.readCommand();
        }

        ui.showGoodbye();
    }
}
