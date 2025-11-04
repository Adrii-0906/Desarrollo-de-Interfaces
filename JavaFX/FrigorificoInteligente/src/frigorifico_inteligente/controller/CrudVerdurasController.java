/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package frigorifico_inteligente.controller;

import frigorifico_inteligente.model.producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class CrudVerdurasController implements Initializable {
private static final String carpeta = "carpetaFicheros";
    
    private static final String fichero = "ficheroVerduras.ser";
    private static final String ficheroRuta = carpeta + File.separator + fichero;

    @FXML
    private TextField nombre;
    @FXML
    private TableView<producto> tableAlimentos;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private Button bAnadir;
    @FXML
    private TextField cantidad;
    
    private ObservableList<producto> gproducto;
    @FXML
    private Button bEliminar;
    @FXML
    private Button bModifcar;
    @FXML
    private ChoiceBox<String> elegirTipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> unidades = FXCollections.observableArrayList("kg", "g", "ud");
        elegirTipo.setItems(unidades);
        elegirTipo.setValue("ud");
        
        gproducto = cargarProductos();
        
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        
        tableAlimentos.setItems(gproducto);
    }    
    
    @FXML
    private void irAInicio(MouseEvent event) {
        try {
            
        Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/pantallaPrincipal.fxml")); 

        Scene siguienteScene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.setScene(siguienteScene);
        appStage.setTitle("Pantalla Pricipal");        
        appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void agregar(MouseEvent event) {
        
        String aNombre = nombre.getText();
        String aCantidad = cantidad.getText();
        String aUnidad = elegirTipo.getValue();
        
        producto producto = new producto(aNombre, aCantidad, aUnidad);
        
        gproducto.add(producto);
        tableAlimentos.setItems(gproducto);
        
        guardarProductos();
    }

    @FXML
    private void modificar(MouseEvent event) {
        
        producto aux = tableAlimentos.getSelectionModel().getSelectedItem();
        
        if (aux == null) {
            
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("El prodcuto seleccinado no existe");
            alerta.showAndWait();
        } else {
            
            String aNombre = nombre.getText();
            String aCantidad = cantidad.getText();
            String aUnidad = elegirTipo.getValue();
            
            producto producto = new producto(aNombre, aCantidad, aUnidad);
            
            if (!gproducto.contains(producto)) {
                aux.setNombre(aNombre);
                aux.setCantidad(aCantidad);
                
                tableAlimentos.refresh();
            }
        }
        
        guardarProductos();
    }

    @FXML
    private void eliminar(MouseEvent event) {
        
        producto aux = tableAlimentos.getSelectionModel().getSelectedItem();
        
        if (aux == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("El prodcuto seleccinado no existe");
            alerta.showAndWait();
        } else {
            gproducto.remove(aux);
            tableAlimentos.refresh();
        }
        
        guardarProductos();
    }

    @FXML
    private void seleccionarProducto(MouseEvent event) {
        
        producto aux = tableAlimentos.getSelectionModel().getSelectedItem();
        
        if (aux != null) {
            nombre.setText(aux.getNombre());
            cantidad.setText(aux.getCantidad());   
        }
    }
    
    private void guardarProductos() {
        File directorio = new File(carpeta); // Ejemplo: "carpetaFicheros"

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
            } else {
                return; 
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroRuta))) { 

            oos.writeObject(new ArrayList<>(gproducto));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<producto> cargarProductos() {
        File file = new File(ficheroRuta);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                
                ArrayList<producto> listaCargada = (ArrayList<producto>) ois.readObject();
                
                return FXCollections.observableArrayList(listaCargada);
                
            } catch (IOException | ClassNotFoundException e) {
                return FXCollections.observableArrayList(); 
            }
        }
        return FXCollections.observableArrayList();
    }
    
}
