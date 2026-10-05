package org.example.escriturarapida.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.escriturarapida.interfaces.IGameController;

import java.io.IOException;

/**
 * Controller for Game Over final screen.
 * Displays reached level, feedback messages, and handles match restarting.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public class FinalScreenController implements IGameController {

    @FXML private Button reiniciarButton;
    @FXML private Label numFinalLabel;
    @FXML private Label mensajeFinalLabel;

    /** Reference to main game controller for retrieving statistics and state. */
    public GameController gameController;

    /**
     * Initializes graphical components and logs Game Over screen loading event.
     */
    @FXML
    public void initialize() {
        logEvent("Game Over screen loaded");
    }

    /**
     * Logs match restart event from final screen state.
     */
    @Override
    public void startGame() {
        logEvent("Restarting match from final screen");
    }

    /**
     * Logs view exit or change event from final screen.
     */
    @Override
    public void stopGame() {
        logEvent("Exiting final Game Over screen");
    }

    /**
     * Retrieves reached level from {@link GameController} and updates interface display.
     */
    public void nivelAlcanzado() {
        if (gameController != null && numFinalLabel != null) {
            numFinalLabel.setText(String.valueOf(gameController.nivel));
            logEvent("Displayed reached level: " + gameController.nivel);
        }
    }

    /**
     * Reloads gameplay scene to restart match from level 1.
     *
     * @param event Action event triggered by clicking restart button.
     * @throws IOException If game FXML layout cannot be loaded.
     */
    @FXML
    public void reiniciarNiveles(ActionEvent event) throws IOException {
        logEvent("UI Event: Clicked 'Restart / Play Again' button");
        stopGame();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/escriturarapida/basicGame.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        GameController newGameController = loader.getController();
        newGameController.stage = stage;

        stage.setScene(new Scene(root));
    }

    /**
     * Evaluates achieved level and renders personalized player feedback message.
     */
    public void mensajeFinal() {
        if (gameController == null || mensajeFinalLabel == null) return;

        int nivel = gameController.nivel;

        if (nivel < 6) {
            mensajeFinalLabel.setText("¡Inténtalo de nuevo, puedes mejorar!");
        } else if (nivel >= 6 && nivel < 20) {
            mensajeFinalLabel.setText("Podrías mejorar ese nivel...");
        } else if (nivel >= 20 && nivel < 31) {
            mensajeFinalLabel.setText("Lo podrías mejorar, pero está bien.");
        } else {
            mensajeFinalLabel.setText("¡EL TRUE GOAT!");
        }

        logEvent("Final feedback message assigned for level " + nivel);
    }

    /**
     * Logs final screen events to console for debugging purposes.
     *
     * @param message Description of the event to log.
     */
    @Override
    public void logEvent(String message) {
        System.out.println("[LOG]: " + message);
    }
}