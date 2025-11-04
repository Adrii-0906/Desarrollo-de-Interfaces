/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package navegador_automovil;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author adrian
 */
public class NavegadorAutomovil extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{

            Parent root = FXMLLoader.load(getClass().getResource("/vista/pantallaPrincipal.fxml"));

            // Cargo el scene
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Pantalla Principal");
            // Seteo la scene y la muestro
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }    
    
}
