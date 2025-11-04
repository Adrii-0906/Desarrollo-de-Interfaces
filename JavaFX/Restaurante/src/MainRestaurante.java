import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainRestaurante extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/Restaurante/vistas/pantallaPrincipal.fxml"));

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
