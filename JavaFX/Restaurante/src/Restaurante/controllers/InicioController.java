package Restaurante.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class InicioController {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnAcceder;
    @FXML
    private Label labelError;


    private static final String ficheroUsuarios = "src/Restaurante/archivos_restaurante/usuarios.txt";
    private static final String restaurante = "/Restaurante/vistas/pantallaPrincipal.fxml";
    private static final String pub = "/Restaurante/vistas/pantallaPrincipalPub.fxml";

    @FXML
    public void accederARestaurante() {
        String user = usuario.getText();
        String contrasena = password.getText();

        if (validarUsuariosEnFichero(user, contrasena)) {
            if(labelError != null) {
                labelError.setVisible(false);
            }
            accederSegunHora();
        } else {
            labelError.setVisible(true);
            labelError.setText("Usuario o contraseña incorrectos");
        }
    }

    private boolean validarUsuariosEnFichero(String usuario, String constrasena) {
        File archivo = new File(ficheroUsuarios);

        if (!archivo.exists()) {
            return false;
        }

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {

                String linea = scanner.nextLine();

                String[] partes = linea.split(",");

                if (partes.length == 2) {
                    String usuarioGuardado = partes[0].trim();
                    String passwordGuardada = partes[1].trim();

                    if (usuarioGuardado.equals(usuario) && passwordGuardada.equals(constrasena)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public void accederSegunHora() {
        LocalTime horaActual = LocalTime.now();
        boolean noche = horaActual.isAfter(LocalTime.MIDNIGHT) && horaActual.isBefore(LocalTime.of(6, 0));

        if (noche) {
            cambiarEscena(pub, "PUB Casa Manolo");
        } else {
            cambiarEscena(restaurante, "Restaurante Casa Manolo");
        }
    }


    private void cambiarEscena(String fxmlPath, String title) {
        try {
            var url = getClass().getResource(fxmlPath);
            if (url == null) {
                System.out.println("NO SE ENCUENTRA EL FXML: " + fxmlPath);
                return;
            }

            Parent root = FXMLLoader.load(url);

            // TRUCO: Obtenemos la ventana a través del botón 'btnAcceder',
            // en lugar de usar el 'event'.
            Stage stage = (Stage) btnAcceder.getScene().getWindow();

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
