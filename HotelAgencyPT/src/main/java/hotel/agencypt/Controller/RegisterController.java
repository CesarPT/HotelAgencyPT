package hotel.agencypt.Controller;

import Classes.DAO.RegisterDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Classes.DAO.RegisterDAO.*;

/**
 * Classe pública do RegisterController.fxml
 */
public class RegisterController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button LoginButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Label VerifyUserLabel;
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
     * Inicia a scene com o conteúdo desta classe.
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getTypeUserComboBox(permissionComboBox);
    }

    /**
     * Botão para fechar a janela register.
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void cancelButtonOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
            Singleton.open("funcionariointerface", "Hotel >> Funcionário");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Botão para fazer o registo.
     * @param event Ação do evento
     */
    public void registerButtonOnAction(ActionEvent event) {
        String user = usernameTextField.getText();
        verifyPass();
        if (!verifyUser(user)) {
            VerifyUserLabel.setText("O nome já existe!");
        } else {
            VerifyUserLabel.setText("");
        }
        if (!setPasswordField.getText().isBlank() && !usernameTextField.getText().isBlank()) {
            registrationMessageLabel.setText("");
            if (verifyPass() && verifyUser(user)) {
                registerUser();
            }
        } else {
            registrationMessageLabel.setTextFill(Color.RED);
            registrationMessageLabel.setText("Preencha os campos abaixo!");

        }
    }


    /**
     * Verificação se as passwords coincidem
     * @return password
     */
    public boolean verifyPass() {
        boolean pass;
        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            confirmPasswordLabel.setText("");
            pass = true;
        } else {
            confirmPasswordLabel.setText("Palavra-passe não coincide!");
            pass = false;
        }
        return pass;
    }

    /**
     * Faz a encriptação da password e regista o utilizador na base de dados
     */
    public void registerUser() {
        String username = usernameTextField.getText();

        //Encrpytação da password
        String encrypt = "";
        String password = setPasswordField.getText();
        encrypt = encryption.encrypt(password, encrypt);

        if (permissionComboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação!");
            alert.setHeaderText("Por favor selecione um tipo de utilizador!");
            alert.showAndWait();
        } else {
            String perm = permissionComboBox.getSelectionModel().getSelectedItem().toString();
            getPrefix(perm);

            //Inserção dos dados na base de dados
            RegisterDAO.Register(username, encrypt);
        }
    }


    /**
     * Botão para mudar para a aba do login
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void switchToLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
        Singleton.open("Login", "Hotel >> Login");
    }
}
