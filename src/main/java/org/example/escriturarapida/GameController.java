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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;




public class GameController {

    @FXML private Label palabraGenLabel;
    @FXML private TextField respuestaInField;
    @FXML private Label mensajeLabel;
    @FXML private Label timeLabel;
    @FXML private Label nivelLabel;

    private String palabraActual;
    private PauseTransition pausa;
    private Timeline timeline;
    private int segundosRestantes;

    private String[] palabras = {
            "Pepe",
            "Rodolfo",
            "JavaFX",
            "Mártir",
            "Hola mundo",
            "Computadora",
            "Programación",
            "Teclado",
            "Ventana",
            "Desarrollo",
            "Algoritmo",
            "Variable",
            "Método",
            "Controlador",
            "Interfaz",
            "Software",
            "Aplicación",
            "Proyecto",
            "Tecnología",
            "Código fuente",
            "Escritura rápida",
            "Inteligencia",
            "Programación",
            "Desarrollo",
            "Sistema operativo"
    };

    private Random random = new Random();
    public int nivel = 1;



    @FXML public void initialize() {
        generarPalabra();
        nivelLabel.setText("Nivel: " + nivel);
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
        //Esto lo saqué de internet; debemos analizar esta formula
        //Básicamente, permite lo del tiempo Nivel 1–5 tienen 20seg; Nivel 6–10 tienen 18seg
        segundosRestantes = Math.max(2, 20 - ((nivel - 1) / 5) * 2);
        timeLabel.setText(String.format("0:%02d ⏱️", segundosRestantes));

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {   //espera 1 segundo entre ejecutciones
                    segundosRestantes--;timeLabel.setText(String.format("0:%02d ⏱️", segundosRestantes));   //resta tiempo y muestra

                    if (segundosRestantes == 0) {   //Se repide el ciclo
                        timeline.stop();
                        validarRespuesta(true);
                        try {
                            pantallaFinal();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );

        timeline.setCycleCount(segundosRestantes);  //Establece que el bloque "timeline" se repita 5 veces (pq debe restar en total 5 seg)
        timeline.play();    //Lo inicia
    }


    //Como en el fxml no se pueden pasar parámatros, tocó hacer una sobrecarga de metodos
    @FXML private void validarRespuesta() {
        validarRespuesta(false);
    }

    @FXML private void validarRespuesta(boolean tiempoAgotado) {

        String respuesta = respuestaInField.getText(); //obtiene el texto que escribió el usuario (textfield)

        if (respuesta.equals(palabraActual)) {
            mensajeLabel.setText("Correcto");
            timeline.stop();    //Detiene el bloque de actualizar el tiempo de timeline

            //Aumenta el nivel
            nivel++;
            nivelLabel.setText("Nivel: " + nivel);

            generarPalabra();   //Repite el bucle de pedir palabra

        } else {
            mensajeLabel.setText("Has escrito otra cosa...");

            if (tiempoAgotado) {generarPalabra();}
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


    private void pantallaFinal() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("gameOver.fxml")
        );

        Parent root = loader.load();

        FinalScreenController finalController = loader.getController();
        finalController.gameController = this;
        finalController.nivelAlcanzado();
        finalController.mensajeFinal();

        Stage stage = (Stage) timeLabel.getScene().getWindow();

        stage.setScene(new Scene(root));
    }
}