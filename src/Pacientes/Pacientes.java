package Pacientes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by fknrk on 7/15/2017.
 */
public class Pacientes {
        public Pane Display(){
        // Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("PacientesView.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Pane panel = new Pane(root);
        return panel;
    }
}
