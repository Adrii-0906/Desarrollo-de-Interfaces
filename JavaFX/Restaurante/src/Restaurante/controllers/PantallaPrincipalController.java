package Restaurante.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaPrincipalController {

    @FXML
    private ImageView mesa1;
    @FXML
    private ImageView mesa2;
    @FXML
    private ImageView mesa3;
    @FXML
    private ImageView mesa4;
    @FXML
    private ImageView mesa5;
    @FXML
    private ImageView mesa6;
    @FXML
    private ImageView mesa7;
    @FXML
    private ImageView mesa8;
    @FXML
    private ImageView mesa9;

    @FXML
    private Label btnInventario;
    @FXML
    private Label btnCaja;


    private void cambiarPantalla(MouseEvent event, String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void irAMesa1(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 1");
    }
    @FXML
    public void irAMesa2(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 2");
    }
    @FXML
    public void irAMesa3(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 3");
    }
    @FXML
    public void irAMesa4(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 4");
    }
    @FXML
    public void irAMesa5(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 5");
    }
    @FXML
    public void irAMesa6(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 6");
    }
    @FXML
    public void irAMesa7(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 7");
    }
    @FXML
    public void irAMesa8(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 8");
    }
    @FXML
    public void irAMesa9(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/comanda1.fxml", "Comanda Mesa 9");
    }

    public void irAInventario(MouseEvent event) {
        cambiarPantalla(event, "/Restaurante/vistas/inventario.fxml", "Iventario Restaurante");
    }

    public void irACaja(MouseEvent event) {
        cambiarPantalla(event, "", "Caja Restaurante");
    }
}
