/**
 * Represents the command that adds a simple to-do task.
 */
public class TodoCommand extends Command {
    private final String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (arguments.isEmpty()) {
                throw new ShrekAndDonkeyException("todo");
            }
            tasks.add(new Todo(arguments));
            ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());
        } catch (ShrekAndDonkeyException e) {
            ui.showMessage("OOPS!!UWU description of a " + e.getMessage() + " cannot be empty UwU");
        }
        ui.showDivider();
    }
}
