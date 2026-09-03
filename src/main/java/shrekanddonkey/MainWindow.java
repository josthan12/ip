package shrekanddonkey;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the chatbot.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ShrekAndDonkey shrekAndDonkey;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image shrekImage = new Image(this.getClass().getResourceAsStream("/images/DaShrek.png"));

    /**
     * Initializes the controller and binds the scroll pane to the dialog container height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the ShrekAndDonkey chatbot instance and displays the welcome message.
     *
     * @param s chatbot instance
     */
    public void setShrekAndDonkey(ShrekAndDonkey s) {
        shrekAndDonkey = s;
        dialogContainer.getChildren().add(
                DialogBox.getShrekDialog(shrekAndDonkey.getWelcomeMessage(), shrekImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Shrek's reply,
     * then appends them to the dialog container and clears the text input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        String response = shrekAndDonkey.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getShrekDialog(response, shrekImage)
        );
        userInput.clear();
    }
}
