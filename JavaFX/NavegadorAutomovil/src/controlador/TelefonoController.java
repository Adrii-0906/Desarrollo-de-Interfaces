/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class TelefonoController implements Initializable {

    @FXML
    private Label horaReal;
    @FXML
    private ImageView casa;
    @FXML
    private ImageView atras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irACasa(MouseEvent event) {
        try {
            
        Parent root1 = FXMLLoader.load(getClass().getResource("/vista/pantallaPrincipal.fxml")); 

        Scene siguienteScene1 = new Scene(root1);
        Stage appStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage1.setScene(siguienteScene1);
        appStage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void salir(MouseEvent event) {
        try {
            
        Parent root1 = FXMLLoader.load(getClass().getResource("/vista/pantallaPrincipal.fxml")); 

        Scene siguienteScene1 = new Scene(root1);
        Stage appStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage1.setScene(siguienteScene1);
        appStage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
