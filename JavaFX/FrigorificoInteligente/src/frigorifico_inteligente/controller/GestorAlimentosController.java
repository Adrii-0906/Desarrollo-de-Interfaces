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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class GestorAlimentosController implements Initializable {

    @FXML
    private Label postres;
    @FXML
    private Label bebidas;
    @FXML
    private Label verduras;
    @FXML
    private Label comida;
    @FXML
    private Label frigorifico;
    @FXML
    private Pane salir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irAPostres(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/crudPostres.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irABebidas(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/crudBebidas.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAVerduras(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/crudVerduras.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAComida(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/crudComida.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAFrigorifico(MouseEvent event) {
    }

    @FXML
    private void irAInicio(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/pantallaPrincipal.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
