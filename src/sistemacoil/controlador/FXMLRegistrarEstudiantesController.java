package sistemacoil.controlador;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import sistemacoil.modelo.pojo.Estudiante;
import sistemacoil.utilidades.Animaciones;
import sistemacoil.utilidades.Constantes;
import sistemacoil.utilidades.Validaciones;
import sistemacoil.utilidades.Utils;

public class FXMLRegistrarEstudiantesController implements Initializable {

    private ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    private BooleanProperty haySeleccion = new SimpleBooleanProperty(false);

    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreo;
    @FXML
    private Label lbErrorMatricula;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorApPaterno;
    @FXML
    private Label lbErrorApMaterno;
    @FXML
    private Label lbErrorCorreo;
    @FXML
    private TableView<Estudiante> tvEstudiantes;
    @FXML
    private TableColumn<Estudiante, String> colMatricula;
    @FXML
    private TableColumn<Estudiante, String> colApellidos;
    @FXML
    private TableColumn<Estudiante, String> colNombre;
    @FXML
    private TextField tfBuscarEstudiante;
    @FXML
    private Button btEliminar;
    @FXML
    private Button btAgregar;
    @FXML
    private CheckBox cbRegistroCompleto;
    @FXML
    private Button btRegistrarListaEstudiantes;
    @FXML
    private Button btLimpiarCampos;
    @FXML
    private Button btCancelarRegistro;
    @FXML
    private Button btEditar;
    @FXML
    private HBox hboxBuscarEstudiante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        configurarListeners();
        btRegistrarListaEstudiantes.setDisable(true);
        btEliminar.disableProperty().bind(haySeleccion.not());
        btEditar.disableProperty().bind(haySeleccion.not());
        btAgregar.setText("Agregar Estudiante");
        btRegistrarListaEstudiantes.disableProperty().bind(cbRegistroCompleto.selectedProperty().not());
    }
    
    private void configurarTabla() {
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getApellidoPaterno() + "-" + cellData.getValue().getApellidoMaterno()));

        tvEstudiantes.setItems(listaEstudiantes);
    }
    
    private void configurarListeners() {
        Utils.agregarListenerSimple(tfMatricula, Constantes.MAX_LONG_MATRICULA);
        Utils.agregarListenerNombres(tfNombre, Constantes.MAX_LONG_NOMBRES);
        Utils.agregarListenerNombres(tfApellidoPaterno, Constantes.MAX_LONG_NOMBRES);
        Utils.agregarListenerNombres(tfApellidoMaterno, Constantes.MAX_LONG_NOMBRES);
        
        tfCorreo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > Constantes.MAX_LONG_CORREO) {
                tfCorreo.setText(oldValue);
                Animaciones.animarShake(tfCorreo);
            }
        });
        
        tfMatricula.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (tfCorreo.getText().isEmpty() && Validaciones.validarMatricula(tfMatricula, lbErrorMatricula)) {
                    autocompletarCorreo(tfMatricula.getText());
                }
            }
        });
        
        tvEstudiantes.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Estudiante>) change -> {
            haySeleccion.set(!change.getList().isEmpty());
        });

        tfBuscarEstudiante.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarEstudiante();
        });

        cbRegistroCompleto.selectedProperty().addListener((observable, oldValue, newValue) -> {
            actualizarEstadoCampos(newValue);
        });
    }

    private boolean validarCamposEstudiantes() {
        List<Boolean> validaciones = Arrays.asList(
            Validaciones.validarMatricula(tfMatricula, lbErrorMatricula) && validarMatriculaExistente(tfMatricula),
            Validaciones.validarCampo(tfNombre, lbErrorNombre),
            Validaciones.validarCampo(tfApellidoPaterno, lbErrorApPaterno),
            Validaciones.validarCampo(tfApellidoMaterno, lbErrorApMaterno),
            Validaciones.validarCorreo(tfCorreo, lbErrorCorreo)
        );

        return validaciones.stream().allMatch(Boolean::booleanValue);
    }

    
    private boolean validarMatriculaExistente(TextField tfMatricula) {
        String matricula = tfMatricula.getText();
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getMatricula().equals(matricula)) {
                Animaciones.animarShake(tfMatricula);
                lbErrorMatricula.setText("Matrícula ya existente*");
                return false;
            }
        }
        return true;
    }
    
    private void actualizarEstadoCampos(boolean deshabilitar) {
        tfMatricula.setDisable(deshabilitar);
        tfNombre.setDisable(deshabilitar);
        tfApellidoPaterno.setDisable(deshabilitar);
        tfApellidoMaterno.setDisable(deshabilitar);
        tfCorreo.setDisable(deshabilitar);
        btAgregar.setDisable(deshabilitar);
        btEditar.setDisable(deshabilitar);
        btEliminar.setDisable(deshabilitar);
        tvEstudiantes.setDisable(deshabilitar);
    }
    
    private void agregarEstudianteTabla() {
        Estudiante estudiante = new Estudiante(
                tfNombre.getText().trim(),
                tfApellidoPaterno.getText().trim(),
                tfApellidoMaterno.getText().trim(),
                tfMatricula.getText().trim(),
                tfCorreo.getText().trim());

        listaEstudiantes.add(estudiante);
        System.out.println(estudiante);
        limpiarCamposEstudiantes();
    }

    private void limpiarCamposEstudiantes() {
        Utils.limpiarCampo(tfMatricula, lbErrorMatricula);
        Utils.limpiarCampo(tfNombre, lbErrorNombre);
        Utils.limpiarCampo(tfApellidoPaterno, lbErrorApPaterno);
        Utils.limpiarCampo(tfApellidoMaterno, lbErrorApMaterno);
        Utils.limpiarCampo(tfCorreo, lbErrorCorreo);
        tvEstudiantes.getSelectionModel().clearSelection();
        btAgregar.setText("Agregar Estudiante");
        btLimpiarCampos.setText("Limpiar Campos");
    }

    private void autocompletarCorreo(String matricula) {
        if (matricula != null && !matricula.isEmpty()) {
            String correo = "z" + matricula.toLowerCase() + "@estudiantes.uv.mx";
            tfCorreo.setText(correo);
        } else {
            tfCorreo.setText("");
        }
    }
    
    private void cargarDatosEstudiante(Estudiante estudianteSeleccionado) {
        tfMatricula.setText(estudianteSeleccionado.getMatricula());
        tfNombre.setText(estudianteSeleccionado.getNombre());
        tfApellidoPaterno.setText(estudianteSeleccionado.getApellidoPaterno());
        tfApellidoMaterno.setText(estudianteSeleccionado.getApellidoMaterno());
        tfCorreo.setText(estudianteSeleccionado.getCorreo());
    }
    
    private void modificarEstudianteSeleccionado() {
        Estudiante estudianteSeleccionado = tvEstudiantes.getSelectionModel().getSelectedItem();

        if (estudianteSeleccionado != null) {
            estudianteSeleccionado.setMatricula(tfMatricula.getText().trim());
            estudianteSeleccionado.setNombre(tfNombre.getText().trim());
            estudianteSeleccionado.setApellidoPaterno(tfApellidoPaterno.getText().trim());
            estudianteSeleccionado.setApellidoMaterno(tfApellidoMaterno.getText().trim());
            estudianteSeleccionado.setCorreo(tfCorreo.getText().trim());
            tvEstudiantes.refresh();
            tvEstudiantes.getSelectionModel().clearSelection();
            limpiarCamposEstudiantes();
        } else {
            System.out.println("¡No se ha seleccionado ningún estudiante!");
        }
    }
    
    private void eliminarEstudianteSeleccionado(Estudiante estudianteSeleccionado) {
        if (estudianteSeleccionado != null) {
            boolean confirmacion = Utils.mostrarAlertaConfirmacion("Confirmar Eliminación", 
                    "¿Estás seguro de eliminar este estudiante?");
            if (confirmacion)
                listaEstudiantes.remove(estudianteSeleccionado);
        } else
            System.out.println("¡No se ha seleccionado ningún estudiante!");
    }
    
    private void buscarEstudiante() {
        String busqueda = tfBuscarEstudiante.getText().trim().toLowerCase();
        ObservableList<Estudiante> resultados = FXCollections.observableArrayList();

        if (busqueda.isEmpty()) {
            tvEstudiantes.setItems(listaEstudiantes);
            return;
        }

        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getMatricula().toLowerCase().contains(busqueda) ||
                estudiante.getNombre().toLowerCase().contains(busqueda) ||
                estudiante.getApellidoPaterno().toLowerCase().contains(busqueda) ||
                estudiante.getApellidoMaterno().toLowerCase().contains(busqueda)) {
                resultados.add(estudiante);
            }
        }
        tvEstudiantes.setItems(resultados);
    }

    @FXML
    private void btnAgregarEstudiante(ActionEvent event) {
        Animaciones.animarPresionBoton(btAgregar);
        if (validarCamposEstudiantes()) {
            if (btAgregar.getText().equals("Agregar Estudiante"))
                agregarEstudianteTabla();
            else {
                modificarEstudianteSeleccionado();
                btAgregar.setText("Agregar Estudiante");
            }
        }
    }
    
    @FXML
    private void btnLimpiarCampos(ActionEvent event) {
        Animaciones.animarPresionBoton(btLimpiarCampos);
        if (btLimpiarCampos.getText().equals("Cancelar Edición"))
            limpiarCamposEstudiantes();
        else
            limpiarCamposEstudiantes();
    }
    
    @FXML
    private void btnEditarEstudiante(ActionEvent event) {
        Animaciones.animarPresionBoton(btEditar);
        Estudiante estudianteSeleccionado = tvEstudiantes.getSelectionModel().getSelectedItem();

        if (estudianteSeleccionado != null) {
            btAgregar.setText("Modificar Estudiante");
            btLimpiarCampos.setText("Cancelar Edición");
            cargarDatosEstudiante(estudianteSeleccionado);
        } else
            System.out.println("¡No se ha seleccionado ningún estudiante!");
    }
    
    @FXML
    private void btnEliminarEstudiante(ActionEvent event) {
        Animaciones.animarPresionBoton(btEliminar);
        Estudiante estudianteSeleccionado = tvEstudiantes.getSelectionModel().getSelectedItem();
        eliminarEstudianteSeleccionado(estudianteSeleccionado);
    }

    @FXML
    private void btnCancelarBusqueda(ActionEvent event) {
        tfBuscarEstudiante.clear();
        tvEstudiantes.setItems(listaEstudiantes);
    }

    @FXML
    private void btnRegistrarListaEstudiantes(ActionEvent event) {
    }
}
