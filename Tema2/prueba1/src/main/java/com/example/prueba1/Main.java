package com.example.prueba1;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/home/adrian/Documentos/2DAM/Desarrollo de Interfaces/Tema2/prueba1/src/main/resources/com/example/prueba1/vistaejemplo.fxml"));

        // Cargo el scene

        Scene scene = new Scene(root);

        primaryStage.setTitle("Hola mundo");
        // Seteo la scene y la muestro
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
