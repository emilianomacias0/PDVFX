package Registrar;

import Principal.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.io.IOException;

/**
 * Created by fknrk on 7/9/2017.
 */
public class Registrar  {
    /*
   public void display() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Registrar.fxml"));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
       primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Registro de Usuarios");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.showAndWait();
    }*/

    MainWindow mw = new MainWindow();
    public Pane Display(){
        // Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Registrar.fxml"));
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
