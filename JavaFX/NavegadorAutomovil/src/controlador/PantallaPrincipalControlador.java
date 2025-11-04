/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

import javafx.event.ActionEvent; // Si usas botones ActionEvent
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent; // <-- ¡ESTA ES LA CLAVE PARA EL ERROR 'PARENT'!
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent; // <-- La usamos para el evento de clic en la imagen
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class PantallaPrincipalControlador implements Initializable {

    @FXML
    private ImageView gps;
    @FXML
    private ImageView radio;
    @FXML
    private Label horaReal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciarReloj();
    }

  

    @FXML
    private void irARadio(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/vista/radio.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAGps(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/vista/gps.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private void iniciarReloj() {
        // Crea una Timeline que se ejecuta cada 1 segundo (Duration.seconds(1))
        Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.seconds(1), // Duración de cada ciclo (1 segundo)
                event -> {
                    // Acción que se ejecuta en cada ciclo:
                    
                    // a) Obtener la hora actual
                    LocalTime horaActual = LocalTime.now();
                    
                    // b) Formatear y actualizar el texto del Label
                    horaReal.setText(horaActual.format(formatter));
                }
            )
        );

        // Hace que la Timeline se repita indefinidamente
        timeline.setCycleCount(Animation.INDEFINITE);
        
        // Inicia la animación
        timeline.play();
    }

    @FXML
    private void IrATelefono(MouseEvent event) {
        try {
            
        Parent root1 = FXMLLoader.load(getClass().getResource("/vista/telefono.fxml")); 

        Scene siguienteScene1 = new Scene(root1);
        Stage appStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage1.setScene(siguienteScene1);
        appStage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
