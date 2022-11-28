package hotel.agencypt.Controller;

import DataBase.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    Connection con = ConnectionDB.establishConnection();

    /**
     * Inicia a scene com o conteúdo desta classe
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        permissionComboBox.getItems().setAll("Cliente", "Funcionario", "Gestor");
    }

    /**
     * Botão para fechar a aba register
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Botão para fazer o registro
     *
     * @param event Ação do evento
     */
    public void registerButtonOnAction(ActionEvent event) {
        verifyPass();
        verifyUser();
        if (setPasswordField.getText().isBlank() == false && usernameTextField.getText().isBlank() == false) {
            registrationMessageLabel.setText("");
            registrationMessageLabel.setTextFill(Color.BLACK);
            if (verifyPass() == true && verifyUser() == true) {
                registerUser();
            }
        } else {
            registrationMessageLabel.setTextFill(Color.RED);
            registrationMessageLabel.setText("Preencha os campos abaixo!");

        }


    }

    public boolean verifyUser() {
        boolean vUser;
        String verifyUser = "SELECT count(1) FROM Utilizador WHERE nomeuser ='" + usernameTextField.getText() + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(verifyUser);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    VerifyUserLabel.setText("O nome já existe!");
                } else {
                    VerifyUserLabel.setText("");
                }
            }
        } catch (SQLException e) {
            e.getCause();
        }
        if (VerifyUserLabel.getText() == "O nome já existe!") {
            vUser = false;
        } else {
            vUser = true;
        }
        return vUser;
    }

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
     * Registrar o utilizador na base de dados
     */
    public void registerUser() {

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

        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encrypt = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("Plain-Text password: " + password);
        System.out.println("encryptrdpassword:" + encrypt);


        //Inserção dos dados na base de dados
        String insertFields = "INSERT INTO Utilizador(nomeuser, password, tipouser) VALUES('";
        String insertValues = username + "' , '" + encrypt + "' , '" + tipouser + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            PreparedStatement stmt = con.prepareStatement(insertToRegister);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                registrationMessageLabel.setText("O utilizador foi registrado com sucesso!");
                //registrationMessageLabel.setTextFill(Color.GREEN);
            } else {
                registrationMessageLabel.setText("O utilizador não foi registrado!");
                //registrationMessageLabel.setTextFill(Color.RED);
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
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
        Singleton.open("Login", "Hotel >> Login");
    }
}
