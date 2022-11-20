package hotel.agencypt.Controller;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView verifyImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ComboBox permissionComboBox;


    /**
     * Inicia a scene com o conteúdo desta classe
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        permissionComboBox.getItems().setAll("Cliente", "Funcionario", "Gestor Hotel");
    }

    /**
     * Botão para fazer o registro
     *
     * @param event Ação do evento
     */
    public void registerButtonOnAction(ActionEvent event){
        if(setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            confirmPasswordLabel.setText("");

        } else {
            confirmPasswordLabel.setText("Palavra-passe não coincide!");
        }
    }

    /**
     * Botão para fechar a aba register e voltar para a aba do menu principal
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    /*public void closeButtonOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        SwitchMenus.open("MenuPrincipal", "SUPERLIGA | Menu Principal 1");
    }*/

    /**
     * Registrar o utilizador na base de dados
     */
    public void registerUser() {
        Connection con = ConnectionDB.establishConnection();

        String username = usernameTextField.getText();
        String password = setPasswordField.getText();
        String perm = (String) permissionComboBox.getSelectionModel().getSelectedItem();
        val

        String insertFields = "INSERT INTO user_account(firstname, lastname, username, password, permission) VALUES('";
        String insertValues = firstname + "' , '" + lastName + "' , '" + username + "' , '" + password + "' , '"+perm+ "')";
        String insertToRegister = insertFields + insertValues;

        try {
            PreparedStatement stmt = con.prepareStatement(insertToRegister);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                registrationMessageLabel.setText("O utilizador foi registrado com sucesso!");

            } else {
                registrationMessageLabel.setText("O utilizador não foi registrado!");
            }

        } catch (Exception e) {
            e.getCause();
        }
    }

    /**
     * Botão para mudar para a aba do login
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void switchToLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        SwitchMenus.open("Login", "SUPERLIGA | Login");
    }
}
