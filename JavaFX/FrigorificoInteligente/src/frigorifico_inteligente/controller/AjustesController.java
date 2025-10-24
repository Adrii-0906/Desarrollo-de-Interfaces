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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class AjustesController implements Initializable {

    @FXML
    private Pane ajustes;
    @FXML
    private Slider slider;
    @FXML
    private ChoiceBox<?> choiceBox;
    @FXML
    private CheckBox ckeckBox;
    @FXML
    private Hyperlink enlace;
    @FXML
    private Label labelTemperatura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarSlider();
        
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
    
    
    private void configurarSlider() {
        slider.setMin(1);
        slider.setMax(7);
        slider.setBlockIncrement(1);
       
        labelTemperatura.setText(String.format("%.0f °C", slider.getValue()));

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            
            String valorFormateado = String.format("%.0f °C", newValue.doubleValue());
            labelTemperatura.setText(valorFormateado);
        });
    }
    
}
