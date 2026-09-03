package shrekanddonkey;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Serves as the JavaFX GUI entry point for the ShrekAndDonkey chatbot using FXML.
 */
public class Main extends Application {

    private final ShrekAndDonkey shrekAndDonkey = new ShrekAndDonkey();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ShrekAndDonkey");
            fxmlLoader.<MainWindow>getController().setShrekAndDonkey(shrekAndDonkey);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
