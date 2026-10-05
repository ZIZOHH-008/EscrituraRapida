package org.example.escriturarapida.interfaces;

/**
 * Interface defining lifecycle operations and event logging for game controllers.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public interface IGameController {

    /**
     * Starts primary controller logic and game loop workflow.
     */
    void startGame();

    /**
     * Stops running resources such as timers and background audio.
     */
    void stopGame();

    /**
     * Logs system events and debugging output to console.
     *
     * @param message Description of event or action being recorded.
     */
    default void logEvent(String message) {
        System.out.println("[POE - EVENT LOG]: " + message);
    }
}