/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class GpsController implements Initializable {

    @FXML
    private ImageView casa;
    @FXML
    private ImageView atras;
    @FXML
    private Label horaReal;
    @FXML
    private Pane panelMapa;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarReloj();
        inicializarMapaWeb();
    }    

    private void inicializarMapaWeb() {
        // Crear WebView
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();

        String urlMapa = "https://www.google.com/maps/";
        engine.load(urlMapa);

        // Ajustar tamaño del WebView al Pane
        webView.prefWidthProperty().bind(panelMapa.widthProperty());
        webView.prefHeightProperty().bind(panelMapa.heightProperty());

        // Añadir al panelMapa
        panelMapa.getChildren().add(webView);
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
    
}
