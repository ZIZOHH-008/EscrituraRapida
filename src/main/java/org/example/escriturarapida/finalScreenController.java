package org.example.escriturarapida;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class finalScreenController {
    @FXML Button reiniciarButton;
    @FXML Label numFinalLabel;
    @FXML Label mensajeFinalLabel;

    private HelloController helloController;



    private void nivelAlcanzado() {
        numFinalLabel.setText(String.valueOf(helloController.nivel));
    }


    private void reiniciarNiveles(){

    }


    private void mensajeFinal(){

    }


}
