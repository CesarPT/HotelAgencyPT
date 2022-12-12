package hotel.agencypt.Controller;

import Classes.DAO.RegisterDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static Classes.DAO.RegisterDAO.verifyUser;


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
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        permissionComboBox.getItems().setAll("Cliente", "Funcionario", "Gestor");
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
     *
     * @param event Ação do evento
     */
    public void registerButtonOnAction(ActionEvent event) {
        String user = usernameTextField.getText();
        verifyPass();
        boolean vUser = verifyUser(user);
        if (vUser == false) {
            VerifyUserLabel.setText("O nome já existe!");
        } else {
            VerifyUserLabel.setText("");
        }
        if (setPasswordField.getText().isBlank() == false && usernameTextField.getText().isBlank() == false) {
            registrationMessageLabel.setText("");
            if (verifyPass() == true && verifyUser(user) == true) {
                registerUser();
            }
        } else {
            registrationMessageLabel.setTextFill(Color.RED);
            registrationMessageLabel.setText("Preencha os campos abaixo!");

        }
    }


    /**
     * Verificação se as passwords coincidem
     *
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
        JFrame frame = new JFrame("Register");
        frame.setTitle("Registo");

        char tipouser;
        String username = usernameTextField.getText();
        String perm = (String) permissionComboBox.getSelectionModel().getSelectedItem();
        if (perm == "Gestor") {
            tipouser = 'G';
        } else if (perm == "Funcionario") {
            tipouser = 'F';
        } else {
            tipouser = 'C';
        }

        //Encrpytação da password
        String encrypt = "";
        String password = setPasswordField.getText();
        encrypt = encryption.encrypt(password, encrypt);


        //Inserção dos dados na base de dados
        String insertFields = "INSERT INTO Utilizador(nomeuser, password, tipouser) VALUES('";
        String insertValues = username + "' , '" + encrypt + "' , '" + tipouser + "')";
        String insertToRegister = insertFields + insertValues;
        RegisterDAO.Register(tipouser, username, insertToRegister);
        JOptionPane.showMessageDialog(frame, "Registado com sucesso!");
    }


    /**
     * Botão para mudar para a aba do login
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void switchToLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
        Singleton.open("Login", "Hotel >> Login");
    }
}
