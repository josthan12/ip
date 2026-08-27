import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * Runs the ShrekAndDonkey chatbot and manages the user's task list.
 */
public class ShrekAndDonkey {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter SHORT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm")
                    .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Parses a date or date/time entered by the user.
     *
     * @param text date or date/time text
     * @return parsed date/time, using midnight for date-only input
     * @throws DateTimeParseException if the text is not a supported date format
     */
    private static LocalDateTime parseDateTime(String text) {
        try {
            return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException ignoredIsoDateTime) {
            try {
            return LocalDateTime.parse(text, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException ignoredSpaceDateTime) {
                try {
                    return LocalDate.parse(text, DATE_FORMATTER).atStartOfDay();
                } catch (DateTimeParseException ignoredDateOnly) {
                    return LocalDateTime.parse(text, SHORT_DATE_TIME_FORMATTER);
                }
            }
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        // Each Task object keeps its description and done status together.

        ArrayList<Task> taskList = new ArrayList<>();

        String input = ui.readCommand();

        //Scans till bye is input
        while (!input.equals("bye")) {
            ui.showDivider();

            //If list is input 
            if (input.equals("list")) {
                ui.showTaskList(taskList);
                ui.showDivider();
            } else if (input.equals("mark") || input.startsWith("mark ")) {
                String taskNumberText = input.substring("mark".length()).trim();

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
            } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                String taskNumberText = input.substring("unmark".length()).trim();

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
            } else if (input.equals("todo") || input.startsWith("todo ")) {
                String description = input.substring("todo".length()).trim();
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
            } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                String taskDetails = input.substring("deadline".length()).trim();
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
                        LocalDateTime deadline = parseDateTime(deadlineText);
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
            } else if (input.equals("event") || input.startsWith("event ")) {
                String taskDetails = input.substring("event".length()).trim();
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
                        LocalDateTime start = parseDateTime(startText);
                        LocalDateTime end = parseDateTime(endText);
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
            } else if (input.equals("delete") || input.startsWith("delete ")) {
                String deleteDetails = input.substring("delete".length()).trim();

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
            else if (input.equals("write")) {
                File dataDirectory = new File("./data");

                if (!dataDirectory.exists() && !dataDirectory.mkdirs()) {
                    ui.showMessage("Unable to create the data directory.");
                } else {
                    File taskFile = new File(dataDirectory, "happyFile.txt");

                    try (FileWriter writerObject = new FileWriter(taskFile)) {
                        for (Task task : taskList) {
                            writerObject.write(task.toString());
                            writerObject.write(System.lineSeparator());
                        }

                        ui.showMessage("Successfully written");
                    } catch (IOException e) {
                        ui.showMessage("Unable to write to " + taskFile.getPath());
                    }
                    ui.showDivider();
                }
            }
            else if (input.equals("read") || input.startsWith("read ")) {
                String requestedFileName = input.substring("read".length()).trim();

                if (requestedFileName.isEmpty()) {
                    ui.showMessage("Please specify a file name after 'read'.");
                } else {
                    File taskFile = new File("./data", requestedFileName);

                    try (Scanner myReader = new Scanner(taskFile)) {
                        while (myReader.hasNextLine()) {
                            ui.showMessage(myReader.nextLine());
                        }
                        ui.showDivider();
                    } catch (FileNotFoundException e) {
                        ui.showMessage("File not found: " + taskFile.getPath());
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
