package org.example.escriturarapida.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.escriturarapida.Musica;
import org.example.escriturarapida.interfaces.IGameController;

import java.io.IOException;

/**
 * Controller for application welcome screen and main menu interface.
 * Manages menu music initialization and transitions to the main gameplay screen.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public class WelcomeController implements IGameController {

    /**
     * Initializes welcome view components, registers startup event,
     * and plays main menu soundtrack.
     */
    @FXML
    public void initialize() {
        logEvent("Loading welcome screen and playing menu background music");
        Musica.reproducirMenu();
    }

    /**
     * Begins match workflow starting from welcome state.
     */
    @Override
    public void startGame() {
        logEvent("Starting gameplay flow from welcome screen");
    }

    /**
     * Stops main menu background music playback.
     */
    @Override
    public void stopGame() {
        logEvent("Stopping main menu music playback");
        Musica.detener();
    }

    /**
     * Handles scene transition towards the primary game interface.
     * Loads target FXML layout and forwards current window stage reference.
     *
     * @param event Action event triggered by UI button click.
     * @throws IOException If the game FXML file cannot be found or loaded.
     */
    @FXML
    public void entrarPantallaJuego(ActionEvent event) throws IOException {
        logEvent("UI Event: Clicked button to enter gameplay screen");
        stopGame();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/escriturarapida/basicGame.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        GameController gameController = loader.getController();
        gameController.stage = stage;

        stage.setScene(new Scene(root));
    }

    /**
     * Closes the active window stage, terminating application execution.
     *
     * @param event Action event triggered by user clicking exit button.
     */
    @FXML
    private void salirJuego(ActionEvent event) {
        logEvent("UI Event: Clicked Exit Game button");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Logs welcome controller events to system console for debugging.
     *
     * @param mensaje Description of event to be logged.
     */
    @Override
    public void logEvent(String mensaje) {
        System.out.println("[LOG]: " + mensaje);
    }
}