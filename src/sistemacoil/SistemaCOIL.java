package sistemacoil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SistemaCOIL extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {                
        loadFonts();

        Parent root = FXMLLoader.load(getClass().getResource("vista/FXMLPrincipalProfesorUV.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Sistema COIL");
        stage.setScene(scene);
        stage.show();
    }

    // Método para cargar la fuente personalizada
    private void loadFonts() {
        Font.loadFont(getClass().getResourceAsStream("/sistemacoil/estilos/tipografias/Inter-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/sistemacoil/estilos/tipografias/Inter-Medium.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/sistemacoil/estilos/tipografias/Inter-Bold.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/sistemacoil/estilos/tipografias/Inter-ExtraBold.ttf"), 12);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
