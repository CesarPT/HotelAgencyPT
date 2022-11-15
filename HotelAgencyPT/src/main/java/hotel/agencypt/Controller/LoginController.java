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
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    /**
     * Inicialização do controlador
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File lockFile = new File("Imagens/LoginIcon.PNG");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    /**
     * Login através do butão
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
     * Fecha a aba login através do butão cancel
     *
     * @param event Ação do evento
     */
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Validação do login para verificar se existe na base de dados
     */
    public void validateLogin() {

        Connection con = ConnectionDB.establishConnection();

        String verifyLogin = "SELECT count(1) FROM Utilizador WHERE nomeuser ='" + usernameTextField.getText() + "' AND password ='" + enterPasswordField.getText() + "'";

        try {
            PreparedStatement stmt = con.prepareStatement(verifyLogin);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    loginMessageLabel.setText("Login com sucesso!");
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
     * Validação das permissões
     */
   public void validatePerms(Connection con) {
        String verifyPerm = ("SELECT tipouser FROM Utilizador WHERE nomeuser ='"+usernameTextField.getText()+"'");

        try {
            PreparedStatement stmt = con.prepareStatement(verifyPerm);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                if (Objects.equals(rs.getString("tipouser"), "G")) {

                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.close();
                    Singleton.open("GestorHotel", "Hotel >> Gestor de Hotel");
                }
                if (Objects.equals(rs.getString("tipouser"), "F")) {

                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.close();
                    Singleton.open("Funcionario", "Hotel >> Funcionário");
                }
                if (Objects.equals(rs.getString("tipouser"), "C")) {

                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.close();
                    Singleton.open("Cliente", "Hotel >> Cliente");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
