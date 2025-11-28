package cine;

import cine.modelo.Cine;
import cine.modelo.Sala;
import cine.persistencia.PersistenciaDatos;
import cine.controlador.ViewLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Intentar cargar el cine guardado
        Cine cine = PersistenciaDatos.cargarCine();

        // Si no existe, crear uno nuevo
        if (cine == null) {
            cine = crearCineInicial();
        }

        final Cine cineFinal = cine;

        // Cargar el archivo viewLogin
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/ViewLogin.fxml"));
        Parent root = loader.load();

        // Obtener el controlador y pasarle los datos
        ViewLoginController controller = loader.getController();
        controller.setCine(cineFinal);
        controller.setStage(primaryStage);

        // Crear la escena y mostrar la ventana
        Scene scene = new Scene(root);
        primaryStage.setTitle("Portal Cinemas");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Guardar el cine cuando se cierre la ventana
        primaryStage.setOnCloseRequest(event -> {
            PersistenciaDatos.guardarCine(cineFinal);
        });
    }
    
    private Cine crearCineInicial() {
        Cine cine = new Cine("Portal Cinemas");
        
        // Crear las salas con sus películas
        cine.agregarSala(new Sala(1, "Avatar 3"));
        cine.agregarSala(new Sala(2, "Oppenheimer"));
        cine.agregarSala(new Sala(3, "Barbie"));
        cine.agregarSala(new Sala(4, "Dune 2"));
        cine.agregarSala(new Sala(5, "Insidious"));
        cine.agregarSala(new Sala(6, "Cars"));
        
        return cine;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}