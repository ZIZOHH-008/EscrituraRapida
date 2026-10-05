package org.example.escriturarapida;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

        String[] canciones = {
                "/Music/Grimm.mp3",
                "/Music/GhostFight.mp3",
                "/Music/canzoni preferite.mp3",
                "/Music/mantis.mp3",
                "/Music/Loonboon.mp3",
        };

        Random random = new Random();
        String ruta = Musica.class
                .getResource(canciones[random.nextInt(canciones.length)])
                .toExternalForm();

        Media media = new Media(ruta);
        reproductor = new MediaPlayer(media);

        reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        reproductor.play();
    }

    /**
     * Loads and continuously loops designated menu background music.
     */
    public static void reproducirMenu() {
        String ruta = Musica.class
                .getResource("/Music/menu.mp3")
                .toExternalForm();

        Media media = new Media(ruta);
        reproductor = new MediaPlayer(media);

        reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        reproductor.play();
    }

    /**
     * Stops active background music if reproductor instance exists.
     */
    public static void detener() {
        if (reproductor != null) {
            reproductor.stop();
        }
    }
}