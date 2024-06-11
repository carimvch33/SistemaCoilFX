package sistemacoilfx.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sistemacoilfx.modelo.pojo.OfertaColaboracionUV;
import sistemacoilfx.modelo.pojo.ProfesorUV;
import sistemacoilfx.observador.ObservadorOfertaColaboracionUV;
import sistemacoilfx.utilidades.Constantes;
import sistemacoilfx.utilidades.Utils;

public class FXMLPrincipalProfesorUVController implements Initializable, ObservadorOfertaColaboracionUV {
    
    private ProfesorUV profesorUV;
    private int idProfesorUV;
    private boolean estadoBoton = false;

    @FXML
    private Label lbNombreProfeUV;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnMenu;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private Label lbQueEsCOIL;
    @FXML
    private Pane paneMenu;
    @FXML
    private Button btnRegistrarOfertaUV;
    @FXML
    private Label lbRegistrarOfertaUV;
    @FXML
    private Button btnModificarOfertaUV;
    @FXML
    private Label lbModificarOfertaUV;
    @FXML
    private Button btnRegistrarColaboracion;
    @FXML
    private Label lbRegistrarColaboracion;
    @FXML
    private Button btnCancelarColaboracion;
    @FXML
    private Label lbCancelarColaboracion;
    @FXML
    private Button btnConcluirColaboracion;
    @FXML
    private Label lbConcluirColaboracion;
    @FXML
    private Button btnRegistrarProfeExterno;
    @FXML
    private Label lbRegistrarProfeExterno;
    @FXML
    private Button btnEditarProfeExterno;
    @FXML
    private Label lbEditarProfeExterno;
    @FXML
    private Button btnConsultarHistorialColaboraciones;
    @FXML
    private Label lbConsultarHistorialColaboraciones;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    public void inicializarValores(ProfesorUV profesorUV) {
        this.profesorUV = profesorUV;
        lbNombreProfeUV.setText(this.profesorUV.getNombre()+" "+this.profesorUV.getApellidoPaterno()+" "+this.profesorUV.getApellidoMaterno());
        lbNumPersonal.setText("Número de personal: "+this.profesorUV.getNumPersonal());
        lbQueEsCOIL.setText(Constantes.QUE_ES_COIL);
        configurarBotonesYEtiquetas();
    }
    
    public void inicializarValoresAdministrador(int idProfesorUV){
        this.idProfesorUV = idProfesorUV;
        //configurarBusquedaPaciente();
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
    private void btnClicMenu(ActionEvent event) {
        iniciarAnimacionMenu();
    }
    
    private void iniciarAnimacionMenu(){
        TranslateTransition translate = new TranslateTransition();
        if(estadoBoton == false){
            translate.setNode(paneMenu);
            translate.setDuration(Duration.millis(500));
            translate.setByX(172);
            translate.setAutoReverse(true);
            translate.play();
            estadoBoton = true;
        }else{
            translate.setNode(paneMenu);
            translate.setDuration(Duration.millis(500));
            translate.setByX(-172);
            translate.setAutoReverse(true);
            translate.play();
            estadoBoton = false;
        }
    }
    
    private void configurarBotonesYEtiquetas(){
        Button[] botones = {
            btnMenu, btnCerrarSesion, btnRegistrarOfertaUV, btnModificarOfertaUV,
            btnRegistrarColaboracion, btnCancelarColaboracion, btnConcluirColaboracion,
            btnRegistrarProfeExterno, btnEditarProfeExterno, btnConsultarHistorialColaboraciones
        };
        
        String[] nombreImagenes = {
            "menuIcon.png", "logoutIcon.png", "registerOffer.png", "editIcon.png",
            "registerColab.png", "cancelColab.png", "concludeColab.png",
            "addProfeEx.png", "editProfeEx.png", "myCollab.png"
        };
        
        Utils.configurarBotones(botones, nombreImagenes, 35, 35);
        
        Label[] etiquetas = {
            lbRegistrarOfertaUV, lbModificarOfertaUV, lbRegistrarColaboracion,
            lbCancelarColaboracion, lbConcluirColaboracion, lbRegistrarProfeExterno,
            lbEditarProfeExterno, lbConsultarHistorialColaboraciones
        };
        
        String[] nombreBotones = {
            "Registrar Oferta\nde Colaboración UV", "Modificar Oferta\nde Colaboración UV", 
            "Registrar\nColaboración", "Cancelar\nColaboración", 
            "Concluir\nColaboración", "Registrar\nProfesor Externo",
            "Editar\nProfesor Externo", "Mis\nColaboraciones"
        };
        
        Utils.configurarEtiquetas(etiquetas, nombreBotones);
    }
    
    @FXML
    private void btnClicRegistrarOfertaUV(ActionEvent event) {
        irFormularioOfertaColaboracionUV(null, idProfesorUV);
    }
    
    @FXML
    private void btnClicModificarOfertaUV(ActionEvent event) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLAdministradorOfertasColaboracionUV.fxml");
            Parent root = loader.load();
            FXMLAdministradorOfertasColaboracionUVController controlador = loader.getController();
            controlador.inicializarValores(profesorUV.getIdProfesorUV());
            Stage escenarioAdmin = new Stage();
            Scene escena = new Scene(root);
            escenarioAdmin.setTitle("Administrador de ofertas de colaboración UV");
            escenarioAdmin.setScene(escena);
            escenarioAdmin.initModality(Modality.APPLICATION_MODAL);
            escenarioAdmin.showAndWait();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista...");
        }
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnClicCancelarColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnClicConcluirColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnClicRegistrarOfertaExterna(ActionEvent event) {
    }

    @FXML
    private void btnClicEditarProfeExterno(ActionEvent event) {
    }

    @FXML
    private void btnClicConsultarHistorialColaboraciones(ActionEvent event) {
    }
    
    public void irFormularioOfertaColaboracionUV(OfertaColaboracionUV ofertaColaboracionUVEdicion, int idProfesorUV){
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("vista/FXMLFormularioOfertaColaboracionUV.fxml");
            Parent root = loader.load();
            FXMLFormularioOfertaColaboracionUVController controlador = loader.getController();
            controlador.inicializarValores(ofertaColaboracionUVEdicion, idProfesorUV, this);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Ofertas de Colaboración UV");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void operacionExitosa(String tipoOperacion, String nombreOfertaColaboracionUV) {
        System.out.println("Operación: " + tipoOperacion);
        System.out.println("Oferta de Colaboración UV: " + nombreOfertaColaboracionUV);
        //cargarDatosOfertasColaboracionUV();
    }
}