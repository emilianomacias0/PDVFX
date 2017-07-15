package Principal;

import Datos.Datos;
import Registrar.Registrar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    MainWindow main = new MainWindow();
    public double alto ;
    public double ancho ;

    @FXML
    private AnchorPane stgVentana;

    @FXML
    private MenuItem mnuCerrar;
    @FXML
    private BorderPane mainPane;



      public void cerrarPrograma(ActionEvent actionEvent) {
          // get a handle to the stage
          Stage stage = (Stage) stgVentana.getScene().getWindow();
          // do what you have to do
          stage.close();
    }

   /* public void registrarUsuario(ActionEvent actionEvent) throws IOException {
        Registrar reg = new Registrar();
        try {
            reg.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    void setDatos(ActionEvent event) {
        Registrar datos = new Registrar();
       // ((Node)(event.getSource())).getScene().getWindow();
        Pane nueva = datos.Display();

        //nueva.setPrefSize(500   ,500);
       // System.out.println("Ancho: "+ancho);
        //System.out.println("Alto: "+alto);
      //  ((Stage)stgVentana.getScene().getWindow());
    if (nueva.isVisible()){
        mainPane.setCenter(nueva);
    }else {

    }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // alto = mainPane.getCenter().getLayoutY();
         // ancho = mainPane.getCenter().getLayoutX();
        mainPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // alto.bind((ObservableValue<? extends Double>) newValue);
                alto=(double)newValue;
                System.out.println(alto);
            }
        });
        mainPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //ancho.bind((ObservableValue<? extends Double>) newValue);
                ancho = (double)newValue;
            }
        });
    }
}
