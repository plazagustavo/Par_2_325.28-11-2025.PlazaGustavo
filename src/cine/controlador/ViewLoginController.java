package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewLoginController {
    
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField contraseñaField;
    
    private Cine cine;
    private Stage stage;
    
    public void setCine(Cine cine) {
        this.cine = cine;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void btnLogin() {
        String email = emailField.getText().trim();
        String contraseña = contraseñaField.getText();
        
        // Validar que los campos no estén vacíos
        if (email.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta("Error", "Completa todos los campos");
            return;
        }
        
        // Validar login
        Cliente cliente = cine.validarLogin(email, contraseña);
        
        if (cliente != null) {
            abrirPrincipal(cliente);
        } else {
            mostrarAlerta("Error", "Email o contraseña incorrectos");
            contraseñaField.clear();
        }
    }
    
    @FXML
    private void btnRegistro() {
        String email = emailField.getText().trim();
        String contraseña = contraseñaField.getText();
        
        // Validar que los campos no estén vacíos
        if (email.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta("Error", "Completa todos los campos");
            return;
        }
        
        // Verificar si el cliente ya existe
        if (cine.existeCliente(email)) {
            mostrarAlerta("Error", "El email ya está registrado");
            return;
        }
        
        // Crear y registrar nuevo cliente
        Cliente nuevoCliente = new Cliente("Cliente", email, contraseña);
        cine.registrarCliente(nuevoCliente);
        
        mostrarAlerta("Éxito", "Registrado correctamente");
        
        // Limpiar los campos
        emailField.clear();
        contraseñaField.clear();
    }
    
    private void abrirPrincipal(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cine/vista/ViewPrincipal.fxml"));
            Parent root = loader.load();
            
            ViewPrincipalController controlador = loader.getController();
            
            controlador.setCine(cine);
            controlador.setCliente(cliente);
            controlador.setStage(stage);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}