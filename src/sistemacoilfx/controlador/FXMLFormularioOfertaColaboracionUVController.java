package sistemacoilfx.controlador;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemacoilfx.modelo.dao.CatalogoDAO;
import sistemacoilfx.modelo.dao.OfertaColaboracionUVDAO;
import sistemacoilfx.modelo.pojo.AreaAcademica;
import sistemacoilfx.modelo.pojo.Dependencia;
import sistemacoilfx.modelo.pojo.Idioma;
import sistemacoilfx.modelo.pojo.OfertaColaboracionUV;
import sistemacoilfx.modelo.pojo.Periodo;
import sistemacoilfx.observador.ObservadorOfertaColaboracionUV;
import sistemacoilfx.utilidades.Constantes;
import sistemacoilfx.utilidades.Utils;

public class FXMLFormularioOfertaColaboracionUVController implements Initializable {
    
    private OfertaColaboracionUV ofertaColaboracionUVEdicion;
    private int idProfesorUV;
    private ObservadorOfertaColaboracionUV observador;
    ObservableList<Periodo> periodos;
    ObservableList<Idioma> idiomas;
    ObservableList<AreaAcademica> areasAcademicas;
    ObservableList<Dependencia> dependencias;

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<Periodo> cbPeriodo;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private ComboBox<AreaAcademica> cbAreaAcademica;
    @FXML
    private ComboBox<Dependencia> cbDependencia;
    @FXML
    private Label lbTituloFormulario;
    @FXML
    private TextField tfNombreCursoCV;
    @FXML
    private TextField tfDisciplina;
    @FXML
    private TextArea taObjetivoCurso;
    @FXML
    private TextArea taPerfilEstudiantes;
    @FXML
    private TextArea taTemasInteres;
    @FXML
    private TextArea taInformacionAdicional;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarPeriodos();
        cargarIdiomas();
        cargarCargarAreasAcademicas();
        cargarDependencias();
    }
    
    public void inicializarValores(OfertaColaboracionUV ofertaColaboracionUVEdicion, int idProfesorUV, ObservadorOfertaColaboracionUV observador){
        this.ofertaColaboracionUVEdicion = ofertaColaboracionUVEdicion;
        this.idProfesorUV = idProfesorUV;
        this.observador = observador;
        System.out.println("Oferta Colaboración UV: " + ((ofertaColaboracionUVEdicion != null) ? ofertaColaboracionUVEdicion.getNombre() : "NUEVA"));
        if(this.ofertaColaboracionUVEdicion != null){
            lbTituloFormulario.setText("Modificar los datos de "+ofertaColaboracionUVEdicion.getNombre());
            //cargarInformacionPacienteEdicion(this.ofertaColaboracionUVEdicion);
        }
    }
    
    private void cargarPeriodos(){
        periodos = FXCollections.observableArrayList();
        periodos.addAll(CatalogoDAO.obtenerPeriodos());
        cbPeriodo.setItems(periodos);
    }
    
    private void cargarIdiomas(){
        idiomas = FXCollections.observableArrayList();
        idiomas.addAll(CatalogoDAO.obtenerIdiomas());
        cbIdioma.setItems(idiomas);
    }
    
    private void cargarCargarAreasAcademicas(){
        areasAcademicas = FXCollections.observableArrayList();
        areasAcademicas.addAll(CatalogoDAO.obtenerAreasAcademicas());
        cbAreaAcademica.setItems(areasAcademicas);
    }
    
    private void cargarDependencias(){
        dependencias = FXCollections.observableArrayList();
        dependencias.addAll(CatalogoDAO.obtenerDependencias());
        cbDependencia.setItems(dependencias);
    }

    @FXML
    private void btnClicRegistrar(ActionEvent event) {
        if(validarCampos() == true){
            OfertaColaboracionUV ofertaColaboracionUV = obtenerInformacionOfertaColaboracionUV();
            if(ofertaColaboracionUVEdicion == null){
                registrarOfertaColaboracionUV(ofertaColaboracionUV);
            }else{
                ofertaColaboracionUVEdicion = obtenerInformacionOfertaColaboracionUV();
                //actualizar
            }
        }
    }

    @FXML
    private void btnClicCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private OfertaColaboracionUV obtenerInformacionOfertaColaboracionUV(){
        OfertaColaboracionUV ofertaColaboracionUV = new OfertaColaboracionUV();
        ofertaColaboracionUV.setNombre(tfNombreCursoCV.getText());
        ofertaColaboracionUV.setDisciplina(tfDisciplina.getText());
        ofertaColaboracionUV.setObjetivoCurso(taObjetivoCurso.getText());
        ofertaColaboracionUV.setPerfilEstudiante(taPerfilEstudiantes.getText());
        ofertaColaboracionUV.setTemaInteres(taTemasInteres.getText());
        ofertaColaboracionUV.setInformacionAdicional(taInformacionAdicional.getText());
        ofertaColaboracionUV.setIdIdioma(cbIdioma.getSelectionModel().getSelectedItem().getIdIdioma());
        ofertaColaboracionUV.setIdAreaAcademica(cbAreaAcademica.getSelectionModel().getSelectedItem().getIdAreaAcademica());
        ofertaColaboracionUV.setIdDependencia(cbDependencia.getSelectionModel().getSelectedItem().getIdDependencia());
        ofertaColaboracionUV.setIdPeriodo(cbPeriodo.getSelectionModel().getSelectedItem().getIdPeriodo());
        ofertaColaboracionUV.setIdProfesorUV(this.idProfesorUV);
        return ofertaColaboracionUV;
    }
    
    private boolean validarCampos(){
        return true;
    }
    
    private void registrarOfertaColaboracionUV(OfertaColaboracionUV ofertaColaboracionUV){
        HashMap<String, Object> respuesta = OfertaColaboracionUVDAO.registrarOfertaColaboracionUV(ofertaColaboracionUV);
        if(!(boolean)respuesta.get(Constantes.KEY_ERROR)){
            Utils.mostrarAlertaSimple("Registro exitoso", "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.INFORMATION);
            observador.operacionExitosa("Alta", ofertaColaboracionUV.getNombre());
            cerrarVentana();
        }else{
            Utils.mostrarAlertaSimple("Error al registrar", "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }
}