package Login;

import Conexion.Usuarios;
import Principal.MainWindow;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Created by fknrk on 7/9/2017.
 */
public class LoginController {
    MainWindow win = new MainWindow();
    Usuarios users= new Usuarios();
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private AnchorPane loginWindow;
    @FXML
    private JFXPasswordField txtContrasena;

    @FXML
    private JFXButton btnLogin;

    @FXML
    void veridicaUsuario(ActionEvent event) throws SQLException {
        String user = txtUsuario.getText();
        String pass = txtContrasena.getText();
        if (users.validaUsuario(user,pass)){
          //  win.dispay();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }


}
