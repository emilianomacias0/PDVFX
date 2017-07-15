package Registrar;

import Conexion.Conexion;
import Conexion.Roles;
import Conexion.RolesUsuario;
import Conexion.Usuarios;
import Modals.AlertBox;
import Modals.Warning;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Created by fknrk on 7/9/2017.
 */
public class RegistrarController extends Conexion implements Initializable {
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
    private Boolean editar;



    public void Registrar() throws SQLException {
        if(allFields()) {
        String nombre = txtNombre.getText();
        String aMaterno = txtApellidoM.getText();
        String aPaterno = txtApelldioP.getText();
        String nUsuario = txtNombreU.getText();
        String pass1 = pwdContrasena.getText();
        String pass2 = pwdContrasena2.getText();
        String rol = cboRol.getSelectionModel().getSelectedItem().toString();

            if (editar){

            }else {
                int userId = users.InsertaUsuario(nombre, aPaterno, aMaterno, nUsuario, pass1);
                if (userId != 0) {

                    System.out.println("User ID");
                    System.out.println(userId);
                    System.out.println("IdRol:");
                    System.out.println(roles.getRolId(rol));
                    ru.insertaRolesUsuario(userId, roles.getRolId(rol));
                    AlertBox.display("Exito!", "El usuario " + nUsuario + " fue registrado correctamente!");
                    System.out.println("Role: " + rol);
                    User usuario = new User(userId, nombre, aPaterno, aMaterno, nUsuario, pass1);
                    CleanFields();
                    table.getItems().add(usuario);
                }
            }
        }else{
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
       // reg.setDisable(true);

    }

    private Boolean allFields(){
        Boolean t = false;
        if (!txtNombre.getText().equals("") &&
        !txtApelldioP.getText().equals("") &&
        !txtApellidoM.getText().equals("") &&
        !txtNombreU.getText().equals("") &&
        !pwdContrasena.getText().equals("") &&
        !pwdContrasena2.getText().equals("") &&
        !cboRol.getSelectionModel().getSelectedItem().toString().equals(null)){
            t = true;
        }
        return t;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Roles roles = new Roles();
        desactivaCampos();
        CleanFields();
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
        TableColumn<User,String> passColumn = new TableColumn<>("Contrasena");
        passColumn.setMaxWidth(100);
        passColumn.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        try {
            table.setItems(getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.getColumns().addAll(idColumn,nameColumn,apColumn,amColumn,userColumn,passColumn);
    }

    /*
    private void removeRow() {
        ObservableList<Product> productSelected ,allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

   private void addRow() {
        Product product = new Product();
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setQuantity(Integer.parseInt(quantityInput.getText()));
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Lap Top",8.59,20));
        products.add(new Product("Bed",59.32,15));
        products.add(new Product("BaseBall",15.99,80));
        products.add(new Product("PS4",300,2));
        products.add(new Product("Xbox One",300,2));
        return products;
    }
*/
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
                    rs.getString("contrasena")));
        }
       // users.add(new User(2,"Emiliano","Macias","Lucas","emi","macias"));

        return users;
    }
    public void editarUsuario(ActionEvent actionEvent) throws SQLException {
        editar = true;
        User datos = table.getSelectionModel().getSelectedItem();
      //  System.out.println(datos.getName());
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
}
