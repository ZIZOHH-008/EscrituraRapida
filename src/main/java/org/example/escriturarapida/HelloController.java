package org.example.escriturarapida;

import javafx.animation.PauseTransition;
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
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;






public class HelloController {

    @FXML private Label palabraGenLabel;
    @FXML private TextField respuestaInField;
    @FXML private Label mensajeLabel;
    @FXML private Label timeLabel;

    private String palabraActual;
    private PauseTransition pausa;
    private Timeline timeline;
    private int segundosRestantes;

    private String[] palabras = {
            "Pepe",
            "Rodolfo",
            "JavaFX",
            "Mártir",
            "Hola mundo"
    };

    private Random random = new Random();




    @FXML public void initialize() {
        generarPalabra();
    }


    private void generarPalabra() {
        int indice = random.nextInt(palabras.length); //un numero aleatorio para tomar un indice aleatorio

        palabraActual = palabras[indice];   //Guarda la palabra del indice
        palabraGenLabel.setText(palabraActual); //Muestra en el Label la palabra generada

        respuestaInField.clear();   //Limpia la palabra que estaba escrita
        respuestaInField.requestFocus();    //El cursor queda listo para escribir
        mensajeLabel.setText("");       // Limpia el mensaje que sale al validar
        iniciarTiempo();
    }



    private void iniciarTiempo() {
        segundosRestantes = 5;
        timeLabel.setText("0:05 ⏱️"); //aparece el tiempo

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {   //espera 1 segundo entre ejecutciones
                    segundosRestantes--;timeLabel.setText(String.format("0:%02d ⏱️", segundosRestantes));   //resta tiempo y muestra

                    if (segundosRestantes == 0) {   //Se repide el ciclo
                        generarPalabra();
                    }
                })
        );

        timeline.setCycleCount(5);  //Establece que el bloque "timeline" se repita 5 veces (pq debe restar en total 5 seg)
        timeline.play();    //Lo inicia
    }



    @FXML private void validarRespuesta() {

        String respuesta = respuestaInField.getText(); //obtiene el texto que escribió el usuario (textfield)

        if (respuesta.equals(palabraActual)) {
            mensajeLabel.setText("Correcto");
            timeline.stop();    //Detiene el bloque de actualizar el tiempo de timeline
            generarPalabra();   //Repite el bucle de pedir palabra

        } else {
            mensajeLabel.setText("Has escrito otra cosa...");
        }
    }




    /*Esta funcion es para que podamos hacer una espera entre lineas de comando :D
    * Por el momento no sirve xD
    * */
    private void delay(double timeDelay){
        PauseTransition pause = new PauseTransition(Duration.seconds(timeDelay));
        pause.setOnFinished(e -> generarPalabra());
        pause.play();
    }



    @FXML private void menuPrincipal(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("Welcome.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
    }
}