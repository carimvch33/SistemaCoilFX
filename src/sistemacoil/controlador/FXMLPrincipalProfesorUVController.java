package sistemacoil.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jafet
 */
public class FXMLPrincipalProfesorUVController implements Initializable {
    
    @FXML
    private AnchorPane apVentana;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource("/sistemacoil/vista/FXMLFormularioOfertaColaboracionUV.fxml");
            loader.setLocation(fxmlUrl);
            AnchorPane registrarEstudiantesPane = loader.load();
            apVentana.getChildren().add(registrarEstudiantesPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }  
    
    
    
}
