/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author adrian
 */
public class CalculadoraController implements Initializable {
    
    private double primerNum;
    private double segundoNum;
    private String operador;

    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton0;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;
    @FXML
    private Button boton6;
    @FXML
    private Button botonRestar;
    @FXML
    private Button boton7;
    @FXML
    private Button boton8;
    @FXML
    private Button boton9;
    @FXML
    private Button botonSumar;
    @FXML
    private Button botonBorrar;
    @FXML
    private Button botonMultiplicar;
    @FXML
    private Button botonDividir;
    @FXML
    private Button botonResultado;
    @FXML
    private AnchorPane Anchor1;
    @FXML
    private AnchorPane Anchor2;
    @FXML
    private AnchorPane Anchor3;
    @FXML
    private TextField text1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void escribirUno(ActionEvent event) {
        text1.setText(text1.getText() + "1");
    }

    @FXML
    private void escribirDos(ActionEvent event) {
        text1.setText(text1.getText() + "2");
    }

    @FXML
    private void escribirTres(ActionEvent event) {
        text1.setText(text1.getText() + "3");
    }

    @FXML
    private void escribirCero(ActionEvent event) {
        text1.setText(text1.getText() + "0");
    }

    @FXML
    private void escribirCuatro(ActionEvent event) {
        text1.setText(text1.getText() + "4");
    }

    @FXML
    private void escribirCinco(ActionEvent event) {
        text1.setText(text1.getText() + "5");
    }

    @FXML
    private void escribirSeis(ActionEvent event) {
        text1.setText(text1.getText() + "6");
    }

    @FXML
    private void escribirMenos(ActionEvent event) {
        this.primerNum = Double.parseDouble(this.text1.getText());
        this.operador = "-";
        this.text1.setText("");
    }

    @FXML
    private void escribirSiete(ActionEvent event) {
        text1.setText(text1.getText() + "7");
    }

    @FXML
    private void escribirOcho(ActionEvent event) {
        text1.setText(text1.getText() + "8");
    }

    @FXML
    private void escribirNueve(ActionEvent event) {
        text1.setText(text1.getText() + "9");
    }

    @FXML
    private void escribirMas(ActionEvent event) {
        this.primerNum = Double.parseDouble(this.text1.getText());
        this.operador = "+";
        this.text1.setText("");
    }

    @FXML
    private void borrar(ActionEvent event) {
        text1.setText("");
    }

    @FXML
    private void escrbirMultiplicar(ActionEvent event) {
        this.primerNum = Double.parseDouble(this.text1.getText());
        this.operador = "*";
        this.text1.setText("");
    }

    @FXML
    private void escribirDividir(ActionEvent event) {
        this.primerNum = Double.parseDouble(this.text1.getText());
        this.operador = "/";
        this.text1.setText("");
    }

    @FXML
    private void escrbirResultado(ActionEvent event) {
        this.segundoNum = Double.parseDouble(this.text1.getText());
        
        switch(this.operador) {
            case "+": this.text1.setText(Double.toString(this.primerNum + this.segundoNum));break;
            case "-": this.text1.setText(Double.toString(this.primerNum - this.segundoNum));break;
            case "*": this.text1.setText(Double.toString(this.primerNum * this.segundoNum));break;
            case "/": this.text1.setText(Double.toString(this.primerNum / this.segundoNum));break;
        }
        
       
        
    }
    
    
}
