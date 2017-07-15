package Datos;

import Principal.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by fknrk on 7/9/2017.
 */
public class Datos {
MainWindow mw = new MainWindow();
    public Pane Display(){
       // Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DatosView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Registro de Usuarios");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.showAndWait();*/
       Pane panel = new Pane(root);
        return panel;
    }
}
