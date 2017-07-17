package Pacientes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by fknrk on 7/15/2017.
 */
public class PacientesController implements Initializable {
    @FXML
    private double ancho;
    @FXML
    public double alto = 500;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ancho = 700;
        alto = 700;
    }
}
