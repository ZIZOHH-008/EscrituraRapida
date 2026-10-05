package org.example.escriturarapida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application entry point for the Fast Writing JavaFX program.
 * Handles primary stage setup and initial scene loading.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public class EscrituraRapidaApplication extends Application {

    /**
     * Main JavaFX lifecycle entry method. Loads welcome FXML layout and configures window stage.
     *
     * @param stage Primary window stage provided by JavaFX runtime environment.
     * @throws IOException If target view FXML resource fails to load.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EscrituraRapidaApplication.class.getResource("Welcome.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Escritura rápida :>");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}