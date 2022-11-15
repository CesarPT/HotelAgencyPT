package hotel.agencypt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
public class C_registoteste {

    @FXML
    private Button butaoteste;

    @FXML
    private PasswordField passwordController;

    public void validarPassword()
    {
        String password;
        password = passwordController.getText();
        String encryptedpassword = null;
        try{
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println("Plain-Text password: " + password);
        System.out.println("encryptrdpassword:" + encryptedpassword);
    }

    @FXML
    void onClick(ActionEvent event) {
        validarPassword();
    }

}
