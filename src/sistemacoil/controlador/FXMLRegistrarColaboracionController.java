package sistemacoil.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sistemacoil.modelo.dao.CatalogoDAO;
import sistemacoil.modelo.dao.ModuloUniversitarioDAO;
import sistemacoil.modelo.dao.ProfesorExternoDAO;
import sistemacoil.modelo.pojo.AreaAcademica;
import sistemacoil.modelo.pojo.Dependencia;
import sistemacoil.modelo.pojo.ExperienciaEducativa;
import sistemacoil.modelo.pojo.Idioma;
import sistemacoil.modelo.pojo.Periodo;
import sistemacoil.modelo.pojo.ProfesorExterno;
import sistemacoil.modelo.pojo.ProgramaEducativo;
import sistemacoil.utilidades.Constantes;
import sistemacoil.utilidades.Utils;

/**
 * FXML Controller class
 *
 * @author jafet
 */
public class FXMLRegistrarColaboracionController implements Initializable {
    
    private ObservableList<Idioma> idiomas;
    private ObservableList<Periodo> periodos;
    private ObservableList<AreaAcademica> areasAcademicas;
    private ObservableList<ProfesorExterno> profesoresExternos;
    private ObservableList<Dependencia> dependencias;
    private ObservableList<ProgramaEducativo> programasEducativos;
    private ObservableList<ExperienciaEducativa> experienciasEducativas;
    private ProfesorExterno profesorExterno;
    
    @FXML
    private TextField tfNombre;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private ComboBox<Idioma> cbIdiomas;
    @FXML
    private Label lbErrorIdioma;
    @FXML
    private ComboBox<Periodo> cbPeriodos;
    @FXML
    private Label lbErrorPeriodos;
    @FXML
    private ComboBox<ProfesorExterno> cbProfesoresExternos;
    @FXML
    private Button btRegistrarProfeExterno;
    @FXML
    private Label lbErrorProfesorExterno;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private Label lbErrorFechaInicio;
    @FXML
    private DatePicker dpFechaConclusion;
    @FXML
    private Label lbErrorFechaConclusion;
    @FXML
    private ComboBox<AreaAcademica> cbAreasAcademicas;
    @FXML
    private Label lbErrorAreaAcademica;
    @FXML
    private ComboBox<Dependencia> cbDependencias;
    @FXML
    private Label lbErrorDependencia;
    @FXML
    private ComboBox<ProgramaEducativo> cbProgramasEducativos;
    @FXML
    private Label lbErrorProgramaEducativo1;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciasEducativas;
    @FXML
    private Label lbErrorExperienciaEducativa;
    @FXML
    private TextArea taObjetivosCurso;
    @FXML
    private TextArea taPerfilEstudiantes;
    @FXML
    private TextArea taInfoAdicional;
    @FXML
    private TextArea taTemasInteres;
    @FXML
    private TextField tfProfesorUV;
    @FXML
    private Label lbErrorObjetivoCurso;
    @FXML
    private Label lbErrorPerfilEstudiantes;
    @FXML
    private Label lbErrorInformacionAdicional;
    @FXML
    private Label lbErrorTemaInteres;
    @FXML
    private AnchorPane apVentana;
    @FXML
    private Pane paneProfesorColaborador;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarListeners();
        cargarIdiomas();
        cargarPeriodos();
        cargarAreasAcademicas();
    }
    
    public void setProfesorExterno(ProfesorExterno profesorExterno) {
        this.profesorExterno = profesorExterno;
    }
    

    public void configurarListeners() {
        Utils.agregarListenerSimple(tfNombre, Constantes.MAX_LONG_NOMBRE_COLAB);
        
        cbAreasAcademicas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarDependencias(newValue.getIdAreaAcademica());
                cbDependencias.setDisable(false);
            } else
                cbDependencias.setDisable(true);
        });
        
        cbIdiomas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarProfesoresExternos(newValue.getIdIdioma());
                cbProfesoresExternos.setDisable(false);
            } else
                cbProfesoresExternos.setDisable(true);
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
    
    private void cargarIdiomas() {
        idiomas = FXCollections.observableArrayList();
        List<Idioma> idiomasBD = CatalogoDAO.obtenerIdiomas();
        idiomas.addAll(idiomasBD);
        cbIdiomas.setItems(idiomas);
    }
    
    private void cargarProfesoresExternos(int idIdioma) {
        profesoresExternos = FXCollections.observableArrayList();
        List<ProfesorExterno> profesoresExternosBD = ProfesorExternoDAO.obtenerProfesoresExternosPorIdioma(idIdioma);
        profesoresExternos.addAll(profesoresExternosBD);
        cbProfesoresExternos.setItems(profesoresExternos);
    }
    
    private void cargarPeriodos() {
        periodos = FXCollections.observableArrayList();
        List<Periodo> periodosBD = ModuloUniversitarioDAO.obtenerPeriodos();
        periodos.addAll(periodosBD);
        cbPeriodos.setItems(periodos);
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

    @FXML
    private void btnRegistrarProfesorExterno(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource("/sistemacoil/vista/FXMLRegistrarProfesorExterno.fxml");
            loader.setLocation(fxmlUrl);
            AnchorPane registrarEstudiantesPane = loader.load();
            apVentana.getChildren().add(registrarEstudiantesPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}