package cine.controlador;

import cine.modelo.Butaca;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Entrada;
import cine.modelo.Sala;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class ViewConfirmacionController {
    
    private static final double PRECIO_BUTACA_UNITARIO = 1000.00; 

    @FXML
    private Label lblPelicula;
    @FXML
    private Label lblSala;
    @FXML
    private Label lblButaca; 
    @FXML
    private Label lblPrecio; 

    private Cine cine;
    private Cliente cliente;
    private Sala sala;
    private List<Butaca> butacasSeleccionadas;
    private Stage stage;
    
    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public void setButacas(List<Butaca> butacas) {
        this.butacasSeleccionadas = butacas;
        mostrarDatos();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private void mostrarDatos() {
        // Calcular ubicaciones directamente sin streams
        String ubicaciones = "";
        for (Butaca butaca : butacasSeleccionadas) {
            ubicaciones += butaca.getUbicacion() + ", ";
        }
        // Eliminar la última coma
        if (ubicaciones.length() > 0) {
            ubicaciones = ubicaciones.substring(0, ubicaciones.length() - 2);
        }
        
        double precioTotal = butacasSeleccionadas.size() * PRECIO_BUTACA_UNITARIO;
        
        lblPelicula.setText(sala.getPelicula());
        lblSala.setText(String.valueOf(sala.getNumero()));
        lblButaca.setText(ubicaciones); 
        lblPrecio.setText("$" + String.format("%.2f", precioTotal)); 
    }

    @FXML
    private void btnConfirmar() {
        // Crear una entrada para cada butaca comprada
        for (Butaca butaca : butacasSeleccionadas) {
            butaca.setOcupada(true);
            Entrada entrada = new Entrada(cliente, sala, butaca, PRECIO_BUTACA_UNITARIO);
            cine.agregarEntrada(entrada);
        }
        
        double precioTotal = butacasSeleccionadas.size() * PRECIO_BUTACA_UNITARIO;
        String mensaje = "¡Compra exitosa de " + butacasSeleccionadas.size() 
                       + " butaca(s)!\nTotal: $" + String.format("%.2f", precioTotal);
        mostrarAlerta("Éxito", mensaje, AlertType.INFORMATION);
        
        if (stage != null) {
            stage.close();
        }
    }
    
    @FXML
    private void btnCancelar() {
        if (stage != null) {
            stage.close();
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
