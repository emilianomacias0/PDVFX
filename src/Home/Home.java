package Home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by fknrk on 7/22/2017.
 */
public class Home {
    public Pane Display(){
        // Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Pane panel = new Pane(root);
        return panel;
    }
}
