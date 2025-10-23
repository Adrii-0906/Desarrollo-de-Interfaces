/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.persona;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class VistaModificarController implements Initializable {

    @FXML
    private TextField tNombre;
    @FXML
    private TextField tApellidos;
    @FXML
    private TextField tEdad;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalir;
    
    persona aux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initp(persona p){
        aux=p;
        tNombre.setText(aux.getNombre());
        tApellidos.setText(aux.getApellidos());
        tEdad.setText(aux.getEdad()+"");
    }

    @FXML
    private void modificarPersona(MouseEvent event) {
        aux.setNombre(tNombre.getText());
        aux.setApellidos(tApellidos.getText());
        aux.setEdad(parseInt(tEdad.getText()));
        
        Stage agregar = (Stage) btnSalir.getScene().getWindow();
        agregar.close();
    }

    @FXML
    private void salir(MouseEvent event) {
        Stage agregar = (Stage) btnSalir.getScene().getWindow();
        agregar.close();
    }
    
}
