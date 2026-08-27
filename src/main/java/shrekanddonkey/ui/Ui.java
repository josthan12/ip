package shrekanddonkey.ui;
import shrekanddonkey.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles console input and output for the chatbot.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BANNER = " ____  _              _        _              _ ____              _\n"
            + "/ ___|| |__  _ __ ___| | __   / \\   _ __   __| |  _ \\  ___  _ __ | | _____ _   _\n"
            + "\\___ \\| '_ \\| '__/ _ \\ |/ /  / _ \\ | '_ \\ / _` | | | |/ _ \\| '_ \\| |/ / _ \\ | | |\n"
            + " ___) | | | | | |  __/   <  / ___ \\| | | | (_| | |_| | (_) | | | |   <  __/ |_| |\n"
            + "|____/|_| |_|_|  \\___|_|\\_\\/_/   \\_\\_| |_|\\__,_|____/ \\___/|_| |_|_|\\_\\___|\\__, |\n"
            + "                                                                            |___/\n";

    private final Scanner scanner;

    /**
     * Creates a UI that reads commands from standard input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads one command from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the chatbot welcome message.
     */
    public void showWelcome() {
        showDivider();
        System.out.print(BANNER);
        showMessage("Grrr GET OUT OF MY SWAMP! Ohh, I didn't see you there stranger! I'm ShrekAndDonkey.");
        showMessage("What can I do for you?");
        showDivider();
    }

    /**
     * Displays the chatbot goodbye message.
     */
    public void showGoodbye() {
        showDivider();
        showMessage("FEE FIE FOE FUMP. GET OUT OF MY SWAMP!");
        showDivider();
    }

    /**
     * Displays a visual divider between chatbot responses.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays one chatbot message.
     *
     * @param message message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the confirmation after a task is added.
     *
     * @param task task that was added
     * @param taskCount number of tasks in the list
     */
    public void showTaskAdded(Task task, int taskCount) {
        showMessage(" Got it. I've added this task:");
        showMessage("   " + task);
        showMessage(" Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays all tasks with one-based numbering.
     *
     * @param tasks tasks to display
     */
    public void showTaskList(List<Task> tasks) {
        showMessage(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showMessage(" " + (i + 1) + "." + tasks.get(i));
        }
    }
}

