/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package frigorifico_inteligente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class FrigorificoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irAInicio(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/pantallaPrincipal.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.setTitle("Pantalla Principal");        
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
