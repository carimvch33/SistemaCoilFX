package sistemacoilfx.utilidades;

import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Utils {
    
    public static void mostrarAlertaSimple(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    public static boolean mostrarAlertaConfirmacion(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        Optional<ButtonType> botonSeleccionado = alerta.showAndWait();
        return (botonSeleccionado.get() == ButtonType.OK);
    }
    
    public static FXMLLoader obtenerLoader(String ruta){
        return new FXMLLoader(sistemacoilfx.SistemaCoilFX.class.getResource(ruta));
    }
    
    public static String obtenerRutaImagen(String nombreImagen) {
        return Utils.class.getResource("/sistemacoilfx/resourses/"+nombreImagen).toExternalForm();
    }
    
    public static void colocarImagenEnBoton(Button boton, String rutaImagen, double ancho, double alto) {
        Image image = new Image(rutaImagen);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(ancho);
        imageView.setFitHeight(alto);
        boton.setGraphic(imageView);
    }
    
    public static void configurarBotones(Button[] botones, String[] nombreImagenes, double ancho, double alto) {
        for (int i = 0; i < botones.length; i++) {
            String rutaImagen = Utils.obtenerRutaImagen(nombreImagenes[i]);
            Utils.colocarImagenEnBoton(botones[i], rutaImagen, ancho, alto);
        }
    }
    
    public static void configurarEtiquetas(Label[] etiquetas, String[] nombreBotones) {
        for (int i = 0; i < etiquetas.length; i++) {
            etiquetas[i].setText(nombreBotones[i]);
        }
    }
}