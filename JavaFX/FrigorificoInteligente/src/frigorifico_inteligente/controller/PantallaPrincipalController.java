/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package frigorifico_inteligente.controller;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.time.LocalDateTime; // Clase para la fecha y hora
import java.time.format.DateTimeFormatter; // Clase para el formato
import java.util.Locale;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    private Label laberHora;
    
    private final DateTimeFormatter formateador = DateTimeFormatter
            .ofPattern("HH:mm:ss") // Formato de hora con segundos
            .withLocale(new Locale("es", "ES"));
    @FXML
    private Label labelFecha;
    
    private final DateTimeFormatter formateadorFecha = DateTimeFormatter
            .ofPattern("dd/MM/yyyy");
    @FXML
    private AnchorPane alimentos;
    @FXML
    private AnchorPane calendar;
    @FXML
    private AnchorPane listas;
    @FXML
    private AnchorPane ajustes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarRelojTiempoReal();
        
        mostrarFechaNumerica();
    }
    
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private void actualizarReloj() {
        LocalDateTime ahora = LocalDateTime.now();
        
        String horaCompleta = ahora.format(formateador);
        
        laberHora.setText(horaCompleta);    
    }
    
    private void iniciarRelojTiempoReal() {
        actualizarReloj(); 
        
        
        Timeline timeline = new Timeline(
            
            new KeyFrame(Duration.seconds(1), event -> {
                
                actualizarReloj();
            })
        );
        
        
        timeline.setCycleCount(Animation.INDEFINITE);
        
        timeline.play();
    }
    
    private void mostrarFechaNumerica() {
        LocalDate fechaHoy = LocalDate.now();
        
        // Formatea la fecha actual
        String fechaNumerica = fechaHoy.format(formateadorFecha);
        
        // Actualiza el texto de la segunda etiqueta
        labelFecha.setText(fechaNumerica);
    }

    @FXML
    private void irAGestorAlimentos(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/gestorAlimentos.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irACalendar(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/calendario.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAListas(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/listaAlimentos.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAAjustes(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/ajustes.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
