package sistemacoil.utilidades;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author jafet
 */
public class Utils {
    public static void limpiarCampo(TextField textField, Label lbError) {
        textField.clear();
        lbError.setText("");
    }
    
    public static void limpiarComboBox(ComboBox<?> comboBox, Label lbError) {
        if (comboBox != null)
            comboBox.getSelectionModel().clearSelection();
        lbError.setText("");
    }
    
    public static void redirigirVentana(String rutaFXML, Pane apVentana) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = Utils.class.getResource(rutaFXML);
            loader.setLocation(fxmlUrl);
            AnchorPane nuevaVentanaPane = loader.load();
            apVentana.getChildren().setAll(nuevaVentanaPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void agregarListenerSimple(TextField textField, int longitudMaxima) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() <= longitudMaxima)
                    textField.setText(newValue.toUpperCase());
                else {
                    textField.setText(oldValue);
                    Animaciones.animarShake(textField);
                }
            }
        });
    }
    

    public static void agregarListenerNombres(TextField textField, int longitudMaxima) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() <= longitudMaxima || !newValue.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]*"))
                    textField.setText(newValue.toUpperCase());   
                else {
                    textField.setText(oldValue);
                    Animaciones.animarShake(textField);
                }
            }
        });
    }
   
    public static void agregarListenerCorreo(TextField textField, int longitudMaxima) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() <= longitudMaxima)
                textField.setText(newValue.toLowerCase());
            else {
                textField.setText(oldValue);
                Animaciones.animarShake(textField);
            }
        });
    }
    
    public static void agregarListenerTelefono(TextField textField, int longitudMaxima) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() <= longitudMaxima && newValue.matches("\\d*"))
                textField.setText(newValue);
            else {
                textField.setText(oldValue);
                Animaciones.animarShake(textField);
            }
        });
    }
    
    public static void mostrarAlertaSimple(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static boolean mostrarAlertaConfirmacion(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        // Agregar el estilo CSS al panel de diálogo de la alerta (ruta relativa)
        String rutaCSS = "sistemacoil/estilos/estilosDialogos.css";
        alerta.getDialogPane().getStylesheets().add(rutaCSS);

        Optional<ButtonType> botonSeleccionado = alerta.showAndWait();
        return (botonSeleccionado.get() == ButtonType.OK);
    }
}
