package sistemacoilfx.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sistemacoilfx.modelo.dao.OfertaColaboracionUVDAO;
import sistemacoilfx.modelo.pojo.OfertaColaboracionUV;
import sistemacoilfx.observador.ObservadorOfertaColaboracionUV;
import sistemacoilfx.utilidades.Constantes;
import sistemacoilfx.utilidades.Utils;

public class FXMLAdministradorOfertasColaboracionUVController implements Initializable, ObservadorOfertaColaboracionUV {
    
    private int idProfesorUV;
    private ObservableList<OfertaColaboracionUV> ofertasColaboracionUV;

    @FXML
    private Button btnRegresar;
    @FXML
    private TextField tfBusquedaOferta;
    @FXML
    private TableView<OfertaColaboracionUV> tvOfertas;
    @FXML
    private Button btnSiguiente;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colDisciplina;
    @FXML
    private TableColumn colIdioma;
    @FXML
    private TableColumn colAreaAcademica;
    @FXML
    private TableColumn colDependencia;
    @FXML
    private TableColumn colPeriodo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
    }
    
    public void inicializarValores(int idProfesorUV){
        this.idProfesorUV = idProfesorUV;
        cargarDatosOfertasColaboracionUV();
        //configurarBusquedaOferta();
    }
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory("disciplina"));
        colIdioma.setCellValueFactory(new PropertyValueFactory("idioma"));
        colAreaAcademica.setCellValueFactory(new PropertyValueFactory("areaAcademica"));
        colDependencia.setCellValueFactory(new PropertyValueFactory("dependencia"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory("periodo"));
    }
    
    private void cargarDatosOfertasColaboracionUV(){
        ofertasColaboracionUV = FXCollections.observableArrayList();
        HashMap<String, Object> respuesta = OfertaColaboracionUVDAO.obtenerOfertasUVProfesorUV(idProfesorUV);
        boolean isError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!isError){
            ArrayList<OfertaColaboracionUV> ofertasBD = (ArrayList<OfertaColaboracionUV>) respuesta.get("ofertasUV");
            ofertasColaboracionUV.addAll(ofertasBD);
            tvOfertas.setItems(ofertasColaboracionUV);
        }else{
            Utils.mostrarAlertaSimple("Error", "" + respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    private void btnClicSiguiente(ActionEvent event) {
        OfertaColaboracionUV ofertaUVSeleccionada = tvOfertas.getSelectionModel().getSelectedItem();
        if(ofertaUVSeleccionada != null){
            FXMLPrincipalProfesorUVController fxmlppuvc = new FXMLPrincipalProfesorUVController();
            fxmlppuvc.irFormularioOfertaColaboracionUV(ofertaUVSeleccionada, idProfesorUV);
        }else{
            Utils.mostrarAlertaSimple("Selecciona una oferta", "Para modificar la información de una Oferta de Colaboración UV primero debes seleccionarla de la tabla.", Alert.AlertType.WARNING);
        }
    }
    
    private void cerrarVentana(){
        ((Stage) btnRegresar.getScene().getWindow()).close();
    }

    @Override
    public void operacionExitosa(String tipoOperacion, String nombreOfertaColaboracionUV) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}