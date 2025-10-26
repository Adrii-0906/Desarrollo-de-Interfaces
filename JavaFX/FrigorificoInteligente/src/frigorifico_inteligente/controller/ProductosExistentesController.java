/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package frigorifico_inteligente.controller;

import frigorifico_inteligente.model.producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class ProductosExistentesController implements Initializable {

    @FXML
    private TableView<producto> tableAlimentos;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private TableColumn<?, ?> colTipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<producto> productos = FXCollections.observableArrayList();
        
        ObservableList<producto> postres = new CrudPostresController().cargarProductos();
        productos.addAll(asignarTipo(postres, "Postre"));
        
        ObservableList<producto> bebidas = new CrudBebidasController().cargarProductos();
        productos.addAll(asignarTipo(bebidas, "Bebida"));
        
        ObservableList<producto> comidas = new CrudComidaController().cargarProductos();
        productos.addAll(asignarTipo(comidas, "Comida"));
        
        ObservableList<producto> verduras = new CrudVerdurasController().cargarProductos();
        productos.addAll(asignarTipo(verduras, "Verduras"));
        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        tableAlimentos.setItems(productos);
    }    

    @FXML
    private void IrAInicio(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/pantallaPrincipal.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.setTitle("Pantalla Principal"); 
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private ObservableList<producto> asignarTipo(
        ObservableList<producto> productos, String tipoCategoria) {
        
        for (producto p : productos) {
            p.setTipo(tipoCategoria); // Usa el nuevo setter
        }
        return productos;
    }
    
}
