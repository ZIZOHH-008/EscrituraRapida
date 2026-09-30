package org.example.escriturarapida;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Random;

public class Musica {
    private static MediaPlayer reproductor;

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



    public static void reproducirMenu() {
        String ruta = Musica.class
                .getResource("/Music/menu.mp3")
                .toExternalForm();

        Media media = new Media(ruta);
        reproductor = new MediaPlayer(media);

        reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        reproductor.play();
    }

    public static void detener() {
        if (reproductor != null) {
            reproductor.stop();
        }
    }
}