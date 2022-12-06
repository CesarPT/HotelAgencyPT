package hotel.agencypt.Controller;

import DataBase.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    /**
     * Inicialização do controlador.
     *
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File lockFile = new File("Imagens/LoginIcon.PNG");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    /**
     * Verifica se os campos estão vazios.
     *
     * @param event Ação do evento
     */
    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Preencha o username e a password");
        }
    }

    /**
     * Fecha a janela do login através do butão cancel.
     *
     * @param event Ação do evento
     */
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Faz encriptação e faz a validação do login para verificar se existe na base de dados.
     */
    public void validateLogin() {
        Connection con = ConnectionDB.establishConnection();


        //Encyptação da password
        String encryptedpassword = "";
        String password = enterPasswordField.getText();

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
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("Plain-Text password: " + password);
        System.out.println("encryptrdpassword:" + encryptedpassword);


        // Verificação se existe na base dados
        String verifyLogin = "SELECT count(1) FROM Utilizador WHERE nomeuser ='" + usernameTextField.getText() + "' AND convert (varchar(MAX), password) = '" + encryptedpassword + "'";

        try {
            PreparedStatement stmt = con.prepareStatement(verifyLogin);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    loginMessageLabel.setText("Login com sucesso!");
                    //Passa informações para a scene seguinte
                    Controller.getInstance().setUsername(usernameTextField.getText());
                    validatePerms(con);
                } else {
                    loginMessageLabel.setText("Login invalido, tente novamente!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * Valida as permissões e abre a aba dessa mesma.
     */
    public void validatePerms(Connection con) {
        String verifyPerm = ("SELECT tipouser FROM Utilizador WHERE nomeuser ='" + usernameTextField.getText() + "'");

        try {
            PreparedStatement stmt = con.prepareStatement(verifyPerm);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (Objects.equals(rs.getString("tipouser"), "G")) {

                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.close();
                    Singleton.open("GestorHotel", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Gestor de Hotel");
                }
                if (Objects.equals(rs.getString("tipouser"), "F")) {

                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.close();
                    Singleton.open("funcionariointerface", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Funcionário");
                }
                if (Objects.equals(rs.getString("tipouser"), "C")) {

                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.close();
                    Singleton.open("Cliente", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Cliente");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}

