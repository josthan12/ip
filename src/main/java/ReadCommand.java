import java.io.IOException;

/**
 * Represents the command that displays a file's contents.
 */
public class ReadCommand extends Command {
    private final String arguments;

    public ReadCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.isEmpty()) {
            ui.showMessage("Please specify a file name after 'read'.");
            return;
        }

        String relativePath = "./data/" + arguments;
        try {
            for (String line : new Storage(relativePath).loadLines()) {
                ui.showMessage(line);
            }
            ui.showDivider();
        } catch (IOException e) {
            ui.showMessage("File not found: " + relativePath);
        }
    }
}
