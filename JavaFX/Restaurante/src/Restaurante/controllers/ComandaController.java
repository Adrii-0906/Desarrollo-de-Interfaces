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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

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
    private Label labelMensaje;

    @FXML
    private ImageView btnSalir;

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
        Producto p = cbBebida.getValue();
        if (p != null) {
            gestionarProdcuto(p);
            cbBebida.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void anadirEntrante(ActionEvent event) {
        Producto p = cbEntrante.getValue();
        if (p != null) {
            gestionarProdcuto(p);
            cbEntrante.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void anadirCarne(ActionEvent event) {
        Producto p = cbCarne.getValue();
        if (p != null) {
            gestionarProdcuto(p);
            cbCarne.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void anadirPescado(ActionEvent event) {
        Producto p = cbPescado.getValue();
        if (p != null) {
            gestionarProdcuto(p);
            cbPescado.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void anadirPostre(ActionEvent event) {
        Producto p = cbPostre.getValue();
        if (p != null) {
            gestionarProdcuto(p);
            cbPostre.getSelectionModel().clearSelection();
        }
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
    public void cobrarMesa(ActionEvent event) {

        mostrarMensaje("", false);

        double total = 0;

        for (Producto p : productosCuenta) {
            total += (p.getPrecio() * p.getCantidad());
        }

        if (total == 0) {
            mostrarMensaje("La mesa está vacía, añade productos antes de cobrar.", true);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cobrar mesa");
        alert.setHeaderText("Cuenta total de la mesa: " + String.format("%.2f", total));
        alert.setContentText("Selecciona el metodo de pago");

        // Creamos los botones para decidir la forma de pago
        ButtonType btnEfectivo = new ButtonType("Efectivo");
        ButtonType btnTarjeta = new ButtonType("Tarjeta");
        ButtonType btnCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(btnEfectivo, btnTarjeta, btnCancelar);

        java.util.Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get() == btnTarjeta) {
            cobrarDirectamente(total, "Tarjeta");
        } else if (resultado.get() == btnEfectivo) {
            pagoConEfectivo(total);
        }
    }

    private void pagoConEfectivo(double total) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Pago en Efectivo");
        dialog.setHeaderText("Total: " + String.format("%.2f €", total));
        dialog.setContentText("Dinero entregado:");

        java.util.Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            try {
                // Leemos lo que ha escrito el usuario (cambiando coma por punto)
                double entregado = Double.parseDouble(result.get().replace(",", "."));

                if (entregado < total) {
                    mostrarMensaje("Dinero insuficiente. Faltan: " + String.format("%.2f €", total - entregado), true);
                } else {
                    double cambio = entregado - total;

                    mostrarMensaje("✅ ¡COBRADO! Devolver cambio: " + String.format("%.2f €", cambio), false);

                    // Mostramos el cambio
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Cambio");
                    info.setHeaderText("Operación realizada");
                    info.setContentText("Devolver al cliente: " + String.format("%.2f €", cambio));
                    info.showAndWait();

                    // Cerramos la venta
                    cobrarDirectamente(total, "Efectivo");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Introduce un número válido.", true);
            }
        } else {
            mostrarMensaje("Operación cancelada.", true);
        }
    }

    private void cobrarDirectamente(double total, String metodoPago) {
        guardarEnHistorialCaja(total, metodoPago);

        productosCuenta.clear();

        File file = new File(ficheroMesaActual);

        if (file.exists()) {
            file.delete();
        }

        actualizarTotal();
        mostrarMensaje("Cobro con " + metodoPago.toUpperCase() + " realizado correctamente.", false);
    }

    private void guardarEnHistorialCaja(double total, String metodoPago) {

        String ficheroCaja = "src/Restaurante/archivos_restaurante/cajaDia.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroCaja, true));
            bw.write("Mesa " + PantallaPrincipalController.numeroDeMesaActual + ", " +  total + "," + metodoPago);
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarProducto(ActionEvent event) {

        Producto productoSeleccionado = tablaComanda.getSelectionModel().getSelectedItem();

        if (productoSeleccionado == null) {
            mostrarMensaje("Selecciona un producto de la lista para eliminar.", true);
            return;
        }

        if (productoSeleccionado.getCantidad() > 1) {
            productoSeleccionado.setCantidad(productoSeleccionado.getCantidad() - 1);
            mostrarMensaje("Eliminado 1 unidad de " + productoSeleccionado.getNombre(), true);
        } else {
            productosCuenta.remove(productoSeleccionado);
            mostrarMensaje("Eliminado: " + productoSeleccionado.getNombre(), false);
        }

        tablaComanda.refresh();
        actualizarTotal();
        guardarComandaEnFichero();
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

    private void mostrarMensaje(String texto, boolean esError) {
        labelMensaje.setText(texto);

        if (esError) {
            // Errores
            labelMensaje.setStyle("-fx-text-fill: #D32F2F; -fx-font-weight: bold;");
        } else {
            // Confirmaciones
            labelMensaje.setStyle("-fx-text-fill: #388E3C; -fx-font-weight: bold;");
        }
    }
}
