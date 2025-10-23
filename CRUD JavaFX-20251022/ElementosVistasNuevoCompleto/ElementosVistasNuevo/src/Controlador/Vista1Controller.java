/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.persona;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class Vista1Controller implements Initializable {

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
    private void agregar(MouseEvent event) throws IOException {
        Stage nuevaV = new Stage();
        FXMLLoader objetoAgregar = new FXMLLoader (getClass().getResource("/Vista/vistaAgregar.fxml"));

        Parent root = objetoAgregar.load();
        VistaAgregarController control = objetoAgregar.getController();
        control.initAtributos(gpersonas);

        // Cargo el scene
            
        Scene scene = new Scene(root);
            
        nuevaV.setTitle("Agregar");
        // Seteo la scene y la muestro
        nuevaV.initModality(Modality.APPLICATION_MODAL);
        nuevaV.setScene(scene);
        nuevaV.showAndWait();
            
        //persona nueva = control.getAux();
        //System.out.print(nueva.getApellidos());
        //gpersonas.add(nueva);
        tablaPers.setItems(gpersonas);
        tablaPers.refresh();
    }

    @FXML
    private void modificarPers(MouseEvent event) throws IOException {
        
        persona p = tablaPers.getSelectionModel().getSelectedItem();
        Stage nuevaV = new Stage();
        FXMLLoader objetoAgregar = new FXMLLoader (getClass().getResource("/Vista/vistaModificar.fxml"));

        Parent root = objetoAgregar.load();
        VistaModificarController control = objetoAgregar.getController();
        control.initp(p);
        
        Scene scene = new Scene(root);
            
        nuevaV.setTitle("Modificar");
        // Seteo la scene y la muestro
        nuevaV.initModality(Modality.APPLICATION_MODAL);
        nuevaV.setScene(scene);
        nuevaV.showAndWait();
        
        tablaPers.refresh();
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
    }
    
    
    
}
