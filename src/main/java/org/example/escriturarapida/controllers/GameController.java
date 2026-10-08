package org.example.escriturarapida.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

import org.example.escriturarapida.Musica;
import org.example.escriturarapida.Palabra;
import org.example.escriturarapida.interfaces.IGameController;

/**
 * Main controller for the Fast Writing game screen.
 * Manages game lifecycle, timers, level progression, and event logging.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public class GameController implements IGameController {

    @FXML private Label palabraGenLabel;
    @FXML private TextField respuestaInField;
    @FXML private Label mensajeLabel;
    @FXML private Label timeLabel;
    @FXML private Label nivelLabel;

    private String palabraActual;
    private PauseTransition pausa;
    private Timeline timeline;
    private int segundosRestantes;

    private Random random = new Random();

    /** Current level achieved by the player during the match. */
    public int nivel = 1;

    Stage stage;

    /**
     * Initializes UI components, starts background music reproduction,
     * and triggers initial game startup logic.
     */
    @FXML public void initialize() {
        logEvent("Initializing GameController and playing background music");
        Musica.reproducir();
        startGame();
    }

    /**
     * Starts the match, generates the initial word, and updates the UI level label.
     */
    @Override
    public void startGame() {
        logEvent("Starting match at Level " + nivel);
        generarPalabra();
        if (nivelLabel != null) {
            nivelLabel.setText("Nivel: " + nivel);
        }
    }

    /**
     * Stops running timers and pauses music upon exiting or pausing the game.
     */
    @Override
    public void stopGame() {
        logEvent("Stopping timers and game session");
        if (timeline != null) {
            timeline.stop();
        }
        Musica.detener();
    }

    /**
     * Randomly selects a word from the provided array and displays it.
     *
     * @param palabras Array of word options filtered by current difficulty.
     */
    private void elegirPalabra(String[] palabras) {
        int indice = random.nextInt(palabras.length);

        if (palabras[indice].equals(palabraActual)) {
            elegirPalabra(palabras); //Funcion recursiva que puede ser infinita
            return; //Cuando seleccione otra palabra, finaliza esta función
        }

        palabraActual = palabras[indice];
        palabraGenLabel.setText(palabraActual);
    }

    /**
     * Evaluates player level to load the corresponding word bank difficulty.
     */
    private void generarPalabra() {
        logEvent("Generating new word for Level " + nivel);

        if (nivel <= 10) {
            elegirPalabra(Palabra.palabrasFaciles);
        } else if (nivel > 10 && nivel <= 20) {
            elegirPalabra(Palabra.palabrasMedias);
        } else if (nivel > 20 && nivel <= 30) {
            elegirPalabra(Palabra.palabrasDificiles);
        } else {
            elegirPalabra(Palabra.palabrasImposibles);
        }

        respuestaInField.clear();
        respuestaInField.requestFocus();
        mensajeLabel.setText("");
        iniciarTiempo();
    }

    /**
     * Starts and manages word countdown timer based on current player level.
     */
    private void iniciarTiempo() {
        segundosRestantes = Math.max(2, 20 - ((nivel - 1) / 5) * 2);
        timeLabel.setText(String.format("0:%02d ⏱️", segundosRestantes));

        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    segundosRestantes--;
                    timeLabel.setText(String.format("0:%02d ⏱️", segundosRestantes));

                    if (segundosRestantes <= 0) {
                        timeline.stop();
                        logEvent("Time expired at Level " + nivel + " for word: " + palabraActual);

                        try {
                            pantallaFinal();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );

        timeline.setCycleCount(segundosRestantes);
        timeline.play();
    }

    /**
     * Selects a random feedback message from the provided array.
     *
     * @param mensajes Array containing success or failure feedback strings.
     * @return The randomly selected message string.
     */
    private String elegirMensaje(String[] mensajes) {
        int indice = random.nextInt(mensajes.length);
        return mensajes[indice];
    }

    /**
     * Triggers validation logic via user interface interaction (button or Enter key).
     */
    @FXML
    private void validarRespuesta() {
        logEvent("User-triggered validation event");
        validarRespuesta(false);
    }

    /**
     * Validates whether the typed user input matches the target word.
     *
     * @param tiempoAgotado Indicates if validation was triggered by timer completion.
     * @return {@code true} if typed word matches exactly; {@code false} otherwise.
     */
    @FXML
    private boolean validarRespuesta(boolean tiempoAgotado) {
        String respuesta = respuestaInField.getText();

        if (respuesta.equals(palabraActual)) {
            logEvent("CORRECT answer submitted: " + respuesta);
            mensajeLabel.setText(elegirMensaje(Palabra.exitoso));
            timeline.stop();

            PauseTransition pausa = new PauseTransition(Duration.seconds(0.5));
            pausa.setOnFinished(event -> {
                nivel++;
                nivelLabel.setText("Nivel: " + nivel);
                generarPalabra();
            });
            pausa.play();

            return true;

        } else {
            logEvent("INCORRECT answer submitted: " + respuesta);
            mensajeLabel.setText(elegirMensaje(Palabra.fracasado));

            PauseTransition pausa = new PauseTransition(Duration.seconds(1));
            pausa.setOnFinished(event -> mensajeLabel.setText(""));
            pausa.play();

            if (tiempoAgotado) {
                generarPalabra();
            }

            return false;
        }
    }

    /**
     * Redirects the user back to the welcome main menu view.
     *
     * @param event Action event triggered by UI button interaction.
     * @throws IOException If the target FXML file cannot be loaded.
     */
    @FXML
    private void menuPrincipal(ActionEvent event) throws IOException {
        logEvent("Event: Returning to main menu");
        stopGame();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/escriturarapida/Welcome.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
    }

    /**
     * Transitions active scene to Game Over screen when time expires.
     *
     * @throws IOException If game over FXML layout cannot be located.
     */
    private void pantallaFinal() throws IOException {
        logEvent("Navigating to Game Over screen");
        stopGame();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/escriturarapida/gameOver.fxml")
        );

        Parent root = loader.load();

        FinalScreenController finalController = loader.getController();
        finalController.gameController = this;
        finalController.nivelAlcanzado();
        finalController.mensajeFinal();

        if (stage == null && timeLabel != null && timeLabel.getScene() != null) {
            stage = (Stage) timeLabel.getScene().getWindow();
        } else if (stage == null && palabraGenLabel != null && palabraGenLabel.getScene() != null) {
            stage = (Stage) palabraGenLabel.getScene().getWindow();
        }

        if (stage != null) {
            stage.setScene(new Scene(root));
        } else {
            System.err.println("Error: Unable to resolve active Stage.");
        }
    }

    /**
     * Logs game events to system console for debugging and traceability.
     *
     * @param mensaje Description of the event to log.
     */
    @Override
    public void logEvent(String mensaje) {
        System.out.println("[LOG]: " + mensaje);
    }
}