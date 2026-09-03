package shrekanddonkey;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Serves as the JavaFX GUI entry point for the ShrekAndDonkey chatbot.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Grrr GET OUT OF MY SWAMP!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.setTitle("ShrekAndDonkey");
        stage.show();
    }
}
