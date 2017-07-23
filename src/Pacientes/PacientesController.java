package Pacientes;

import com.jfoenix.controls.JFXTextField;
import com.rigem.utilities.ComRigemUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import com.rigem.utilities.ComRigemUtilities.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by fknrk on 7/15/2017.
 */
public class PacientesController implements Initializable {
    ComRigemUtilities utils = new ComRigemUtilities();
    @FXML
    private double ancho;
    @FXML
    public double alto = 500;
    @FXML
    private JFXTextField txtNombre;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ancho = 700;
        alto = 700;
    }

    public void capitalizar(KeyEvent keyEvent) {

    }
}
