package hotel.agencypt.Controller;

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
import java.util.ResourceBundle;

import static Classes.DAO.LoginDAO.login;


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
        String user = usernameTextField.getText();
        Stage window = (Stage) loginButton.getScene().getWindow();

        //Encyptação da password
        String encryptedpassword = "";
        String password = enterPasswordField.getText();
        encryptedpassword = encryption.encrypt(password);


        // Verificação se existe na base dados
        boolean login = login(user, window, encryptedpassword);
        if (login == true) {
            loginMessageLabel.setText("Login com sucesso!");
        } else {
            loginMessageLabel.setText("Login invalido, tente novamente!");
        }

    }


}

