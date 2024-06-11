package sistemacoilfx.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sistemacoilfx.modelo.pojo.Coordinador;
import sistemacoilfx.utilidades.Utils;

public class FXMLPrincipalCoordinadorController implements Initializable {
    
    private Coordinador coordinador;

    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Label lbNombreCoordinador;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private Button btnConsultarEvidenciasDeColaboracion;
    @FXML
    private Button btnConsultarNumeraliaAreaAcademica;
    @FXML
    private Button btnConsultarNumeraliaRegion;
    @FXML
    private Button btnRegistrarOfertaExterna;
    @FXML
    private Button btnRevisarRegistroColaboracion;
    @FXML
    private Button btnConsultarHistorialColaboraciones;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarValores(Coordinador coordinador) {
        this.coordinador = coordinador;
        lbNombreCoordinador.setText(this.coordinador.getApellidoPaterno().toUpperCase()+"-"+this.coordinador.getApellidoMaterno().toUpperCase()+" "+this.coordinador.getNombre().toUpperCase());
        lbNumPersonal.setText(this.coordinador.getNumPersonal());
        //configurarBotonesYEtiquetas();
        
        Utils.colocarImagenEnBoton(btnCerrarSesion, Utils.obtenerRutaImagen("logoutIconCoordi.png"), 35, 35);        
        Utils.colocarImagenEnBoton(btnConsultarEvidenciasDeColaboracion, Utils.obtenerRutaImagen("consultEv.png"), 90, 90);
        Utils.colocarImagenEnBoton(btnConsultarNumeraliaAreaAcademica, Utils.obtenerRutaImagen("numeraliaAAcademica.png"), 90, 90);
        Utils.colocarImagenEnBoton(btnConsultarNumeraliaRegion, Utils.obtenerRutaImagen("numeraliaRegion.png"), 90, 90);
        Utils.colocarImagenEnBoton(btnRegistrarOfertaExterna, Utils.obtenerRutaImagen("registrarColabEx.png"), 90, 90);
        Utils.colocarImagenEnBoton(btnRevisarRegistroColaboracion, Utils.obtenerRutaImagen("revisarOferta.png"), 90, 90);
        Utils.colocarImagenEnBoton(btnConsultarHistorialColaboraciones, Utils.obtenerRutaImagen("consultarHistorial.png"), 90, 90);
    }

    @FXML
    private void btnClicCerrarSesion(ActionEvent event) {
        try {
            Stage escenarioPrincipal = (Stage) btnCerrarSesion.getScene().getWindow();
            Parent root = FXMLLoader.load(sistemacoilfx.SistemaCoilFX.class.getResource("vista/FXMLIniciarSesion.fxml"));
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setTitle("Iniciar sesión");
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.close();
            escenarioPrincipal.show();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }

    @FXML
    private void btnClicConsultarEvidenciasDeColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnClicConsultarNumeraliaAreaAcademica(ActionEvent event) {
    }

    @FXML
    private void btnClicConsultarNumeraliaRegion(ActionEvent event) {
    }

    @FXML
    private void btnClicRegistrarOfertaExterna(ActionEvent event) {
    }

    @FXML
    private void btnClicRevisarRegistroColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnClicConsultarHistorialColaboraciones(ActionEvent event) {
    }
}