package org.example.escriturarapida;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;




public class FinalScreenController {
    @FXML Button reiniciarButton;
    @FXML Label numFinalLabel;
    @FXML Label mensajeFinalLabel;

    public GameController gameController;
    private WelcomeController welcomeController;





    public void nivelAlcanzado() {
        numFinalLabel.setText(String.valueOf(gameController.nivel));
    }



    //este es el mismo metodo de "Welcome controler", debemos buscar una manera de no repetir
    @FXML public void reiniciarNiveles(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("basicGame.fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        GameController gameController = loader.getController();
        gameController.stage = stage;

        stage.setScene(new Scene(root));
    }



    public void mensajeFinal(){
        int nivel = gameController.nivel;

        if(nivel<6){
            mensajeFinalLabel.setText("Pndjo no dura nada");
        }
        else if(nivel>5 && nivel<20){
            mensajeFinalLabel.setText("Podrías mejorar ese nivel...");
        }
        else if(nivel>20 && nivel<31){
            mensajeFinalLabel.setText("Lo podrías mejorar, pero está bien");
        }
        else if(nivel>=31){
            mensajeFinalLabel.setText("EL TRUE GOAT");
        }

    }


}
