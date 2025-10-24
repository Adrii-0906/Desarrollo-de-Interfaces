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
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class CalendarioController implements Initializable {

    @FXML
    private Pane webCalendar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarCalendario();
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
    
     private void inicializarCalendario() {
        // Crear WebView
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();

        String urlMapa = "https://calendar.google.com/calendar/u/1/r";
        engine.load(urlMapa);

        // Ajustar tamaño del WebView al Pane
        webView.prefWidthProperty().bind(webCalendar.widthProperty());
        webView.prefHeightProperty().bind(webCalendar.heightProperty());

        // Añadir al panelCalendario
        webCalendar.getChildren().add(webView);
    }
}
