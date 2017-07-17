package Registrar;

import Conexion.Conexion;
import Conexion.Roles;
import Conexion.RolesUsuario;
import Conexion.Usuarios;
import Modals.AlertBox;
import Modals.ConfirmBox;
import Modals.Warning;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Created by fknrk on 7/9/2017.
 */
public class RegistrarController extends Conexion implements Initializable {
    Date dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fechaActual = sdf.format(dt);
    Usuarios users = new Usuarios();
    RolesUsuario ru = new RolesUsuario();
    Roles roles = new Roles();
    public TextField txtNombre;
    public TextField txtApelldioP;
    public TextField txtApellidoM;
    public TextField txtNombreU;
    public PasswordField pwdContrasena;
    public PasswordField pwdContrasena2;
    public ComboBox cboRol;
    @FXML
    private TableView<User> table;
    @FXML
    private JFXButton reg;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private MenuItem mneEditarUsuario;
    @FXML
    private MenuItem mnEliminaUsuario;

    private Boolean editar = false;
    private int idUsuarioEditar;



    public void Registrar() throws SQLException {
        if (allFields()) {
            String nombre = txtNombre.getText();
            String aMaterno = txtApellidoM.getText();
            String aPaterno = txtApelldioP.getText();
            String nUsuario = txtNombreU.getText();
            String pass1 = pwdContrasena.getText();
            String pass2 = pwdContrasena2.getText();
            String rol = cboRol.getSelectionModel().getSelectedItem().toString();
            if (pass1.equals(pass2)) {
                if (editar) {
                    users.editaUsuario(idUsuarioEditar, nombre, aPaterno, aMaterno, nUsuario, pass1);
                    ru.actualizaRolesUsuario(idUsuarioEditar, roles.getRolId(rol));
                    CleanFields();
                    desactivaCampos();
                    AlertBox.display("Exito!", "Usuario actualizado correctamente");
                    editar = false;
                } else {
                    int userId = users.InsertaUsuario(nombre, aPaterno, aMaterno, nUsuario, pass1,fechaActual);
                    if (userId != 0) {

                        System.out.println("User ID");
                        System.out.println(userId);
                        System.out.println("IdRol:");
                        System.out.println(roles.getRolId(rol));
                        ru.insertaRolesUsuario(userId, roles.getRolId(rol));
                        AlertBox.display("Exito!", "El usuario " + nUsuario + " fue registrado correctamente!");
                        System.out.println("Role: " + rol);
                        User usuario = new User(userId, nombre, aPaterno, aMaterno, nUsuario, "**********",fechaActual);
                        CleanFields();
                        table.getItems().add(usuario);
                        desactivaCampos();

                    }
                }
            } else {
                AlertBox.display("Error!", "Las contraseñas no coinsiden");
            }

        }else {
            AlertBox.display("Error!", "Debes llenar todos los campos");

        }

    }

    public void CleanFields(){
        txtNombre.clear();
        txtApelldioP.clear();
        txtApellidoM.clear();
        txtNombreU.clear();
        pwdContrasena.clear();
        pwdContrasena2.clear();
        cboRol.getSelectionModel().clearSelection();
        reg.setText("Registrar");
        idUsuarioEditar = 0;
    }

    private Boolean allFields(){
        Boolean t = false;
        if (!txtNombre.getText().equals("") &&
        !txtApelldioP.getText().equals("") &&
        !txtApellidoM.getText().equals("") &&
        !txtNombreU.getText().equals("") &&
        !pwdContrasena.getText().equals("") &&
        !pwdContrasena2.getText().equals("") &&
        !cboRol.getSelectionModel().isEmpty()){
            t = true;
        }
        return t;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Roles roles = new Roles();
        desactivaCampos();
        CleanFields();

//        JFXTextField validationField = new JFXTextField();
//        validationField.setPromptText("With Validation..");
//        RequiredFieldValidator validator = new RequiredFieldValidator();
//        validator.setMessage("Input Required");
//        validator.setIcon(new Icon(AwesomeIcon.DASHBOARD, "Domain", "3em"));
//        validationField.getValidators().add(validator);
//        validationField.focusedProperty().addListener((o,oldVal,newVal)->{
//            if(!newVal) validationField.validate();
//        });
        cboRol.getItems().removeAll(cboRol.getItems());
        try {
            cboRol.getItems().addAll(roles.getRoles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TableColumn<User,Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(0);
        idColumn.setVisible(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        TableColumn<User,String> nameColumn  = new TableColumn<>("Nombre");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        // new columns
        TableColumn<User,String> apColumn  = new TableColumn<>("Apellido P.");
        apColumn.setMinWidth(100);
        apColumn.setCellValueFactory(new PropertyValueFactory<>("apellidoP"));
        // new columns
        TableColumn<User,String> amColumn  = new TableColumn<>("Apellido M.");
        amColumn.setMinWidth(100);
        amColumn.setCellValueFactory(new PropertyValueFactory<>("apellidoM"));
        //new column
        TableColumn<User,String > userColumn = new TableColumn<>("Usuario");
        userColumn.setMaxWidth(100);
        userColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        //new column
        TableColumn<User,String> passColumn = new TableColumn<>("Contraseña");
        passColumn.setMaxWidth(100);
        passColumn.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        TableColumn<User,String> dateColumn = new TableColumn<>("Creado el");
        dateColumn.setMaxWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("creadoEl"));
        try {
            table.setItems(getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.getColumns().addAll(idColumn,nameColumn,apColumn,amColumn,userColumn,passColumn,dateColumn);
    }


    public ObservableList<User> getUser() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        Statement st = con.createStatement();
        String Consulta = "Select * FROM usuarios";
        ResultSet rs = null;
        rs = st.executeQuery(Consulta);
        while(rs.next()){
            users.add(new User(rs.getInt("idUsuario")
                    ,rs.getString("nombre"),
                    rs.getString("apellidoP"),
                    rs.getString("apellidoM"),
                    rs.getString("usuario"),
                    "**********",
                    rs.getString("creadoEl")));
        }
       // users.add(new User(2,"Emiliano","Macias","Lucas","emi","macias"));

        return users;
    }
    public void editarUsuario(ActionEvent actionEvent) throws SQLException {
        editar = true;
        User datos = table.getSelectionModel().getSelectedItem();
      //  System.out.println(datos.getName());
        idUsuarioEditar = datos.getIdUsuario();
        Map<String,String> userData = users.getUser(datos.getIdUsuario()+"");
        txtNombre.setText(userData.get("Nombre"));
        txtApelldioP.setText(userData.get("ApellidoP"));
        txtApellidoM.setText(userData.get("ApellidoM"));
        txtNombreU.setText(userData.get("Usuario"));
        pwdContrasena.clear();
        pwdContrasena2.clear();
        cboRol.setValue(userData.get("Rol"));
        activaCampos();
        reg.setText("Editar");

    }

    public void desactivaCampos(){
        txtNombre.setDisable(true);
        txtApelldioP.setDisable(true);
        txtApellidoM.setDisable(true);
        txtNombreU.setDisable(true);
        pwdContrasena.setDisable(true);
        pwdContrasena2.setDisable(true);
        cboRol.setDisable(true);
        reg.setDisable(true);
        btnLimpiar.setDisable(true);
    }

    public void activaCampos(){
        txtNombre.setDisable(false);
        txtApelldioP.setDisable(false);
        txtApellidoM.setDisable(false);
        txtNombreU.setDisable(false);
        pwdContrasena.setDisable(false);
        pwdContrasena2.setDisable(false);
        cboRol.setDisable(false);
        reg.setDisable(false);
        btnLimpiar.setDisable(false);

    }


    public void eliminaUsuario(ActionEvent actionEvent) throws SQLException {
        User datos = table.getSelectionModel().getSelectedItem();
        ObservableList<User> productSelected ,allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();


        if (ConfirmBox.display("Cuidad","Deseas eliminar al usuario \""+datos.getNombre()+"\"")){
            if (users.eliminaUsuario(datos.getIdUsuario()+"")){
                ru.eliminaRolesUsuario(datos.getIdUsuario());
                productSelected.forEach(allProducts::remove);
                AlertBox.display("Exito","El usuario \""+datos.getNombre()+"\" fue eliminado exitosamente");
            }
        }
    }
}
