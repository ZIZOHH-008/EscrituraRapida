package org.example.escriturarapida;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Random;

/**
 * Utility class managing audio playback and background music tracks.
 * Handles game music randomization, menu themes, and audio stopping.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public class Musica {

    /** Active media player instance managing audio stream playback. */
    private static MediaPlayer reproductor;

    /**
     * Randomly selects a gameplay soundtrack from available audio tracks and plays it in continuous loop.
     */
    public static void reproducir() {
        detener();

        String[] canciones = {
                "/Music/Grimm.mp3",
                "/Music/GhostFight.mp3",
                "/Music/canzoni preferite.mp3",
                "/Music/mantis.mp3",
                "/Music/Loonboon.mp3",
        };

        Random random = new Random();
        String cancionSeleccionada = canciones[random.nextInt(canciones.length)];

        try {
            URL resource = Musica.class.getResource(cancionSeleccionada);
            if (resource != null) {
                Media media = new Media(resource.toExternalForm());
                reproductor = new MediaPlayer(media);
                reproductor.setCycleCount(MediaPlayer.INDEFINITE);
                reproductor.play();
            } else {
                System.err.println("No se encontró la canción: " + cancionSeleccionada);
            }
        } catch (Exception e) {
            System.err.println("No se pudo reproducir la música en este sistema: " + e.getMessage());
        }
    }

    /**
     * Loads and continuously loops designated menu background music.
     */
    public static void reproducirMenu() {
        detener();

        try {
            URL resource = Musica.class.getResource("/Music/menu.mp3");
            if (resource != null) {
                Media media = new Media(resource.toExternalForm());
                reproductor = new MediaPlayer(media);
                reproductor.setCycleCount(MediaPlayer.INDEFINITE);
                reproductor.play();
            } else {
                System.err.println("No se encontró el archivo /Music/menu.mp3");
            }
        } catch (Exception e) {
            System.err.println("No se pudo reproducir la música del menú en este sistema: " + e.getMessage());
        }
    }

    /**
     * Stops active background music if reproductor instance exists.
     */
    public static void detener() {
        if (reproductor != null) {
            try {
                reproductor.stop();
                reproductor.dispose();
            } catch (Exception e) {
                // Captura excepciones en el cierre del reproductor
            }
            reproductor = null;
        }
    }
}