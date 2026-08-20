import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the ShrekAndDonkey chatbot and manages the user's task list.
 */
public class ShrekAndDonkey {
    /**
     * Prints the confirmation shown after adding a task.
     *
     * @param task task that was added
     * @param taskCount number of tasks currently stored
     */
    private static void printTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    public static void main(String[] args) {
        String divider = "____________________________________________________________";
        //Created using ChatGPT
        String banner = " ____  _              _        _              _ ____              _\n"
                + "/ ___|| |__  _ __ ___| | __   / \\   _ __   __| |  _ \\  ___  _ __ | | _____ _   _\n"
                + "\\___ \\| '_ \\| '__/ _ \\ |/ /  / _ \\ | '_ \\ / _` | | | |/ _ \\| '_ \\| |/ / _ \\ | | |\n"
                + " ___) | | | | | |  __/   <  / ___ \\| | | | (_| | |_| | (_) | | | |   <  __/ |_| |\n"
                + "|____/|_| |_|_|  \\___|_|\\_\\/_/   \\_\\_| |_|\\__,_|____/ \\___/|_| |_|_|\\_\\___|\\__, |\n"
                + "                                                                            |___/\n";

        System.out.println(divider);
        System.out.print(banner);
        System.out.println("Grrr GET OUT OF MY SWAMP! Ohh, I didn't see you " +
                "there stranger! I'm ShrekAndDonkey.");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        // Each Task object keeps its description and done status together.

        ArrayList<Task> taskList = new ArrayList<>();

        //Scanning for input by user
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //Scans till bye is input
        while (!input.equals("bye")) { 
            System.out.println(divider);
            
            //If list is input 
            if (input.equals("list")) {
                System.out.println(" Here are the tasks in your list:");

                //Updated by chatgpt
                //Iterate through the task array to display the list
                for (int i = 0; i < taskList.size(); i++) {
                    // Add 1 because task numbers shown to users start from 1, not 0.
                    System.out.println(" " + (i + 1) + "." + taskList.get(i));
                }
                System.out.println(divider);
            } else if (input.equals("mark") || input.startsWith("mark ")) {
                String taskNumberText = input.substring("mark".length()).trim();

                //Exception handling
                try {
                    int taskIndex = Integer.parseInt(taskNumberText) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        System.out.println(" Please enter a task number from 1 to " + taskList.size() + ".");
                    } else {
                        //using task as state
                        taskList.get(taskIndex).markAsDone();
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + taskList.get(taskIndex));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid task number after 'mark'.");
                }
                System.out.println(divider);

                //Updated by Chatgpt
            } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                String taskNumberText = input.substring("unmark".length()).trim();

                //Exception handling for unmarked
                try {
                    int taskIndex = Integer.parseInt(taskNumberText) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        System.out.println(" Please enter a task number from 1 to " + taskList.size()
                                + ".");
                    } else {
                        //Using task state itself
                        taskList.get(taskIndex).markAsNotDone();
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + taskList.get(taskIndex));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid task number after 'unmark'.");
                }
                System.out.println(divider);
            } else if (input.equals("todo") || input.startsWith("todo ")) {
                String description = input.substring("todo".length()).trim();
                try{
                    if (description.isEmpty()) {
                        throw new ShrekAndDonkeyException("todo");

                    }
                    else{
                        taskList.add(new Todo(description));
                        printTaskAdded(taskList.get(taskList.size()-1), taskList.size());
                        System.out.println(divider);
                    }

                }
                catch (ShrekAndDonkeyException e){
                    System.out.println("OOPS!!UWU description of a " + e.getMessage() + " cannot be empty UwU");
                    System.out.println(divider);
                }
            } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                String taskDetails = input.substring("deadline".length()).trim();
                int byMarkerIndex = taskDetails.indexOf("/by");

                //Exception only checks for missing description and date for deadline(copied format from todo)
                try {
                    if (byMarkerIndex < 0) {
                        System.out.println(" Please specify a deadline using /by.");
                    } else {
                        String description = taskDetails.substring(0, byMarkerIndex).trim();
                        if (description.isEmpty()) {
                            throw new ShrekAndDonkeyException("deadline");
                        }
                        String deadline = taskDetails.substring(byMarkerIndex + "/by".length()).trim();
                        taskList.add(new Deadline(description, deadline));
                        printTaskAdded(taskList.get(taskList.size()-1), taskList.size());
                    }
                } catch (ShrekAndDonkeyException e) {
                    System.out.println("OOPS!!UWU description of a "
                            + e.getMessage() + " cannot be empty UwU");
                }
                System.out.println(divider);
            } else if (input.equals("event") || input.startsWith("event ")) {
                String taskDetails = input.substring("event".length()).trim();
                int fromMarkerIndex = taskDetails.indexOf("/from");
                int toMarkerIndex = taskDetails.indexOf("/to", fromMarkerIndex + "/from".length());

                //Exception only checks for missing deadline and description in Events(copied format from todo)
                try {
                    if (fromMarkerIndex < 0 || toMarkerIndex < 0) {
                        System.out.println(" Please specify an event using /from and /to.");
                    } else {
                        String description = taskDetails.substring(0, fromMarkerIndex).trim();
                        if (description.isEmpty()) {
                            throw new ShrekAndDonkeyException("event");
                        }
                        String start = taskDetails.substring(
                                fromMarkerIndex + "/from".length(), toMarkerIndex).trim();
                        String end = taskDetails.substring(toMarkerIndex + "/to".length()).trim();
                        taskList.add(new Event(description, start, end));
                        printTaskAdded(taskList.get(taskList.size()-1), taskList.size());
                    }
                } catch (ShrekAndDonkeyException e) {
                    System.out.println("OOPS!!UWU description of a "
                            + e.getMessage() + " cannot be empty UwU");
                }
                System.out.println(divider);
            } else if (input.equals("delete") || input.startsWith("delete ")) {
                String deleteDetails = input.substring("delete".length()).trim();

                try {
                    int deleteIndex = Integer.parseInt(deleteDetails) - 1;
                    if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
                        System.out.println(" Please enter a task number from 1 to " + taskList.size()
                                + ".");
                    } else {

                        Task removedTask = taskList.remove(deleteIndex);
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + removedTask);
                        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid task number after 'delete'.");
                }
                System.out.println(divider);
            }

            else {

                System.out.println("NO VALID INPUT GIVEN,PWEASE TRY AGAIN");
                System.out.println(divider);
            }
            input = scanner.nextLine();
        }

        System.out.println(divider);
        System.out.println("FEE FIE FOE FUMP. GET OUT OF MY SWAMP!");
        System.out.println(divider);
    }
}
