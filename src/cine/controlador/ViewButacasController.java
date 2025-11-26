package cine.controlador;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Sala;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewButacasController {
    @FXML
    private GridPane gridButacas;
    
    private Cine cine;
    private Cliente cliente;
    private Sala sala;
    private List<Butaca> butacasSeleccionadas = new ArrayList<>(); 
    private Stage stage;
    
    // Estilos de los botones
    private static final String DISPONIBLE = "-fx-background-color: #51CF66; -fx-text-fill: white; -fx-font-weight: bold;";
    private static final String SELECCIONADO = "-fx-background-color: #0000FF; -fx-text-fill: white; -fx-font-weight: bold;";
    private static final String OCUPADA = "-fx-background-color: #FF2400; -fx-text-fill: white; -fx-font-weight: bold;";
    
    public void setCine(Cine cine) {
        this.cine = cine;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
        dibujarButacas();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private void dibujarButacas() {
        gridButacas.setHgap(10);
        gridButacas.setVgap(10);
        gridButacas.setAlignment(Pos.CENTER);
        
        Butaca[][] butacas = sala.getButacas();
        for (int i = 0; i < sala.getFilas(); i++) {
            for (int j = 0; j < sala.getColumnas(); j++) {
                Butaca butaca = butacas[i][j];
                Button btn = crearBoton(butaca);
                gridButacas.add(btn, j, i);
            }
        }
    }
    
    private Button crearBoton(Butaca butaca) {
        Button btn = new Button(butaca.getUbicacion());
        btn.setPrefSize(50, 50);
        
        if (butaca.isOcupada()) {
            btn.setStyle(OCUPADA);
            btn.setDisable(true);
        } else {
            btn.setStyle(DISPONIBLE);
            btn.setOnAction(e -> seleccionarButaca(butaca, btn));
        }
        
        return btn;
    }
    
    private void seleccionarButaca(Butaca butaca, Button btn) {
        if (butacasSeleccionadas.contains(butaca)) {
            butacasSeleccionadas.remove(butaca);
            btn.setStyle(DISPONIBLE);
        } else {
            butacasSeleccionadas.add(butaca);
            btn.setStyle(SELECCIONADO);
        }
    }
    
    @FXML
    private void btnComprar() {
        if (butacasSeleccionadas.isEmpty()) { 
            mostrarAlerta("Error", "Selecciona al menos una butaca");
            return;
        }
        
        abrirConfirmacion();
    }
    
    private void abrirConfirmacion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cine/vista/ViewConfirmacion.fxml"));
            Parent root = loader.load();

            ViewConfirmacionController controlador = loader.getController();
            controlador.setCine(cine);
            controlador.setCliente(cliente);
            controlador.setSala(sala);
            controlador.setButacas(butacasSeleccionadas);

            Stage ventana = new Stage();
            ventana.setTitle("Confirmar Compra");
            ventana.setScene(new Scene(root, 500, 400));
            controlador.setStage(ventana);
            ventana.show();

            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la confirmación");
        }
    }
    
    @FXML
    private void btnCancelar() {
        stage.close();
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
