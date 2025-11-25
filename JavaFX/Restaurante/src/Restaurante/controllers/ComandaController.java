package Restaurante.controllers;

import Restaurante.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ComandaController implements Initializable {

    @FXML
    private Label labelTitulo;

    @FXML
    private Label labelTotal;

    @FXML
    private Label labelDevolver;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnCobrar;

    @FXML
    private TableColumn<Producto, Integer> colUnidades;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableView<Producto> tablaComanda;

    @FXML
    private ComboBox<Producto> cbBebida;
    @FXML
    private ComboBox<Producto> cbEntrante;
    @FXML
    private ComboBox<Producto> cbCarne;
    @FXML
    private ComboBox<Producto> cbPescado;
    @FXML
    private ComboBox<Producto> cbPostre;

    private ObservableList<Producto> productosCuenta;

    //Rutas de ficheros
    private String ficheroProductos = "src/Restaurante/archivos_restaurante/productos.txt";
    private String ficheroMesaActual;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int numeroMesa = PantallaPrincipalController.numeroDeMesaActual;
        labelTitulo.setText("Mesa " + numeroMesa);

        ficheroMesaActual = "src/Restaurante/archivos_restaurante/mesa" + numeroMesa + ".txt";

        colUnidades.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        productosCuenta = FXCollections.observableArrayList();
        tablaComanda.setItems(productosCuenta);

        cargarInventarioEnCombos();
        cargarPedidoExistente();
        actualizarTotal();
    }

    @FXML
    public void anadirBebida(ActionEvent event) {
        gestionarProdcuto(cbBebida.getValue());
    }

    @FXML
    public void anadirEntrante(ActionEvent event) {
        gestionarProdcuto(cbEntrante.getValue());
    }

    @FXML
    public void anadirCarne(ActionEvent event) {
        gestionarProdcuto(cbCarne.getValue());
    }

    @FXML
    public void anadirPescado(ActionEvent event) {
        gestionarProdcuto(cbPescado.getValue());
    }

    @FXML
    public void anadirPostre(ActionEvent event) {
        gestionarProdcuto(cbPostre.getValue());
    }

    private void gestionarProdcuto(Producto productoSeleccinado) {
        if (productoSeleccinado == null) {
            return;
        }

        boolean encontrado = false;

        for (Producto p: productosCuenta) {
            if (p.getNombre().equals(productoSeleccinado.getNombre())) {
                p.setCantidad(p.getCantidad() + 1);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            Producto nuevoProducto = new Producto(productoSeleccinado.getNombre(),productoSeleccinado.getPrecio(),1,productoSeleccinado.getCategoria());
            productosCuenta.add(nuevoProducto);
        }

        tablaComanda.refresh();
        actualizarTotal();
        guardarComandaEnFichero();
    }

    private void cargarInventarioEnCombos() {
        File file = new File(ficheroProductos);
        if(!file.exists()) {
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 4) {
                    Producto nuevoProd = new Producto(partes[0], Double.parseDouble(partes[1]), Integer.parseInt(partes[2]), partes[3]);

                    String cat = nuevoProd.getCategoria().toLowerCase().trim();

                    if (cat.contains("bebida")) {
                        cbBebida.getItems().add(nuevoProd);
                    } else if (cat.contains("entrantes")) {
                        cbEntrante.getItems().add(nuevoProd);
                    } else if (cat.contains("carne")) {
                        cbCarne.getItems().add(nuevoProd);
                    } else if (cat.contains("pescado")) {
                        cbPescado.getItems().add(nuevoProd);
                    } else if (cat.contains("postre")) {
                        cbPostre.getItems().add(nuevoProd);
                    }
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void guardarComandaEnFichero() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroMesaActual));

            for (Producto p : productosCuenta) {
                bw.write(p.getNombre() + "," + p.getPrecio() + "," + p.getCantidad());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarPedidoExistente() {
        File file = new File(ficheroMesaActual);
        if (!file.exists()) {
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length >= 3) {
                    String nombre = partes[0];
                    double precio = Double.parseDouble(partes[1]);
                    int cantidad = Integer.parseInt(partes[2]);

                    Producto nuevoProd = new Producto(nombre, precio, cantidad, "");
                    productosCuenta.add(nuevoProd);
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void actualizarTotal() {
        double total = 0;
        for (Producto p : productosCuenta) {
            total += (p.getPrecio() * p.getCantidad());
        }
        labelTotal.setText(String.format("%.2f €", total));
    }

    @FXML
    public void volverAtras(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Restaurante/vistas/pantallaPrincipal.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Restaurante Casa Manolo");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
