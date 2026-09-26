package org.example.escriturarapida;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


public class HelloController {

    // ======================== VARIABLES========================
    @FXML private Label palabraGenLabel;
    @FXML private TextField respuestaInField;
    @FXML private Label mensajeLabel;

    private String palabraActual;

    private String[] palabras = {
            "Pepe",
            "Rodolfo",
            "JavaFX",
            "Escritura rápida",
            "Hola mundo"
    };

    private Random random = new Random();




    // ======================== METODOS ========================


    @FXML public void initialize() {
        generarPalabra();
    }

    //Genera la palabra en el sistemas nomas
    private void generarPalabra() {
        int indice = random.nextInt(palabras.length);

        palabraActual = palabras[indice];
        palabraGenLabel.setText(palabraActual);
    }


    @FXML
    private void validarRespuesta() {

        String respuesta = respuestaInField.getText();

        if (respuesta.equals(palabraActual)) {
            mensajeLabel.setText("¡Correcto!");
        } else {
            mensajeLabel.setText("Has escrito otra cosa...");
        }
    }



    @FXML private void menuPrincipal(ActionEvent event) throws IOException{
        //Creo que se debería parar y reiniciar el flujo del juego, y luego si tirar al menú

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