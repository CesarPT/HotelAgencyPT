package Classes.DAO;

import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;
import hotel.agencypt.Controller.Singleton;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class LoginDAO {
    static String correctOrIncorrect;

    static Connection con = ConnectionDB.establishConnection();

    public static boolean login(String user, Stage window, String encryptedpassword) {
        String verifyLogin = "SELECT count(1) FROM Utilizador WHERE nomeuser ='" + user + "' AND convert (varchar(MAX), password) = '" + encryptedpassword + "'";
        String verifyPerm = ("SELECT tipouser FROM Utilizador WHERE nomeuser ='" + user + "'");
        boolean log;
        try {
            PreparedStatement stmt = con.prepareStatement(verifyLogin);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    correctOrIncorrect = "Login com sucesso!";
                    Controller.getInstance().setUsername(user);
                } else {
                    correctOrIncorrect = "Login invalido, tente novamente!";

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (Objects.equals(correctOrIncorrect, "Login com sucesso!")) {
            //Passa informações para a scene seguinte
            validatePerms(verifyPerm, window);
            log = true;
        } else {
            log = false;
        }
        return log;
    }

    /**
     * Valida as permissões e abre a aba dessa mesma.
     */
    public static void validatePerms(String verifyPerm, Stage window) {
        try {
            PreparedStatement stmt = con.prepareStatement(verifyPerm);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                if (Objects.equals(rs.getString("tipouser"), "G")) {
                    Controller.getInstance().setTipo_user('G');
                    window.close();
                    Singleton.open("GestorHotel", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Gestor de Hotel");
                }

                if (Objects.equals(rs.getString("tipouser"), "F")) {
                    Controller.getInstance().setTipo_user('F');
                    window.close();
                    Singleton.open("funcionariointerface", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Funcionário");
                }

                if (Objects.equals(rs.getString("tipouser"), "C")) {
                    Controller.getInstance().setTipo_user('C');
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
