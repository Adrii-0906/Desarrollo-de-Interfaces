package Restaurante.controllers;

import Restaurante.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtPrecio;

    @FXML
     private ComboBox<String> cbCategorias;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Integer> colCantidad;
    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private Label labelError;


    private ObservableList<Producto> listaProductos;
    private String ficheroProductos = "src/Restaurante/archivos_restaurante/productos.txt";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        cbCategorias.getItems().addAll("Bebida", "Entrante", "Carne", "Pescado", "Postre");

        cbCategorias.getSelectionModel().selectFirst();

        listaProductos = FXCollections.observableArrayList();
        cargarProdcuctosDelFichero();
        tablaProductos.setItems(listaProductos);
    }

    @FXML
    public void anadirProductos(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText().replace(",","."));
            int cantidad = Integer.parseInt(txtCantidad.getText());

            String categoria = cbCategorias.getValue();

            if (nombre.isEmpty()) {
                labelError.setVisible(true);
                labelError.setText("El nombre esta vacio");
                return;
            }

            if (precio == 0) {
                labelError.setVisible(true);
                labelError.setText("El precio no puede ser 0");
            }

            Producto nuevoProducto = new Producto(nombre, precio, cantidad, categoria);

            listaProductos.add(nuevoProducto);
            labelError.setVisible(false);
            guardarEnFichero(nuevoProducto);

            limpiarCampos();



        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
            // Poner label para los errores
        }
    }


    private void guardarEnFichero(Producto p) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroProductos, true));
            bw.write(p.getNombre() + "," + p.getPrecio() + "," + p.getCantidad() + "," + p.getCategoria());
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void cargarProdcuctosDelFichero() {
        File fichero = new File(ficheroProductos);
        if (!fichero.exists()) {
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 4) {
                    String nombre = partes[0];
                    double precio = Double.parseDouble(partes[1]);
                    int cantidad = Integer.parseInt(partes[2]);
                    String categoria = partes[3];

                    listaProductos.add(new Producto(nombre, precio, cantidad, categoria));
                }
            }

            br.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void seleccionarProdcutos(MouseEvent event) {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();

        if (p != null) {
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtCantidad.setText(String.valueOf(p.getCantidad()));
            cbCategorias.setValue(p.getCategoria());
        }
    }

    @FXML
    public void modificarProductos(ActionEvent event) {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();

        if (p == null) {
            // Poner el label de error
            return;
        }

        try {
            p.setNombre(txtNombre.getText());
            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
            p.setCantidad(Integer.parseInt(txtCantidad.getText()));
            p.setCategoria(cbCategorias.getValue());

            tablaProductos.refresh();

            reescribirFichero();

            // Mostrar label con un mensaje de exito
            limpiarCampos();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarProducto(ActionEvent event) {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();

        if (p == null) {
            // Poner el label de error
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Seguro que quieres borrar " + p.getNombre() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            listaProductos.remove(p);

            reescribirFichero();

            // Mostrar label con un mensaje de exito
            limpiarCampos();
        }
    }

    public void reescribirFichero() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroProductos));

            for (Producto p : listaProductos) {
                bw.write(p.getNombre() + "," + p.getPrecio() + "," + p.getCantidad() + "," + p.getCategoria());
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos() {
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        cbCategorias.getSelectionModel().selectFirst();
    }


    public void volverAInicio(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Restaurante/vistas/pantallaPrincipal.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Restaurante Casa Manolo");
            stage.setScene(new Scene(root));
            stage.centerOnScreen(); // Opcional: Centrar ventana
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
