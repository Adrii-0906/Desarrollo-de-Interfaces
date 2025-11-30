package Restaurante.controllers;

import Restaurante.model.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CajaController implements Initializable {

    @FXML
    private TableView<Venta> tablaVentas;
    @FXML
    private TableColumn<Venta, String> colMesa;
    @FXML
    private TableColumn<Venta, Double> colImporte;
    @FXML
    private TableColumn<Venta, String> colMetodo;
    @FXML
    private TableColumn<Venta, String> colFecha;

    @FXML private Label lblTotalCaja;
    @FXML private Label lblTotalEfectivo;
    @FXML private Label lblTotalTarjeta;

    @FXML private DatePicker dpFecha;

    @FXML private ImageView btnSalir;

    private ObservableList<Venta> listaVentas;

    private String ficheroHistorial = "src/Restaurante/archivos_restaurante/cajaVenta.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colMesa.setCellValueFactory(new PropertyValueFactory<>("mesa"));
        colImporte.setCellValueFactory(new PropertyValueFactory<>("importe"));
        colMetodo.setCellValueFactory(new PropertyValueFactory<>("metodoPago"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        listaVentas = FXCollections.observableArrayList();
        tablaVentas.setItems(listaVentas);

        dpFecha.setValue(LocalDate.now());
        cargarVentasPorFecha(LocalDate.now());
    }


    @FXML
    public void buscarPorFecha(ActionEvent event) {
        LocalDate fechaSeleccionada = dpFecha.getValue();
        if (fechaSeleccionada != null) {
            cargarVentasPorFecha(fechaSeleccionada);
        }
    }

    private void cargarVentasPorFecha(LocalDate fecha) {
        listaVentas.clear();

        File file = new File(ficheroHistorial);
        if (!file.exists()) return;

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaString = fecha.format(formato);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");

                if (partes.length >= 4) {
                    String mesa = partes[0];
                    double importe = Double.parseDouble(partes[1]);
                    String metodo = partes[2];
                    String fechaVenta = partes[3];

                    if (fechaVenta.equals(fechaString)) {
                        listaVentas.add(new Venta(mesa, importe, metodo, fechaVenta));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        calcularTotales();
    }

    private void calcularTotales() {
        double totalGeneral = 0;
        double totalEfectivo = 0;
        double totalTarjeta = 0;

        for (Venta v : listaVentas) {
            totalGeneral += v.getImporte();

            if (v.getMetodoPago().equalsIgnoreCase("Efectivo")) {
                totalEfectivo += v.getImporte();
            } else {
                totalTarjeta += v.getImporte();
            }
        }

        lblTotalCaja.setText(String.format("%.2f €", totalGeneral));
        lblTotalEfectivo.setText(String.format("%.2f €", totalEfectivo));
        lblTotalTarjeta.setText(String.format("%.2f €", totalTarjeta));
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
        } catch (IOException e) { e.printStackTrace(); }
    }

}
