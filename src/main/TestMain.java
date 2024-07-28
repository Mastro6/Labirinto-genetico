package main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import labirinto.Finestra;
import javafx.application.Application;

/*
    IMPORTANTE
        Gli elementi di JavaFX potrebbe lanciare un errore in esecuzione se non sono stati seguiti i seguenti passaggi:
            Run -> Edit Configurations -> (New configuration) -> Modify options -> Add VM options -> --module-path %path% --add-modules javafx.controls,javafx.fxml
            NOTA: sostituire %path% con il path locale della cartella lib, nella SDK di JavaFX
 */

public class TestMain extends Application {
    public static void main(String[] args) {
        // Finestra finestraTest = new Finestra();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setup the main container
        BorderPane root = new BorderPane();
        // Place a label in the center of the window
        root.setCenter(new Label("FunXiona"));
        // Set up scene and stage
        primaryStage.setScene(new Scene(root, 285, 195));
        primaryStage.setTitle("JavaFX Hello");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
