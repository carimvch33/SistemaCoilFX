package sistemacoil.controlador;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
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
import sistemacoil.modelo.dao.CatalogoDAO;
import sistemacoil.modelo.dao.ModuloUniversitarioDAO;
import sistemacoil.modelo.dao.OfertaColaboracionUVDAO;
import sistemacoil.modelo.pojo.AreaAcademica;
import sistemacoil.modelo.pojo.Dependencia;
import sistemacoil.modelo.pojo.ExperienciaEducativa;
import sistemacoil.modelo.pojo.Idioma;
import sistemacoil.modelo.pojo.OfertaColaboracionUV;
import sistemacoil.modelo.pojo.Periodo;
import sistemacoil.modelo.pojo.ProgramaEducativo;
import sistemacoil.observador.ObservadorOfertaColaboracionUV;
import sistemacoil.utilidades.Animaciones;
import sistemacoil.utilidades.Constantes;
import sistemacoil.utilidades.Utils;

public class FXMLFormularioOfertaColaboracionUVController implements Initializable {
    
    private OfertaColaboracionUV ofertaEdicion;
    private int idProfesorUV;
    private ObservadorOfertaColaboracionUV observador;
    private ObservableList<Idioma> idiomas;
    private ObservableList<Periodo> periodos;
    private ObservableList<AreaAcademica> areasAcademicas;
    private ObservableList<Dependencia> dependencias;
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<ExperienciaEducativa> experienciasEducativas;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lbTituloFormulario;
    @FXML
    private TextField tfNombreCursoCV;
    @FXML
    private TextArea taObjetivoCurso;
    @FXML
    private TextArea taPerfilEstudiantes;
    @FXML
    private TextArea taTemasInteres;
    @FXML
    private TextArea taInformacionAdicional;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorIdioma;
    @FXML
    private Label lbErrorAreaAcademica;
    @FXML
    private Label lbErrorPrograma;
    @FXML
    private ComboBox<ProgramaEducativo> cbProgramasEducativos;
    @FXML
    private Label lbErrorExperienciaEducativa;
    @FXML
    private Label lbErrorObjetivo;
    @FXML
    private Label lbErrorPerfil;
    @FXML
    private Label lbErrorTemas;
    @FXML
    private Label lbErrorTemas1;
    @FXML
    private ComboBox<Dependencia> cbDependencias;
    @FXML
    private ComboBox<Idioma> cbIdiomas;
    @FXML
    private ComboBox<Periodo> cbPeriodos;
    @FXML
    private Label lbErrorPeriodo;
    @FXML
    private Label lbErrorDependencia;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciasEducativas;
    @FXML
    private ComboBox<AreaAcademica> cbAreasAcademicas;
    @FXML
    private Button btnRegistrarOferta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarIdiomas();
        cargarAreasAcademicas();
        cargarPeriodos();
        inicializarValores(ofertaEdicion, idProfesorUV, observador);
        configurarListeners();
    }
    
    public void inicializarValores(OfertaColaboracionUV ofertaEdicion, int idProfesorUV, ObservadorOfertaColaboracionUV observador){
        this.ofertaEdicion = ofertaEdicion;
        this.idProfesorUV = idProfesorUV;
        this.observador = observador;
        System.out.println("Oferta Colaboración UV: " + ((ofertaEdicion != null) ? ofertaEdicion.getNombre() : "NUEVA"));
        if(this.ofertaEdicion != null){
            lbTituloFormulario.setText(ofertaEdicion.getNombre());
            btnRegistrarOferta.setText("Guardar cambios");
            //btnRegistrarOferta.setLayoutX(561);
            cargarInformacionOfertaEdicion(this.ofertaEdicion);
        }
    }
    
    public void configurarListeners() {
        Utils.agregarListenerSimple(tfNombreCursoCV, Constantes.MAX_LONG_NOMBRE_COLAB);
        
        cbAreasAcademicas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarDependencias(newValue.getIdAreaAcademica());
                cbDependencias.setDisable(false);
            } else
                cbDependencias.setDisable(true);
        });

        cbDependencias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarProgramasEducativos(newValue.getIdDependencia());
                cbProgramasEducativos.setDisable(false);
            } else
                cbProgramasEducativos.setDisable(true);
        });
        
        cbProgramasEducativos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarExperienciasEducativas(newValue.getIdProgramaEducativo());
                cbExperienciasEducativas.setDisable(false);
            } else
                cbExperienciasEducativas.setDisable(true);
        });
    } 
    
    private void cargarPeriodos() {
        periodos = FXCollections.observableArrayList();
        List<Periodo> periodosBD = ModuloUniversitarioDAO.obtenerPeriodos();
        periodos.addAll(periodosBD);
        cbPeriodos.setItems(periodos);
    }
    
    private void cargarIdiomas() {
        idiomas = FXCollections.observableArrayList();
        List<Idioma> idiomasBD = CatalogoDAO.obtenerIdiomas();
        idiomas.addAll(idiomasBD);
        cbIdiomas.setItems(idiomas);
    }
    
    private void cargarAreasAcademicas() {
        areasAcademicas = FXCollections.observableArrayList();
        List<AreaAcademica> areasAcademicasBD = ModuloUniversitarioDAO.obtenerAreasAcademicas();
        areasAcademicas.addAll(areasAcademicasBD);
        cbAreasAcademicas.setItems(areasAcademicas);
    }
    
    private void cargarDependencias(int idAreaAcademica) {
        dependencias = FXCollections.observableArrayList();
        List<Dependencia> dependenciasBD = ModuloUniversitarioDAO.obtenerDependenciasPorAreaAcademica(idAreaAcademica);
        dependencias.addAll(dependenciasBD);
        cbDependencias.setItems(dependencias);
    }
    
    private void cargarProgramasEducativos(int idDependencia) {
        programasEducativos = FXCollections.observableArrayList();
        List<ProgramaEducativo> programasEducativosBD = ModuloUniversitarioDAO.obtenerProgramasEducativosPorDependencia(idDependencia);
        programasEducativos.addAll(programasEducativosBD);
        cbProgramasEducativos.setItems(programasEducativos);
    }
    
    private void cargarExperienciasEducativas(int idProgramaEducativo) {
        experienciasEducativas = FXCollections.observableArrayList();
        List<ExperienciaEducativa> experienciasEducativasBD = ModuloUniversitarioDAO.obtenerExperienciasPorProgramaEducativo(idProgramaEducativo);
        experienciasEducativas.addAll(experienciasEducativasBD);
        cbExperienciasEducativas.setItems(experienciasEducativas);
    }

    private void btnClicRegistrar(ActionEvent event) {
        if(validarCampos() == true){
            OfertaColaboracionUV ofertaColaboracionUV = obtenerInformacionOfertaColaboracionUV();
            if(ofertaEdicion == null){
                registrarOfertaColaboracionUV(ofertaColaboracionUV);
            }else{
                ofertaEdicion = obtenerInformacionOfertaColaboracionUV();
                modificarOfertaColaboracionUV(ofertaEdicion);
            }
        }
    }
    
    private int buscarIdPeriodo(int idPeriodo) {
        for(int i = 0; i < periodos.size(); i++){
            if(periodos.get(i).getIdPeriodo()== idPeriodo)
                return i;
        }
        return 0;
    }
    
    private int buscarIdIdioma(int idIdioma){
        for(int i = 0; i < idiomas.size(); i++){
            if(idiomas.get(i).getIdIdioma() == idIdioma)
                return i;
        }
        return 0;
    }
    
    private int buscarIdAreaAcademica(int idAreaAcademica){
        for(int i = 0; i < areasAcademicas.size(); i++){
            if(areasAcademicas.get(i).getIdAreaAcademica() == idAreaAcademica)
                return i;
        }
        return 0;
    }
    
    private int buscarIdDependencia(int idDependencia){
        for(int i = 0; i < dependencias.size(); i++){
            if(dependencias.get(i).getIdDependencia()== idDependencia)
                return i;
        }
        return 0;
    }
    
    private int buscarIdExperienciaEducativa(int idExperienciaEducativa){
        for(int i = 0; i < experienciasEducativas.size(); i++){
            if(experienciasEducativas.get(i).getIdExperienciaEducativa() == idExperienciaEducativa)
                return i;
        }
        return 0;
    }
    
    private void cargarInformacionOfertaEdicion(OfertaColaboracionUV ofertaColaboracionUV){
        tfNombreCursoCV.setText(ofertaColaboracionUV.getNombre());
        taObjetivoCurso.setText(ofertaColaboracionUV.getObjetivoCurso());
        taPerfilEstudiantes.setText(ofertaColaboracionUV.getPerfilEstudiante());
        taTemasInteres.setText(ofertaColaboracionUV.getTemaInteres());
        taInformacionAdicional.setText(ofertaColaboracionUV.getInformacionAdicional());
        cbIdiomas.getSelectionModel().select(buscarIdIdioma(ofertaColaboracionUV.getIdIdioma()));
        cbAreasAcademicas.getSelectionModel().select(buscarIdAreaAcademica(ofertaColaboracionUV.getIdAreaAcademica()));
        cbDependencias.getSelectionModel().select(buscarIdDependencia(ofertaColaboracionUV.getIdDependencia()));
        cbPeriodos.getSelectionModel().select(buscarIdPeriodo(ofertaColaboracionUV.getIdPeriodo()));
    }
    
    private OfertaColaboracionUV obtenerInformacionOfertaColaboracionUV(){
        OfertaColaboracionUV ofertaColaboracionUV = new OfertaColaboracionUV();
        ofertaColaboracionUV.setNombre(tfNombreCursoCV.getText());
        ofertaColaboracionUV.setObjetivoCurso(taObjetivoCurso.getText());
        ofertaColaboracionUV.setPerfilEstudiante(taPerfilEstudiantes.getText());
        ofertaColaboracionUV.setTemaInteres(taTemasInteres.getText());
        ofertaColaboracionUV.setInformacionAdicional(taInformacionAdicional.getText());
        ofertaColaboracionUV.setIdIdioma(cbIdiomas.getSelectionModel().getSelectedItem().getIdIdioma());
        ofertaColaboracionUV.setIdAreaAcademica(cbAreasAcademicas.getSelectionModel().getSelectedItem().getIdAreaAcademica());
        ofertaColaboracionUV.setIdDependencia(cbDependencias.getSelectionModel().getSelectedItem().getIdDependencia());
        ofertaColaboracionUV.setIdPeriodo(cbPeriodos.getSelectionModel().getSelectedItem().getIdPeriodo());
        ofertaColaboracionUV.setIdProfesorUV(this.idProfesorUV);
        if(ofertaEdicion != null){
            ofertaColaboracionUV.setIdOfertaColaboracionUV(ofertaEdicion.getIdOfertaColaboracionUV());
        }
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
    
    private void modificarOfertaColaboracionUV(OfertaColaboracionUV ofertaColaboracionUV){
        HashMap<String, Object> respuesta = OfertaColaboracionUVDAO.modificarOfertaColaboracionUV(ofertaColaboracionUV);
        if(!(boolean)respuesta.get(Constantes.KEY_ERROR)){
            Utils.mostrarAlertaSimple("Oferta modificada", "Los datos de la oferta "+ofertaColaboracionUV.getNombre()+" han sido modificados correctamente.", Alert.AlertType.INFORMATION);
            observador.operacionExitosa("Modificación", ofertaColaboracionUV.getNombre());
            cerrarVentana();
        }else{
            Utils.mostrarAlertaSimple("Error al actualizar", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }

    @FXML
    private void btnClicRegistrarOferta(ActionEvent event) {
        Animaciones.animarPresionBoton(btnRegistrarOferta);
        if(validarCampos() == true){
            OfertaColaboracionUV ofertaColaboracionUV = obtenerInformacionOfertaColaboracionUV();
            if(ofertaEdicion == null)
                registrarOfertaColaboracionUV(ofertaColaboracionUV);
            else{
                ofertaEdicion = obtenerInformacionOfertaColaboracionUV();
                modificarOfertaColaboracionUV(ofertaEdicion);
            }
        }
    }
    
    @FXML
    private void btnClicCancelar(ActionEvent event) {
        cerrarVentana();
    }
}