package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Sala;
import cine.persistencia.PersistenciaDatos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

public class ViewPrincipalController {
    
    @FXML
    private Label clienteLabel;
    @FXML
    private ListView<Sala> salasListView;
    
    private Cine cine;
    private Cliente cliente;
    private Stage stage;
    
    public void setCine(Cine cine) {
        this.cine = cine;
        cargarSalas();
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        clienteLabel.setText("Bienvenido: " + cliente.getNombre());
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private void cargarSalas() {
        // Agregar todas las salas a la lista
        for (int i = 0; i < cine.getSalas().size(); i++) {
            Sala sala = cine.getSalas().get(i);
            salasListView.getItems().add(sala);
        }
    }
    
    @FXML
    private void btnComprar() {
        Sala salaSeleccionada = salasListView.getSelectionModel().getSelectedItem();
        
        // Validar que se haya seleccionado una sala
        if (salaSeleccionada == null) {
            mostrarAlerta("Error", "Selecciona una sala primero");
            return;
        }
        
        abrirSeleccionButacas(salaSeleccionada);
    }
    
    @FXML
    private void btnSalir() {
        // Guardar los datos antes de salir
        PersistenciaDatos.guardarCine(cine);
        
        if (stage != null) {
            stage.close();
        }
    }
    
    private void abrirSeleccionButacas(Sala sala) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cine/vista/ViewButacas.fxml"));
            Parent root = loader.load();
            
            ViewButacasController controlador = loader.getController();
            
            controlador.setCine(cine);
            controlador.setCliente(cliente);
            
            Stage ventana = new Stage();
            ventana.setTitle("Seleccionar Butacas - " + sala.getPelicula());
            ventana.setScene(new Scene(root, 800, 600));
            
            controlador.setStage(ventana);
            controlador.setSala(sala);
            
            ventana.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de butacas");
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