package org.example.escriturarapida;

import javafx.application.Application;

/**
 * Launcher wrapper class hosting main entry point.
 * Ensures executable JAR compatibility across different Java execution environments.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public class Launcher {

    /**
     * Application entry point. Invokes JavaFX launcher mechanism.
     *
     * @param args Command line arguments passed during execution.
     */
    public static void main(String[] args) {
        System.out.println("¿Inició? Ji");
        Application.launch(EscrituraRapidaApplication.class, args);
    }
}