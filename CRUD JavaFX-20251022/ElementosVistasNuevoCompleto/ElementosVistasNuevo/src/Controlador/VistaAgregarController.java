/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.persona;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
public class VistaAgregarController implements Initializable {

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalir;
    @FXML
    private TextField tNombre;
    @FXML
    private TextField tApellidos;
    @FXML
    private TextField tEdad;
    
    private persona paux;
    private ObservableList<persona> gpersonas2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initAtributos(ObservableList<persona> lista){
        gpersonas2=lista;
    }
    
    @FXML
    private void agregarPersona(MouseEvent event) {
        String aNombre = tNombre.getText();
        String aApellidos = tApellidos.getText();
        int aEdad = parseInt(tEdad.getText());
        
        paux = new persona(aNombre, aApellidos, aEdad);
        
        gpersonas2.add(paux);
        
        Stage agregar = (Stage) btnSalir.getScene().getWindow();
        agregar.close();
        
    }

    @FXML
    private void salir(MouseEvent event) {
        Stage agregar = (Stage) btnSalir.getScene().getWindow();
        agregar.close();
        
    }

    public persona getAux() {
        return paux;
    }
    
}
