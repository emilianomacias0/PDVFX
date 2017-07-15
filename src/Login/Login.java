package Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by fknrk on 7/9/2017.
 */
public class Login extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        primaryStage.setTitle("Acceso");
        Scene scene = new Scene(root,400,200);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.UTILITY);
        scene.getStylesheets().add("Estilos/estilologin.css");
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
