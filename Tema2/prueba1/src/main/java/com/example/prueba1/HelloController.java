package com.example.prueba1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label text1;
    @FXML
    private Button button1;

    @FXML
    protected void onHelloButtonClick() {
        text1.setText("Esto va a cambiar");
    }
}