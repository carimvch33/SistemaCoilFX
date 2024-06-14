package sistemacoil.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sistemacoil.modelo.dao.CatalogoDAO;
import sistemacoil.modelo.dao.ProfesorExternoDAO;
import sistemacoil.modelo.dao.UniversidadDAO;
import sistemacoil.modelo.pojo.Idioma;
import sistemacoil.modelo.pojo.Pais;
import sistemacoil.modelo.pojo.ProfesorExterno;
import sistemacoil.modelo.pojo.Universidad;
import sistemacoil.utilidades.Animaciones;
import sistemacoil.utilidades.Constantes;
import sistemacoil.utilidades.Utils;
import sistemacoil.utilidades.Validaciones;

public class FXMLRegistrarProfesorExternoController implements Initializable {

    private ObservableList<Pais> paises;
    private ObservableList<Idioma> idiomas;
    private ObservableList<Universidad> universidades;
    
    @FXML
    private TextField tfNombre;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private Label lbErrorApPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private Label lbErrorApMaterno;
    @FXML
    private TextField tfCorreo;
    @FXML
    private Label lbErrorCorreo;
    @FXML
    private ComboBox<Pais> cbPaises;
    @FXML
    private ComboBox<Idioma> cbIdiomas;
    @FXML
    private Label lbErrorIdioma;
    @FXML
    private ComboBox<Universidad> cbUniversidades;
    @FXML
    private Label lbErrorUniversidad;
    @FXML
    private TextField tfCarrera;
    @FXML
    private Label lbErrorCarrera;
    @FXML
    private TextField tfDepartamento;
    @FXML
    private Label lbErrorDepartamento;
    @FXML
    private Button btCancelarRegistro;
    @FXML
    private Label lbErrorPais;
    @FXML
    private TextField tfTelefono;
    @FXML
    private Label lbErrorTelefono;
    @FXML
    private TextField tfLadaTelefono;
    @FXML
    private Button btLimpiarCampos;
    @FXML
    private Button btRegistrarProfesorExterno;
    @FXML
    private AnchorPane apVentana;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarListeners();
        cargarPaises();
        cargarIdiomas();
        cbUniversidades.setDisable(true);
    }
    
    public void configurarListeners() {
        Utils.agregarListenerNombres(tfNombre, Constantes.MAX_LONG_NOMBRES);
        Utils.agregarListenerNombres(tfApellidoPaterno, Constantes.MAX_LONG_NOMBRES);
        Utils.agregarListenerNombres(tfApellidoMaterno, Constantes.MAX_LONG_NOMBRES);
        Utils.agregarListenerSimple(tfCarrera, Constantes.MAX_LONG_INSTITUCION);
        Utils.agregarListenerSimple(tfDepartamento, Constantes.MAX_LONG_INSTITUCION);
        Utils.agregarListenerTelefono(tfTelefono, Constantes.MAX_LONG_TELEFONO);
        Utils.agregarListenerCorreo(tfCorreo, Constantes.MAX_LONG_CORREO);
        
        cbPaises.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarUniversidades(newValue.getIdPais());
                cargarLadaTelefono(newValue.getLadaTelefono());
                cbUniversidades.setDisable(false);
            } else {
                cbUniversidades.setDisable(true);
            }
        });
    }
    
    private ProfesorExterno crearProfesorExterno() {
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setNombre(tfNombre.getText());
        profesorExterno.setApellidoPaterno(tfApellidoPaterno.getText());
        profesorExterno.setApellidoMaterno(tfApellidoMaterno.getText());
        profesorExterno.setDepartamento(tfDepartamento.getText());
        profesorExterno.setCarrera(tfCarrera.getText());
        profesorExterno.setCorreo(tfCorreo.getText());
        profesorExterno.setTelefono(tfLadaTelefono.getText() + tfTelefono.getText());
        profesorExterno.setIdUniversidad(cbUniversidades.getSelectionModel().getSelectedItem().getIdUniversidad());
        profesorExterno.setIdIdioma(cbIdiomas.getSelectionModel().getSelectedItem().getIdIdioma());
        return profesorExterno;
    }
    
    private void cargarPaises() {
        paises = FXCollections.observableArrayList();
        List<Pais> paisesBD = CatalogoDAO.obtenerPaises();
        paises.addAll(paisesBD);
        cbPaises.setItems(paises);
    }
    
    private void cargarIdiomas() {
        idiomas = FXCollections.observableArrayList();
        List<Idioma> idiomasBD = CatalogoDAO.obtenerIdiomas();
        idiomas.addAll(idiomasBD);
        cbIdiomas.setItems(idiomas);
    }

    private void cargarUniversidades(int idPais) {
        universidades = FXCollections.observableArrayList();
        List<Universidad> universidadesBD = UniversidadDAO.obtenerUniversidadesPorPais(idPais);
        universidades.addAll(universidadesBD);
        cbUniversidades.setItems(universidades);
    }

    private void cargarLadaTelefono(String ladaTelefono) {
        tfLadaTelefono.setText("+" + ladaTelefono);
    }
    
    private void limpiarCamposProfesorExterno(){
        Utils.limpiarCampo(tfNombre, lbErrorNombre);
        Utils.limpiarCampo(tfApellidoPaterno, lbErrorApPaterno);
        Utils.limpiarCampo(tfApellidoMaterno, lbErrorApMaterno);
        Utils.limpiarCampo(tfCorreo, lbErrorCorreo);
        Utils.limpiarCampo(tfTelefono, lbErrorTelefono);
        Utils.limpiarCampo(tfLadaTelefono, lbErrorTelefono);
        Utils.limpiarCampo(tfCarrera, lbErrorCarrera);
        Utils.limpiarCampo(tfDepartamento, lbErrorDepartamento);
        Utils.limpiarComboBox(cbPaises, lbErrorPais);
        Utils.limpiarComboBox(cbIdiomas, lbErrorIdioma);
        Utils.limpiarComboBox(cbUniversidades, lbErrorUniversidad);
    }
    
    private boolean validarCamposProfesor() {
        List<Boolean> validaciones = Arrays.asList(
            Validaciones.validarCampo(tfNombre, lbErrorNombre),
            Validaciones.validarCampo(tfApellidoPaterno, lbErrorApPaterno),
            Validaciones.validarCampo(tfApellidoMaterno, lbErrorApMaterno),
            Validaciones.validarCorreo(tfCorreo, lbErrorCorreo),
            Validaciones.validarCampo(tfTelefono, lbErrorTelefono),
            Validaciones.validarCampo(tfLadaTelefono, lbErrorTelefono),
            Validaciones.validarCampo(tfDepartamento, lbErrorDepartamento),
            Validaciones.validarCampo(tfCarrera, lbErrorCarrera),
            Validaciones.validarComboBox(cbPaises, lbErrorPais),
            Validaciones.validarComboBox(cbIdiomas, lbErrorIdioma),
            Validaciones.validarComboBox(cbUniversidades, lbErrorUniversidad)
        );
        return validaciones.stream().allMatch(Boolean::booleanValue);
    }
    
    private void irARegistrarColaboracion(ProfesorExterno profesorExterno) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource("/sistemacoil/vista/FXMLRegistrarColaboracion.fxml");
            loader.setLocation(fxmlUrl);
            AnchorPane registrarColaboracionPane = loader.load();

            FXMLRegistrarColaboracionController controller = loader.getController();
            controller.setProfesorExterno(profesorExterno);

            apVentana.getChildren().setAll(registrarColaboracionPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void btnClicLimpiarCampos(ActionEvent event) {
        Animaciones.animarPresionBoton(btLimpiarCampos);
        limpiarCamposProfesorExterno();
    }

    @FXML
    private void btnClicCancelarRegistro(ActionEvent event) {
        Utils.redirigirVentana("/sistemacoil/vista/FXMLRegistrarColaboracion.fxml", apVentana);
    }

    @FXML
    private void btnClicRegistrarProfesorExterno(ActionEvent event) {
        Animaciones.animarPresionBoton(btRegistrarProfesorExterno);
        if (validarCamposProfesor()) {
            String nombre = tfNombre.getText();
            String apellidoPaterno = tfApellidoPaterno.getText();
            String apellidoMaterno = tfApellidoMaterno.getText();

            if (!ProfesorExternoDAO.consultarExistenciaProfesorExterno(nombre, apellidoPaterno, apellidoMaterno)) {
                ProfesorExterno profesorExterno = crearProfesorExterno();

                // Pasar el objeto al siguiente controlador
                irARegistrarColaboracion(profesorExterno);
            } else {
                System.out.println("Error: Registro duplicado");
            }
        }
    }


}
   
