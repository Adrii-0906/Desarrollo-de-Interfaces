/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package frigorifico_inteligente.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime; // Clase para la fecha y hora
import java.time.format.DateTimeFormatter; // Clase para el formato
import java.util.Locale;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    private Label laberHora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarReloj();
        // TODO
    }
    
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    
    private void actualizarReloj() {
        LocalDateTime ahora = LocalDateTime.now();
        
        // Formato para incluir el día de la semana y todo en español
        Locale spanishLocale = new Locale("es", "ES");
        DateTimeFormatter formateador = DateTimeFormatter
                .ofPattern("EEEE, dd 'de' MMMM 'de' yyyy \n 'a las' HH:mm")
                .withLocale(spanishLocale);
        
        String fechaCompleta = ahora.format(formateador);
        
        // 4. Actualizar el texto del Label en la interfaz
        laberHora.setText(fechaCompleta);
    
    }
}
