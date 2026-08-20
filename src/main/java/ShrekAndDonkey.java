import java.util.Scanner;

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
        //Now stores Task in the array instead so each index has a state
        Task[] tasks = new Task[100];

        //Added Pointer for current Count
        int pointer = 0;


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
                for (int i = 0; i < pointer; i++) {
                    // Add 1 because task numbers shown to users start from 1, not 0.
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(divider);
            } else if (input.equals("mark") || input.startsWith("mark ")) {
                String taskNumberText = input.substring("mark".length()).trim();

                //Exception handling
                try {
                    int taskIndex = Integer.parseInt(taskNumberText) - 1;
                    if (taskIndex < 0 || taskIndex >= pointer) {
                        System.out.println(" Please enter a task number from 1 to " + pointer + ".");
                    } else {
                        //using task as state
                        tasks[taskIndex].markAsDone();
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskIndex]);
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
                    if (taskIndex < 0 || taskIndex >= pointer) {
                        System.out.println(" Please enter a task number from 1 to " + pointer + ".");
                    } else {
                        //Using task state itself
                        tasks[taskIndex].markAsNotDone();
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskIndex]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid task number after 'unmark'.");
                }
                System.out.println(divider);
            } else if (input.equals("todo") || input.startsWith("todo ")) {
                String description = input.substring("todo".length()).trim();
                tasks[pointer] = new Todo(description);
                pointer++;
                printTaskAdded(tasks[pointer - 1], pointer);
                System.out.println(divider);
            } else if (input.equals("deadline") || input.startsWith("deadline ")) {
                String taskDetails = input.substring("deadline".length()).trim();
                int byMarkerIndex = taskDetails.indexOf("/by");

                if (byMarkerIndex < 0) {
                    System.out.println(" Please specify a deadline using /by.");
                } else {
                    String description = taskDetails.substring(0, byMarkerIndex).trim();
                    String deadline = taskDetails.substring(byMarkerIndex + "/by".length()).trim();
                    tasks[pointer] = new Deadline(description, deadline);
                    pointer++;
                    printTaskAdded(tasks[pointer - 1], pointer);
                }
                System.out.println(divider);
            } else if (input.equals("event") || input.startsWith("event ")) {
                String taskDetails = input.substring("event".length()).trim();
                int fromMarkerIndex = taskDetails.indexOf("/from");
                int toMarkerIndex = taskDetails.indexOf("/to", fromMarkerIndex + "/from".length());

                if (fromMarkerIndex < 0 || toMarkerIndex < 0) {
                    System.out.println(" Please specify an event using /from and /to.");
                } else {
                    String description = taskDetails.substring(0, fromMarkerIndex).trim();
                    String start = taskDetails.substring(
                            fromMarkerIndex + "/from".length(), toMarkerIndex).trim();
                    String end = taskDetails.substring(toMarkerIndex + "/to".length()).trim();
                    tasks[pointer] = new Event(description, start, end);
                    pointer++;
                    printTaskAdded(tasks[pointer - 1], pointer);
                }
                System.out.println(divider);
            } else {
                // Commands without a type are treated as ToDos for backward compatibility.
                tasks[pointer] = new Todo(input);
                pointer++;
                printTaskAdded(tasks[pointer - 1], pointer);
                System.out.println(divider);
            }
            input = scanner.nextLine();
        }

        System.out.println(divider);
        System.out.println("FEE FIE FOE FUMP. GET OUT OF MY SWAMP!");
        System.out.println(divider);
    }
}
