package Datos;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class DatosController implements Initializable {

    @FXML
    private Pane pnlDatos;
    @FXML
    private JFXTreeTableView<?> table;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            pnlDatos.setPrefSize(200,200);
            System.out.println();
        }catch (Exception ex){
            System.out.println(ex);
        }




    }


}
