package controller;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

            Parent root = FXMLLoader.load(getClass().getResource("/vista/ventanaPrincipal.fxml"));

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
