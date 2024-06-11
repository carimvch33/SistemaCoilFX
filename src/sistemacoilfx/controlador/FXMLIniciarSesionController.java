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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemacoilfx.modelo.dao.AutenticacionDAO;
import sistemacoilfx.modelo.pojo.Coordinador;
import sistemacoilfx.modelo.pojo.ProfesorUV;
import sistemacoilfx.modelo.pojo.RespuestaLoginCoordinador;
import sistemacoilfx.modelo.pojo.RespuestaLoginProfesorUV;
import sistemacoilfx.utilidades.Constantes;
import sistemacoilfx.utilidades.Utils;

public class FXMLIniciarSesionController implements Initializable {
    
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnIniciarSesion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnClicIniciarSesion(ActionEvent event) {
        String numPersonal = tfUsuario.getText();
        String password = tfPassword.getText();
        if(verificarUsuario(numPersonal)){
            if(validarCampos(numPersonal, password)){
                RespuestaLoginCoordinador respuesta = AutenticacionDAO.iniciarSesionCoordinador(numPersonal, password);
                if(!respuesta.getError()){
                    Utils.mostrarAlertaSimple("Bienvenido(a)", "Bienvenido coordinador(a) "
                        +respuesta.getCoordinador().getNombre()+" "
                        +respuesta.getCoordinador().getApellidoPaterno()+" "
                        +respuesta.getCoordinador().getApellidoMaterno()+" al sistema.",Alert.AlertType.INFORMATION);
                    irPantallaPrincipalCoordinador(respuesta.getCoordinador());
                }else{
                    Utils.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.WARNING);
                }
            }
        }else{
            if(validarCampos(numPersonal, password)){
                RespuestaLoginProfesorUV respuesta = AutenticacionDAO.iniciarSesionProfesorUV(numPersonal, password);
                if(!respuesta.getError()){
                    Utils.mostrarAlertaSimple("Bienvenido(a)", "Bienvenido profesor(a) "
                        +respuesta.getProfesorUV().getNombre()+" "
                        +respuesta.getProfesorUV().getApellidoPaterno()+" "
                        +respuesta.getProfesorUV().getApellidoMaterno()+" al sistema.",Alert.AlertType.INFORMATION);
                    irPantallaPrincipalProfesorUV(respuesta.getProfesorUV());
                }else{
                    Utils.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.WARNING);
                }
            }
        }
    }
    
    private boolean validarCampos(String numPersonal, String password){
        if(!numPersonal.isEmpty() && !password.isEmpty()){
            return true;
        }else{
            Utils.mostrarAlertaSimple("Error", "Por favor, llene todos los campos para iniciar sesión.", Alert.AlertType.WARNING);
            return false;
        }
    }
    
    private boolean verificarUsuario(String numPersonal){
        if(numPersonal.toLowerCase().contains(Constantes.ES_COORDINADOR)){
            return true;
        }else{
            return false;
        }
    }
    
    private void irPantallaPrincipalProfesorUV(ProfesorUV profesorUV){
        try {
            Stage escenarioPrincipal = (Stage) btnIniciarSesion.getScene().getWindow();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLPrincipalProfesorUV.fxml");
            Parent root = loader.load();
            FXMLPrincipalProfesorUVController controlador = loader.getController();
            controlador.inicializarValores(profesorUV);
            controlador.inicializarValoresAdministrador(profesorUV.getIdProfesorUV());
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setTitle("Menú principal");
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.show();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
    private void irPantallaPrincipalCoordinador(Coordinador coordinador){
        try {
            Stage escenarioPrincipal = (Stage) btnIniciarSesion.getScene().getWindow();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLPrincipalCoordinador.fxml");
            Parent root = loader.load();
            FXMLPrincipalCoordinadorController controlador = loader.getController();
            controlador.inicializarValores(coordinador);
            Scene escenaPrincipal = new Scene(root);
            escenarioPrincipal.setTitle("Menú principal");
            escenarioPrincipal.setScene(escenaPrincipal);
            escenarioPrincipal.show();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
}