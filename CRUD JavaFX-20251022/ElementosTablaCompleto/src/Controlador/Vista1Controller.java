/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.persona;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class Vista1Controller implements Initializable {

    @FXML
    private TextField tNombre;
    @FXML
    private TextField tEdad;
    @FXML
    private TextField tApellidos;
    @FXML
    private TableView<persona> tablaPers;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colEdad;
    @FXML
    private Button btn;
    
    private ObservableList<persona> gpersonas;
    @FXML
    private Button btnMod;
    @FXML
    private Button btnEliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gpersonas = FXCollections.observableArrayList();
        
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory("edad"));
    }    

    @FXML
    private void agregar(MouseEvent event) {
        String aNombre = tNombre.getText();
        String aApellidos = tApellidos.getText();
        int aEdad = parseInt(tEdad.getText());
        
        persona elemento = new persona(aNombre, aApellidos, aEdad);
        
        gpersonas.add(elemento);
        tablaPers.setItems(gpersonas);
    }

    @FXML
    private void modificarPers(MouseEvent event) {
        
        persona aux = tablaPers.getSelectionModel().getSelectedItem();
        
        if(aux==null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("La persona seleccionada no existe en la tabla");
            alerta.showAndWait();
        }else{
            
            String aNombre = tNombre.getText();
            String aApellidos = tApellidos.getText();
            int aEdad = parseInt(tEdad.getText());

            persona elemento = new persona(aNombre, aApellidos, aEdad);
            
            if(!gpersonas.contains(elemento)){
                aux.setNombre(aNombre);
                aux.setApellidos(aApellidos);
                aux.setEdad(aEdad);
                
                tablaPers.refresh();
            }

        }

    }

    @FXML
    private void eliminarPers(MouseEvent event) {
        persona aux = tablaPers.getSelectionModel().getSelectedItem();
        
        if(aux==null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("La persona seleccionada no existe en la tabla");
            alerta.showAndWait();
        }else{
            gpersonas.remove(aux);
            tablaPers.refresh();
        }
    }

    @FXML
    private void seleccionarPers(MouseEvent event) {
        persona aux = tablaPers.getSelectionModel().getSelectedItem();
        
        if(aux!=null){
            tNombre.setText(aux.getNombre());
            tApellidos.setText(aux.getApellidos());
            tEdad.setText(aux.getEdad()+"");
        }
    }
    
    
    
}
